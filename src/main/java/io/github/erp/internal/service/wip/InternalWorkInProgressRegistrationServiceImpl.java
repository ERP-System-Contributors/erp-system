package io.github.erp.internal.service.wip;

/*-
 * Erp System - Mark X No 10 (Jehoiada Series) Server ver 1.8.2
 * Copyright © 2021 - 2024 Edwin Njeru and the ERP System Contributors (mailnjeru@gmail.com)
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
import io.github.erp.domain.WorkInProgressRegistration;
import io.github.erp.internal.repository.InternalWorkInProgressRegistrationRepository;
import io.github.erp.internal.utilities.NextIntegerFiller;
import io.github.erp.repository.search.WorkInProgressRegistrationSearchRepository;
import io.github.erp.service.SettlementService;
import io.github.erp.service.dto.SettlementDTO;
import io.github.erp.service.dto.WorkInProgressRegistrationDTO;
import io.github.erp.service.mapper.WorkInProgressRegistrationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link WorkInProgressRegistration}.
 */
@Service
@Transactional
public class InternalWorkInProgressRegistrationServiceImpl implements InternalWorkInProgressRegistrationService {

    private final Logger log = LoggerFactory.getLogger(InternalWorkInProgressRegistrationServiceImpl.class);

    private final InternalWorkInProgressRegistrationRepository workInProgressRegistrationRepository;

    private final WorkInProgressRegistrationMapper workInProgressRegistrationMapper;

    private final WorkInProgressRegistrationSearchRepository workInProgressRegistrationSearchRepository;

    private final SettlementService settlementService;

    public InternalWorkInProgressRegistrationServiceImpl(
        InternalWorkInProgressRegistrationRepository workInProgressRegistrationRepository,
        WorkInProgressRegistrationMapper workInProgressRegistrationMapper,
        WorkInProgressRegistrationSearchRepository workInProgressRegistrationSearchRepository,
        SettlementService settlementService) {
        this.workInProgressRegistrationRepository = workInProgressRegistrationRepository;
        this.workInProgressRegistrationMapper = workInProgressRegistrationMapper;
        this.workInProgressRegistrationSearchRepository = workInProgressRegistrationSearchRepository;
        this.settlementService = settlementService;
    }

    @Override
    public WorkInProgressRegistrationDTO save(WorkInProgressRegistrationDTO workInProgressRegistrationDTO) {
        log.debug("Request to save WorkInProgressRegistration : {}", workInProgressRegistrationDTO);

        SettlementDTO settlement = settlementService.findOne(workInProgressRegistrationDTO.getSettlementTransaction().getId()).orElseThrow();

        // Update payment date
        workInProgressRegistrationDTO.setInstalmentDate(settlement.getPaymentDate());
        WorkInProgressRegistration workInProgressRegistration = workInProgressRegistrationMapper.toEntity(workInProgressRegistrationDTO);
        workInProgressRegistration = workInProgressRegistrationRepository.save(workInProgressRegistration);
        WorkInProgressRegistrationDTO result = workInProgressRegistrationMapper.toDto(workInProgressRegistration);
        workInProgressRegistrationSearchRepository.save(workInProgressRegistration);
        return result;
    }

    @Override
    public WorkInProgressRegistrationDTO update(WorkInProgressRegistrationDTO workInProgressRegistrationDTO) {
        log.debug("Request to save WorkInProgressRegistration : {}", workInProgressRegistrationDTO);
        WorkInProgressRegistration workInProgressRegistration = workInProgressRegistrationMapper.toEntity(workInProgressRegistrationDTO);
        workInProgressRegistration = workInProgressRegistrationRepository.save(workInProgressRegistration);

        return workInProgressRegistrationMapper.toDto(workInProgressRegistration);
    }

    @Override
    public Optional<WorkInProgressRegistrationDTO> partialUpdate(WorkInProgressRegistrationDTO workInProgressRegistrationDTO) {
        log.debug("Request to partially update WorkInProgressRegistration : {}", workInProgressRegistrationDTO);

        return workInProgressRegistrationRepository
            .findById(workInProgressRegistrationDTO.getId())
            .map(existingWorkInProgressRegistration -> {
                workInProgressRegistrationMapper.partialUpdate(existingWorkInProgressRegistration, workInProgressRegistrationDTO);

                return existingWorkInProgressRegistration;
            })
            .map(workInProgressRegistrationRepository::save)
            .map(savedWorkInProgressRegistration -> {
                workInProgressRegistrationSearchRepository.save(savedWorkInProgressRegistration);

                return savedWorkInProgressRegistration;
            })
            .map(workInProgressRegistrationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WorkInProgressRegistrationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all WorkInProgressRegistrations");
        return workInProgressRegistrationRepository.findAll(pageable).map(workInProgressRegistrationMapper::toDto);
    }

    public Page<WorkInProgressRegistrationDTO> findAllWithEagerRelationships(Pageable pageable) {
        return workInProgressRegistrationRepository.findAllWithEagerRelationships(pageable).map(workInProgressRegistrationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<WorkInProgressRegistrationDTO> findOne(Long id) {
        log.debug("Request to get WorkInProgressRegistration : {}", id);
        return workInProgressRegistrationRepository.findOneWithEagerRelationships(id).map(workInProgressRegistrationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete WorkInProgressRegistration : {}", id);
        workInProgressRegistrationRepository.deleteById(id);
        workInProgressRegistrationSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<WorkInProgressRegistrationDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of WorkInProgressRegistrations for query {}", query);
        return workInProgressRegistrationSearchRepository.search(query, pageable).map(workInProgressRegistrationMapper::toDto);
    }

    /**
     * Calculate the next number. Typically used for cataloguing the instances of the entity
     *
     * @return the next catalogue number
     */
    @Override
    public Long calculateNextNumber() {
        log.debug("Request to get next asset number");
        return NextIntegerFiller.fillNext(workInProgressRegistrationRepository.findAllIds());
    }
}
