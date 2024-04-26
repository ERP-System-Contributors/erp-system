package io.github.erp.service.impl;

/*-
 * Copyright © 2021 - 2024 Edwin Njeru and the ERP System Contributors (mailnjeru@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import static org.elasticsearch.index.query.QueryBuilders.*;

import io.github.erp.domain.CustomerIDDocumentType;
import io.github.erp.repository.CustomerIDDocumentTypeRepository;
import io.github.erp.repository.search.CustomerIDDocumentTypeSearchRepository;
import io.github.erp.service.CustomerIDDocumentTypeService;
import io.github.erp.service.dto.CustomerIDDocumentTypeDTO;
import io.github.erp.service.mapper.CustomerIDDocumentTypeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link CustomerIDDocumentType}.
 */
@Service
@Transactional
public class CustomerIDDocumentTypeServiceImpl implements CustomerIDDocumentTypeService {

    private final Logger log = LoggerFactory.getLogger(CustomerIDDocumentTypeServiceImpl.class);

    private final CustomerIDDocumentTypeRepository customerIDDocumentTypeRepository;

    private final CustomerIDDocumentTypeMapper customerIDDocumentTypeMapper;

    private final CustomerIDDocumentTypeSearchRepository customerIDDocumentTypeSearchRepository;

    public CustomerIDDocumentTypeServiceImpl(
        CustomerIDDocumentTypeRepository customerIDDocumentTypeRepository,
        CustomerIDDocumentTypeMapper customerIDDocumentTypeMapper,
        CustomerIDDocumentTypeSearchRepository customerIDDocumentTypeSearchRepository
    ) {
        this.customerIDDocumentTypeRepository = customerIDDocumentTypeRepository;
        this.customerIDDocumentTypeMapper = customerIDDocumentTypeMapper;
        this.customerIDDocumentTypeSearchRepository = customerIDDocumentTypeSearchRepository;
    }

    @Override
    public CustomerIDDocumentTypeDTO save(CustomerIDDocumentTypeDTO customerIDDocumentTypeDTO) {
        log.debug("Request to save CustomerIDDocumentType : {}", customerIDDocumentTypeDTO);
        CustomerIDDocumentType customerIDDocumentType = customerIDDocumentTypeMapper.toEntity(customerIDDocumentTypeDTO);
        customerIDDocumentType = customerIDDocumentTypeRepository.save(customerIDDocumentType);
        CustomerIDDocumentTypeDTO result = customerIDDocumentTypeMapper.toDto(customerIDDocumentType);
        customerIDDocumentTypeSearchRepository.save(customerIDDocumentType);
        return result;
    }

    @Override
    public Optional<CustomerIDDocumentTypeDTO> partialUpdate(CustomerIDDocumentTypeDTO customerIDDocumentTypeDTO) {
        log.debug("Request to partially update CustomerIDDocumentType : {}", customerIDDocumentTypeDTO);

        return customerIDDocumentTypeRepository
            .findById(customerIDDocumentTypeDTO.getId())
            .map(existingCustomerIDDocumentType -> {
                customerIDDocumentTypeMapper.partialUpdate(existingCustomerIDDocumentType, customerIDDocumentTypeDTO);

                return existingCustomerIDDocumentType;
            })
            .map(customerIDDocumentTypeRepository::save)
            .map(savedCustomerIDDocumentType -> {
                customerIDDocumentTypeSearchRepository.save(savedCustomerIDDocumentType);

                return savedCustomerIDDocumentType;
            })
            .map(customerIDDocumentTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerIDDocumentTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerIDDocumentTypes");
        return customerIDDocumentTypeRepository.findAll(pageable).map(customerIDDocumentTypeMapper::toDto);
    }

    public Page<CustomerIDDocumentTypeDTO> findAllWithEagerRelationships(Pageable pageable) {
        return customerIDDocumentTypeRepository.findAllWithEagerRelationships(pageable).map(customerIDDocumentTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerIDDocumentTypeDTO> findOne(Long id) {
        log.debug("Request to get CustomerIDDocumentType : {}", id);
        return customerIDDocumentTypeRepository.findOneWithEagerRelationships(id).map(customerIDDocumentTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerIDDocumentType : {}", id);
        customerIDDocumentTypeRepository.deleteById(id);
        customerIDDocumentTypeSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomerIDDocumentTypeDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CustomerIDDocumentTypes for query {}", query);
        return customerIDDocumentTypeSearchRepository.search(query, pageable).map(customerIDDocumentTypeMapper::toDto);
    }
}
