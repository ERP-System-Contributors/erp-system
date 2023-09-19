package io.github.erp.erp.resources.gdi;

/*-
 * Erp System - Mark V No 8 (Ehud Series) Server ver 1.5.1
 * Copyright © 2021 - 2023 Edwin Njeru (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import io.github.erp.IntegrationTest;
import io.github.erp.domain.IsoCountryCode;
import io.github.erp.domain.Placeholder;
import io.github.erp.erp.resources.IsoCountryCodeResourceProd;
import io.github.erp.erp.resources.PlaceholderResourceIT;
import io.github.erp.repository.IsoCountryCodeRepository;
import io.github.erp.repository.search.IsoCountryCodeSearchRepository;
import io.github.erp.service.IsoCountryCodeService;
import io.github.erp.service.dto.IsoCountryCodeDTO;
import io.github.erp.service.mapper.IsoCountryCodeMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;

import io.github.erp.web.rest.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link IsoCountryCodeResourceProd} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser(roles = {"GRANULAR_REPORTS_USER"})
public class IsoCountryCodeResourceIT {

    private static final String DEFAULT_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/granular-data/iso-country-codes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/granular-data/_search/iso-country-codes";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private IsoCountryCodeRepository isoCountryCodeRepository;

    @Mock
    private IsoCountryCodeRepository isoCountryCodeRepositoryMock;

    @Autowired
    private IsoCountryCodeMapper isoCountryCodeMapper;

    @Mock
    private IsoCountryCodeService isoCountryCodeServiceMock;

    /**
     * This repository is mocked in the io.github.erp.repository.search test package.
     *
     * @see io.github.erp.repository.search.IsoCountryCodeSearchRepositoryMockConfiguration
     */
    @Autowired
    private IsoCountryCodeSearchRepository mockIsoCountryCodeSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIsoCountryCodeMockMvc;

    private IsoCountryCode isoCountryCode;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IsoCountryCode createEntity(EntityManager em) {
        IsoCountryCode isoCountryCode = new IsoCountryCode()
            .countryCode(DEFAULT_COUNTRY_CODE)
            .countryDescription(DEFAULT_COUNTRY_DESCRIPTION);
        return isoCountryCode;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IsoCountryCode createUpdatedEntity(EntityManager em) {
        IsoCountryCode isoCountryCode = new IsoCountryCode()
            .countryCode(UPDATED_COUNTRY_CODE)
            .countryDescription(UPDATED_COUNTRY_DESCRIPTION);
        return isoCountryCode;
    }

    @BeforeEach
    public void initTest() {
        isoCountryCode = createEntity(em);
    }

    @Test
    @Transactional
    void createIsoCountryCode() throws Exception {
        int databaseSizeBeforeCreate = isoCountryCodeRepository.findAll().size();
        // Create the IsoCountryCode
        IsoCountryCodeDTO isoCountryCodeDTO = isoCountryCodeMapper.toDto(isoCountryCode);
        restIsoCountryCodeMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(isoCountryCodeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the IsoCountryCode in the database
        List<IsoCountryCode> isoCountryCodeList = isoCountryCodeRepository.findAll();
        assertThat(isoCountryCodeList).hasSize(databaseSizeBeforeCreate + 1);
        IsoCountryCode testIsoCountryCode = isoCountryCodeList.get(isoCountryCodeList.size() - 1);
        assertThat(testIsoCountryCode.getCountryCode()).isEqualTo(DEFAULT_COUNTRY_CODE);
        assertThat(testIsoCountryCode.getCountryDescription()).isEqualTo(DEFAULT_COUNTRY_DESCRIPTION);

        // Validate the IsoCountryCode in Elasticsearch
        verify(mockIsoCountryCodeSearchRepository, times(1)).save(testIsoCountryCode);
    }

    @Test
    @Transactional
    void createIsoCountryCodeWithExistingId() throws Exception {
        // Create the IsoCountryCode with an existing ID
        isoCountryCode.setId(1L);
        IsoCountryCodeDTO isoCountryCodeDTO = isoCountryCodeMapper.toDto(isoCountryCode);

        int databaseSizeBeforeCreate = isoCountryCodeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restIsoCountryCodeMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(isoCountryCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IsoCountryCode in the database
        List<IsoCountryCode> isoCountryCodeList = isoCountryCodeRepository.findAll();
        assertThat(isoCountryCodeList).hasSize(databaseSizeBeforeCreate);

        // Validate the IsoCountryCode in Elasticsearch
        verify(mockIsoCountryCodeSearchRepository, times(0)).save(isoCountryCode);
    }

    @Test
    @Transactional
    void getAllIsoCountryCodes() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        // Get all the isoCountryCodeList
        restIsoCountryCodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(isoCountryCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].countryDescription").value(hasItem(DEFAULT_COUNTRY_DESCRIPTION)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllIsoCountryCodesWithEagerRelationshipsIsEnabled() throws Exception {
        when(isoCountryCodeServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restIsoCountryCodeMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(isoCountryCodeServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllIsoCountryCodesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(isoCountryCodeServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restIsoCountryCodeMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(isoCountryCodeServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    void getIsoCountryCode() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        // Get the isoCountryCode
        restIsoCountryCodeMockMvc
            .perform(get(ENTITY_API_URL_ID, isoCountryCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(isoCountryCode.getId().intValue()))
            .andExpect(jsonPath("$.countryCode").value(DEFAULT_COUNTRY_CODE))
            .andExpect(jsonPath("$.countryDescription").value(DEFAULT_COUNTRY_DESCRIPTION));
    }

    @Test
    @Transactional
    void getIsoCountryCodesByIdFiltering() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        Long id = isoCountryCode.getId();

        defaultIsoCountryCodeShouldBeFound("id.equals=" + id);
        defaultIsoCountryCodeShouldNotBeFound("id.notEquals=" + id);

        defaultIsoCountryCodeShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultIsoCountryCodeShouldNotBeFound("id.greaterThan=" + id);

        defaultIsoCountryCodeShouldBeFound("id.lessThanOrEqual=" + id);
        defaultIsoCountryCodeShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllIsoCountryCodesByCountryCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        // Get all the isoCountryCodeList where countryCode equals to DEFAULT_COUNTRY_CODE
        defaultIsoCountryCodeShouldBeFound("countryCode.equals=" + DEFAULT_COUNTRY_CODE);

        // Get all the isoCountryCodeList where countryCode equals to UPDATED_COUNTRY_CODE
        defaultIsoCountryCodeShouldNotBeFound("countryCode.equals=" + UPDATED_COUNTRY_CODE);
    }

    @Test
    @Transactional
    void getAllIsoCountryCodesByCountryCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        // Get all the isoCountryCodeList where countryCode not equals to DEFAULT_COUNTRY_CODE
        defaultIsoCountryCodeShouldNotBeFound("countryCode.notEquals=" + DEFAULT_COUNTRY_CODE);

        // Get all the isoCountryCodeList where countryCode not equals to UPDATED_COUNTRY_CODE
        defaultIsoCountryCodeShouldBeFound("countryCode.notEquals=" + UPDATED_COUNTRY_CODE);
    }

    @Test
    @Transactional
    void getAllIsoCountryCodesByCountryCodeIsInShouldWork() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        // Get all the isoCountryCodeList where countryCode in DEFAULT_COUNTRY_CODE or UPDATED_COUNTRY_CODE
        defaultIsoCountryCodeShouldBeFound("countryCode.in=" + DEFAULT_COUNTRY_CODE + "," + UPDATED_COUNTRY_CODE);

        // Get all the isoCountryCodeList where countryCode equals to UPDATED_COUNTRY_CODE
        defaultIsoCountryCodeShouldNotBeFound("countryCode.in=" + UPDATED_COUNTRY_CODE);
    }

    @Test
    @Transactional
    void getAllIsoCountryCodesByCountryCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        // Get all the isoCountryCodeList where countryCode is not null
        defaultIsoCountryCodeShouldBeFound("countryCode.specified=true");

        // Get all the isoCountryCodeList where countryCode is null
        defaultIsoCountryCodeShouldNotBeFound("countryCode.specified=false");
    }

    @Test
    @Transactional
    void getAllIsoCountryCodesByCountryCodeContainsSomething() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        // Get all the isoCountryCodeList where countryCode contains DEFAULT_COUNTRY_CODE
        defaultIsoCountryCodeShouldBeFound("countryCode.contains=" + DEFAULT_COUNTRY_CODE);

        // Get all the isoCountryCodeList where countryCode contains UPDATED_COUNTRY_CODE
        defaultIsoCountryCodeShouldNotBeFound("countryCode.contains=" + UPDATED_COUNTRY_CODE);
    }

    @Test
    @Transactional
    void getAllIsoCountryCodesByCountryCodeNotContainsSomething() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        // Get all the isoCountryCodeList where countryCode does not contain DEFAULT_COUNTRY_CODE
        defaultIsoCountryCodeShouldNotBeFound("countryCode.doesNotContain=" + DEFAULT_COUNTRY_CODE);

        // Get all the isoCountryCodeList where countryCode does not contain UPDATED_COUNTRY_CODE
        defaultIsoCountryCodeShouldBeFound("countryCode.doesNotContain=" + UPDATED_COUNTRY_CODE);
    }

    @Test
    @Transactional
    void getAllIsoCountryCodesByCountryDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        // Get all the isoCountryCodeList where countryDescription equals to DEFAULT_COUNTRY_DESCRIPTION
        defaultIsoCountryCodeShouldBeFound("countryDescription.equals=" + DEFAULT_COUNTRY_DESCRIPTION);

        // Get all the isoCountryCodeList where countryDescription equals to UPDATED_COUNTRY_DESCRIPTION
        defaultIsoCountryCodeShouldNotBeFound("countryDescription.equals=" + UPDATED_COUNTRY_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllIsoCountryCodesByCountryDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        // Get all the isoCountryCodeList where countryDescription not equals to DEFAULT_COUNTRY_DESCRIPTION
        defaultIsoCountryCodeShouldNotBeFound("countryDescription.notEquals=" + DEFAULT_COUNTRY_DESCRIPTION);

        // Get all the isoCountryCodeList where countryDescription not equals to UPDATED_COUNTRY_DESCRIPTION
        defaultIsoCountryCodeShouldBeFound("countryDescription.notEquals=" + UPDATED_COUNTRY_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllIsoCountryCodesByCountryDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        // Get all the isoCountryCodeList where countryDescription in DEFAULT_COUNTRY_DESCRIPTION or UPDATED_COUNTRY_DESCRIPTION
        defaultIsoCountryCodeShouldBeFound("countryDescription.in=" + DEFAULT_COUNTRY_DESCRIPTION + "," + UPDATED_COUNTRY_DESCRIPTION);

        // Get all the isoCountryCodeList where countryDescription equals to UPDATED_COUNTRY_DESCRIPTION
        defaultIsoCountryCodeShouldNotBeFound("countryDescription.in=" + UPDATED_COUNTRY_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllIsoCountryCodesByCountryDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        // Get all the isoCountryCodeList where countryDescription is not null
        defaultIsoCountryCodeShouldBeFound("countryDescription.specified=true");

        // Get all the isoCountryCodeList where countryDescription is null
        defaultIsoCountryCodeShouldNotBeFound("countryDescription.specified=false");
    }

    @Test
    @Transactional
    void getAllIsoCountryCodesByCountryDescriptionContainsSomething() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        // Get all the isoCountryCodeList where countryDescription contains DEFAULT_COUNTRY_DESCRIPTION
        defaultIsoCountryCodeShouldBeFound("countryDescription.contains=" + DEFAULT_COUNTRY_DESCRIPTION);

        // Get all the isoCountryCodeList where countryDescription contains UPDATED_COUNTRY_DESCRIPTION
        defaultIsoCountryCodeShouldNotBeFound("countryDescription.contains=" + UPDATED_COUNTRY_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllIsoCountryCodesByCountryDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        // Get all the isoCountryCodeList where countryDescription does not contain DEFAULT_COUNTRY_DESCRIPTION
        defaultIsoCountryCodeShouldNotBeFound("countryDescription.doesNotContain=" + DEFAULT_COUNTRY_DESCRIPTION);

        // Get all the isoCountryCodeList where countryDescription does not contain UPDATED_COUNTRY_DESCRIPTION
        defaultIsoCountryCodeShouldBeFound("countryDescription.doesNotContain=" + UPDATED_COUNTRY_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllIsoCountryCodesByPlaceholderIsEqualToSomething() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);
        Placeholder placeholder;
        if (TestUtil.findAll(em, Placeholder.class).isEmpty()) {
            placeholder = PlaceholderResourceIT.createEntity(em);
            em.persist(placeholder);
            em.flush();
        } else {
            placeholder = TestUtil.findAll(em, Placeholder.class).get(0);
        }
        em.persist(placeholder);
        em.flush();
        isoCountryCode.addPlaceholder(placeholder);
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);
        Long placeholderId = placeholder.getId();

        // Get all the isoCountryCodeList where placeholder equals to placeholderId
        defaultIsoCountryCodeShouldBeFound("placeholderId.equals=" + placeholderId);

        // Get all the isoCountryCodeList where placeholder equals to (placeholderId + 1)
        defaultIsoCountryCodeShouldNotBeFound("placeholderId.equals=" + (placeholderId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultIsoCountryCodeShouldBeFound(String filter) throws Exception {
        restIsoCountryCodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(isoCountryCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].countryDescription").value(hasItem(DEFAULT_COUNTRY_DESCRIPTION)));

        // Check, that the count call also returns 1
        restIsoCountryCodeMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultIsoCountryCodeShouldNotBeFound(String filter) throws Exception {
        restIsoCountryCodeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restIsoCountryCodeMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingIsoCountryCode() throws Exception {
        // Get the isoCountryCode
        restIsoCountryCodeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewIsoCountryCode() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        int databaseSizeBeforeUpdate = isoCountryCodeRepository.findAll().size();

        // Update the isoCountryCode
        IsoCountryCode updatedIsoCountryCode = isoCountryCodeRepository.findById(isoCountryCode.getId()).get();
        // Disconnect from session so that the updates on updatedIsoCountryCode are not directly saved in db
        em.detach(updatedIsoCountryCode);
        updatedIsoCountryCode.countryCode(UPDATED_COUNTRY_CODE).countryDescription(UPDATED_COUNTRY_DESCRIPTION);
        IsoCountryCodeDTO isoCountryCodeDTO = isoCountryCodeMapper.toDto(updatedIsoCountryCode);

        restIsoCountryCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, isoCountryCodeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(isoCountryCodeDTO))
            )
            .andExpect(status().isOk());

        // Validate the IsoCountryCode in the database
        List<IsoCountryCode> isoCountryCodeList = isoCountryCodeRepository.findAll();
        assertThat(isoCountryCodeList).hasSize(databaseSizeBeforeUpdate);
        IsoCountryCode testIsoCountryCode = isoCountryCodeList.get(isoCountryCodeList.size() - 1);
        assertThat(testIsoCountryCode.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testIsoCountryCode.getCountryDescription()).isEqualTo(UPDATED_COUNTRY_DESCRIPTION);

        // Validate the IsoCountryCode in Elasticsearch
        verify(mockIsoCountryCodeSearchRepository).save(testIsoCountryCode);
    }

    @Test
    @Transactional
    void putNonExistingIsoCountryCode() throws Exception {
        int databaseSizeBeforeUpdate = isoCountryCodeRepository.findAll().size();
        isoCountryCode.setId(count.incrementAndGet());

        // Create the IsoCountryCode
        IsoCountryCodeDTO isoCountryCodeDTO = isoCountryCodeMapper.toDto(isoCountryCode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIsoCountryCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, isoCountryCodeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(isoCountryCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IsoCountryCode in the database
        List<IsoCountryCode> isoCountryCodeList = isoCountryCodeRepository.findAll();
        assertThat(isoCountryCodeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the IsoCountryCode in Elasticsearch
        verify(mockIsoCountryCodeSearchRepository, times(0)).save(isoCountryCode);
    }

    @Test
    @Transactional
    void putWithIdMismatchIsoCountryCode() throws Exception {
        int databaseSizeBeforeUpdate = isoCountryCodeRepository.findAll().size();
        isoCountryCode.setId(count.incrementAndGet());

        // Create the IsoCountryCode
        IsoCountryCodeDTO isoCountryCodeDTO = isoCountryCodeMapper.toDto(isoCountryCode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIsoCountryCodeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(isoCountryCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IsoCountryCode in the database
        List<IsoCountryCode> isoCountryCodeList = isoCountryCodeRepository.findAll();
        assertThat(isoCountryCodeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the IsoCountryCode in Elasticsearch
        verify(mockIsoCountryCodeSearchRepository, times(0)).save(isoCountryCode);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamIsoCountryCode() throws Exception {
        int databaseSizeBeforeUpdate = isoCountryCodeRepository.findAll().size();
        isoCountryCode.setId(count.incrementAndGet());

        // Create the IsoCountryCode
        IsoCountryCodeDTO isoCountryCodeDTO = isoCountryCodeMapper.toDto(isoCountryCode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIsoCountryCodeMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(isoCountryCodeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the IsoCountryCode in the database
        List<IsoCountryCode> isoCountryCodeList = isoCountryCodeRepository.findAll();
        assertThat(isoCountryCodeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the IsoCountryCode in Elasticsearch
        verify(mockIsoCountryCodeSearchRepository, times(0)).save(isoCountryCode);
    }

    @Test
    @Transactional
    void partialUpdateIsoCountryCodeWithPatch() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        int databaseSizeBeforeUpdate = isoCountryCodeRepository.findAll().size();

        // Update the isoCountryCode using partial update
        IsoCountryCode partialUpdatedIsoCountryCode = new IsoCountryCode();
        partialUpdatedIsoCountryCode.setId(isoCountryCode.getId());

        partialUpdatedIsoCountryCode.countryCode(UPDATED_COUNTRY_CODE);

        restIsoCountryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIsoCountryCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedIsoCountryCode))
            )
            .andExpect(status().isOk());

        // Validate the IsoCountryCode in the database
        List<IsoCountryCode> isoCountryCodeList = isoCountryCodeRepository.findAll();
        assertThat(isoCountryCodeList).hasSize(databaseSizeBeforeUpdate);
        IsoCountryCode testIsoCountryCode = isoCountryCodeList.get(isoCountryCodeList.size() - 1);
        assertThat(testIsoCountryCode.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testIsoCountryCode.getCountryDescription()).isEqualTo(DEFAULT_COUNTRY_DESCRIPTION);
    }

    @Test
    @Transactional
    void fullUpdateIsoCountryCodeWithPatch() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        int databaseSizeBeforeUpdate = isoCountryCodeRepository.findAll().size();

        // Update the isoCountryCode using partial update
        IsoCountryCode partialUpdatedIsoCountryCode = new IsoCountryCode();
        partialUpdatedIsoCountryCode.setId(isoCountryCode.getId());

        partialUpdatedIsoCountryCode.countryCode(UPDATED_COUNTRY_CODE).countryDescription(UPDATED_COUNTRY_DESCRIPTION);

        restIsoCountryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIsoCountryCode.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedIsoCountryCode))
            )
            .andExpect(status().isOk());

        // Validate the IsoCountryCode in the database
        List<IsoCountryCode> isoCountryCodeList = isoCountryCodeRepository.findAll();
        assertThat(isoCountryCodeList).hasSize(databaseSizeBeforeUpdate);
        IsoCountryCode testIsoCountryCode = isoCountryCodeList.get(isoCountryCodeList.size() - 1);
        assertThat(testIsoCountryCode.getCountryCode()).isEqualTo(UPDATED_COUNTRY_CODE);
        assertThat(testIsoCountryCode.getCountryDescription()).isEqualTo(UPDATED_COUNTRY_DESCRIPTION);
    }

    @Test
    @Transactional
    void patchNonExistingIsoCountryCode() throws Exception {
        int databaseSizeBeforeUpdate = isoCountryCodeRepository.findAll().size();
        isoCountryCode.setId(count.incrementAndGet());

        // Create the IsoCountryCode
        IsoCountryCodeDTO isoCountryCodeDTO = isoCountryCodeMapper.toDto(isoCountryCode);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIsoCountryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, isoCountryCodeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(isoCountryCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IsoCountryCode in the database
        List<IsoCountryCode> isoCountryCodeList = isoCountryCodeRepository.findAll();
        assertThat(isoCountryCodeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the IsoCountryCode in Elasticsearch
        verify(mockIsoCountryCodeSearchRepository, times(0)).save(isoCountryCode);
    }

    @Test
    @Transactional
    void patchWithIdMismatchIsoCountryCode() throws Exception {
        int databaseSizeBeforeUpdate = isoCountryCodeRepository.findAll().size();
        isoCountryCode.setId(count.incrementAndGet());

        // Create the IsoCountryCode
        IsoCountryCodeDTO isoCountryCodeDTO = isoCountryCodeMapper.toDto(isoCountryCode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIsoCountryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(isoCountryCodeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IsoCountryCode in the database
        List<IsoCountryCode> isoCountryCodeList = isoCountryCodeRepository.findAll();
        assertThat(isoCountryCodeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the IsoCountryCode in Elasticsearch
        verify(mockIsoCountryCodeSearchRepository, times(0)).save(isoCountryCode);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamIsoCountryCode() throws Exception {
        int databaseSizeBeforeUpdate = isoCountryCodeRepository.findAll().size();
        isoCountryCode.setId(count.incrementAndGet());

        // Create the IsoCountryCode
        IsoCountryCodeDTO isoCountryCodeDTO = isoCountryCodeMapper.toDto(isoCountryCode);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIsoCountryCodeMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(isoCountryCodeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the IsoCountryCode in the database
        List<IsoCountryCode> isoCountryCodeList = isoCountryCodeRepository.findAll();
        assertThat(isoCountryCodeList).hasSize(databaseSizeBeforeUpdate);

        // Validate the IsoCountryCode in Elasticsearch
        verify(mockIsoCountryCodeSearchRepository, times(0)).save(isoCountryCode);
    }

    @Test
    @Transactional
    void deleteIsoCountryCode() throws Exception {
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);

        int databaseSizeBeforeDelete = isoCountryCodeRepository.findAll().size();

        // Delete the isoCountryCode
        restIsoCountryCodeMockMvc
            .perform(delete(ENTITY_API_URL_ID, isoCountryCode.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<IsoCountryCode> isoCountryCodeList = isoCountryCodeRepository.findAll();
        assertThat(isoCountryCodeList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the IsoCountryCode in Elasticsearch
        verify(mockIsoCountryCodeSearchRepository, times(1)).deleteById(isoCountryCode.getId());
    }

    @Test
    @Transactional
    void searchIsoCountryCode() throws Exception {
        // Configure the mock search repository
        // Initialize the database
        isoCountryCodeRepository.saveAndFlush(isoCountryCode);
        when(mockIsoCountryCodeSearchRepository.search("id:" + isoCountryCode.getId(), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(isoCountryCode), PageRequest.of(0, 1), 1));

        // Search the isoCountryCode
        restIsoCountryCodeMockMvc
            .perform(get(ENTITY_SEARCH_API_URL + "?query=id:" + isoCountryCode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(isoCountryCode.getId().intValue())))
            .andExpect(jsonPath("$.[*].countryCode").value(hasItem(DEFAULT_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].countryDescription").value(hasItem(DEFAULT_COUNTRY_DESCRIPTION)));
    }
}