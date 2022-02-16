package io.github.erp.web.rest.api;

import io.github.erp.repository.FixedAssetDepreciationRepository;
import io.github.erp.service.FixedAssetDepreciationQueryService;
import io.github.erp.service.FixedAssetDepreciationService;
import io.github.erp.service.criteria.FixedAssetDepreciationCriteria;
import io.github.erp.service.dto.FixedAssetDepreciationDTO;
import io.github.erp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link io.github.erp.domain.FixedAssetDepreciation}.
 */
@RestController("DevFixedAssetDepreciationResource")
@RequestMapping("/api/dev")
public class FixedAssetDepreciationResource {

    private final Logger log = LoggerFactory.getLogger(FixedAssetDepreciationResource.class);

    private static final String ENTITY_NAME = "assetsFixedAssetDepreciation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FixedAssetDepreciationService fixedAssetDepreciationService;

    private final FixedAssetDepreciationRepository fixedAssetDepreciationRepository;

    private final FixedAssetDepreciationQueryService fixedAssetDepreciationQueryService;

    public FixedAssetDepreciationResource(
        FixedAssetDepreciationService fixedAssetDepreciationService,
        FixedAssetDepreciationRepository fixedAssetDepreciationRepository,
        FixedAssetDepreciationQueryService fixedAssetDepreciationQueryService
    ) {
        this.fixedAssetDepreciationService = fixedAssetDepreciationService;
        this.fixedAssetDepreciationRepository = fixedAssetDepreciationRepository;
        this.fixedAssetDepreciationQueryService = fixedAssetDepreciationQueryService;
    }

    /**
     * {@code POST  /fixed-asset-depreciations} : Create a new fixedAssetDepreciation.
     *
     * @param fixedAssetDepreciationDTO the fixedAssetDepreciationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fixedAssetDepreciationDTO, or with status {@code 400 (Bad Request)} if the fixedAssetDepreciation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fixed-asset-depreciations")
    public ResponseEntity<FixedAssetDepreciationDTO> createFixedAssetDepreciation(
        @RequestBody FixedAssetDepreciationDTO fixedAssetDepreciationDTO
    ) throws URISyntaxException {
        log.debug("REST request to save FixedAssetDepreciation : {}", fixedAssetDepreciationDTO);
        if (fixedAssetDepreciationDTO.getId() != null) {
            throw new BadRequestAlertException("A new fixedAssetDepreciation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FixedAssetDepreciationDTO result = fixedAssetDepreciationService.save(fixedAssetDepreciationDTO);
        return ResponseEntity
            .created(new URI("/api/fixed-asset-depreciations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fixed-asset-depreciations/:id} : Updates an existing fixedAssetDepreciation.
     *
     * @param id the id of the fixedAssetDepreciationDTO to save.
     * @param fixedAssetDepreciationDTO the fixedAssetDepreciationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fixedAssetDepreciationDTO,
     * or with status {@code 400 (Bad Request)} if the fixedAssetDepreciationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fixedAssetDepreciationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fixed-asset-depreciations/{id}")
    public ResponseEntity<FixedAssetDepreciationDTO> updateFixedAssetDepreciation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FixedAssetDepreciationDTO fixedAssetDepreciationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update FixedAssetDepreciation : {}, {}", id, fixedAssetDepreciationDTO);
        if (fixedAssetDepreciationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fixedAssetDepreciationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fixedAssetDepreciationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FixedAssetDepreciationDTO result = fixedAssetDepreciationService.save(fixedAssetDepreciationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fixedAssetDepreciationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /fixed-asset-depreciations/:id} : Partial updates given fields of an existing fixedAssetDepreciation, field will ignore if it is null
     *
     * @param id the id of the fixedAssetDepreciationDTO to save.
     * @param fixedAssetDepreciationDTO the fixedAssetDepreciationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fixedAssetDepreciationDTO,
     * or with status {@code 400 (Bad Request)} if the fixedAssetDepreciationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the fixedAssetDepreciationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the fixedAssetDepreciationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/fixed-asset-depreciations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FixedAssetDepreciationDTO> partialUpdateFixedAssetDepreciation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody FixedAssetDepreciationDTO fixedAssetDepreciationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update FixedAssetDepreciation partially : {}, {}", id, fixedAssetDepreciationDTO);
        if (fixedAssetDepreciationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fixedAssetDepreciationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fixedAssetDepreciationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FixedAssetDepreciationDTO> result = fixedAssetDepreciationService.partialUpdate(fixedAssetDepreciationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fixedAssetDepreciationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /fixed-asset-depreciations} : get all the fixedAssetDepreciations.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fixedAssetDepreciations in body.
     */
    @GetMapping("/fixed-asset-depreciations")
    public ResponseEntity<List<FixedAssetDepreciationDTO>> getAllFixedAssetDepreciations(
        FixedAssetDepreciationCriteria criteria,
        Pageable pageable
    ) {
        log.debug("REST request to get FixedAssetDepreciations by criteria: {}", criteria);
        Page<FixedAssetDepreciationDTO> page = fixedAssetDepreciationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /fixed-asset-depreciations/count} : count all the fixedAssetDepreciations.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/fixed-asset-depreciations/count")
    public ResponseEntity<Long> countFixedAssetDepreciations(FixedAssetDepreciationCriteria criteria) {
        log.debug("REST request to count FixedAssetDepreciations by criteria: {}", criteria);
        return ResponseEntity.ok().body(fixedAssetDepreciationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /fixed-asset-depreciations/:id} : get the "id" fixedAssetDepreciation.
     *
     * @param id the id of the fixedAssetDepreciationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fixedAssetDepreciationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fixed-asset-depreciations/{id}")
    public ResponseEntity<FixedAssetDepreciationDTO> getFixedAssetDepreciation(@PathVariable Long id) {
        log.debug("REST request to get FixedAssetDepreciation : {}", id);
        Optional<FixedAssetDepreciationDTO> fixedAssetDepreciationDTO = fixedAssetDepreciationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fixedAssetDepreciationDTO);
    }

    /**
     * {@code DELETE  /fixed-asset-depreciations/:id} : delete the "id" fixedAssetDepreciation.
     *
     * @param id the id of the fixedAssetDepreciationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fixed-asset-depreciations/{id}")
    public ResponseEntity<Void> deleteFixedAssetDepreciation(@PathVariable Long id) {
        log.debug("REST request to delete FixedAssetDepreciation : {}", id);
        fixedAssetDepreciationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    /**
     * {@code SEARCH  /_search/fixed-asset-depreciations?query=:query} : search for the fixedAssetDepreciation corresponding
     * to the query.
     *
     * @param query the query of the fixedAssetDepreciation search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/fixed-asset-depreciations")
    public ResponseEntity<List<FixedAssetDepreciationDTO>> searchFixedAssetDepreciations(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of FixedAssetDepreciations for query {}", query);
        Page<FixedAssetDepreciationDTO> page = fixedAssetDepreciationService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}