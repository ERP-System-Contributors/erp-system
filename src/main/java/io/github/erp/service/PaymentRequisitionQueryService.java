package io.github.erp.service;

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
import io.github.erp.domain.*; // for static metamodels
import io.github.erp.domain.PaymentRequisition;
import io.github.erp.repository.PaymentRequisitionRepository;
import io.github.erp.repository.search.PaymentRequisitionSearchRepository;
import io.github.erp.service.criteria.PaymentRequisitionCriteria;
import io.github.erp.service.dto.PaymentRequisitionDTO;
import io.github.erp.service.mapper.PaymentRequisitionMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link PaymentRequisition} entities in the database.
 * The main input is a {@link PaymentRequisitionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PaymentRequisitionDTO} or a {@link Page} of {@link PaymentRequisitionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PaymentRequisitionQueryService extends QueryService<PaymentRequisition> {

    private final Logger log = LoggerFactory.getLogger(PaymentRequisitionQueryService.class);

    private final PaymentRequisitionRepository paymentRequisitionRepository;

    private final PaymentRequisitionMapper paymentRequisitionMapper;

    private final PaymentRequisitionSearchRepository paymentRequisitionSearchRepository;

    public PaymentRequisitionQueryService(
        PaymentRequisitionRepository paymentRequisitionRepository,
        PaymentRequisitionMapper paymentRequisitionMapper,
        PaymentRequisitionSearchRepository paymentRequisitionSearchRepository
    ) {
        this.paymentRequisitionRepository = paymentRequisitionRepository;
        this.paymentRequisitionMapper = paymentRequisitionMapper;
        this.paymentRequisitionSearchRepository = paymentRequisitionSearchRepository;
    }

    /**
     * Return a {@link List} of {@link PaymentRequisitionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PaymentRequisitionDTO> findByCriteria(PaymentRequisitionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PaymentRequisition> specification = createSpecification(criteria);
        return paymentRequisitionMapper.toDto(paymentRequisitionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PaymentRequisitionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PaymentRequisitionDTO> findByCriteria(PaymentRequisitionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PaymentRequisition> specification = createSpecification(criteria);
        return paymentRequisitionRepository.findAll(specification, page).map(paymentRequisitionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PaymentRequisitionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PaymentRequisition> specification = createSpecification(criteria);
        return paymentRequisitionRepository.count(specification);
    }

    /**
     * Function to convert {@link PaymentRequisitionCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<PaymentRequisition> createSpecification(PaymentRequisitionCriteria criteria) {
        Specification<PaymentRequisition> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), PaymentRequisition_.id));
            }
            if (criteria.getReceptionDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReceptionDate(), PaymentRequisition_.receptionDate));
            }
            if (criteria.getDealerName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDealerName(), PaymentRequisition_.dealerName));
            }
            if (criteria.getBriefDescription() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getBriefDescription(), PaymentRequisition_.briefDescription));
            }
            if (criteria.getRequisitionNumber() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getRequisitionNumber(), PaymentRequisition_.requisitionNumber));
            }
            if (criteria.getInvoicedAmount() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getInvoicedAmount(), PaymentRequisition_.invoicedAmount));
            }
            if (criteria.getDisbursementCost() != null) {
                specification =
                    specification.and(buildRangeSpecification(criteria.getDisbursementCost(), PaymentRequisition_.disbursementCost));
            }
            if (criteria.getTaxableAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTaxableAmount(), PaymentRequisition_.taxableAmount));
            }
            if (criteria.getRequisitionProcessed() != null) {
                specification =
                    specification.and(buildSpecification(criteria.getRequisitionProcessed(), PaymentRequisition_.requisitionProcessed));
            }
            if (criteria.getFileUploadToken() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getFileUploadToken(), PaymentRequisition_.fileUploadToken));
            }
            if (criteria.getCompilationToken() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getCompilationToken(), PaymentRequisition_.compilationToken));
            }
            if (criteria.getPaymentLabelId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getPaymentLabelId(),
                            root -> root.join(PaymentRequisition_.paymentLabels, JoinType.LEFT).get(PaymentLabel_.id)
                        )
                    );
            }
            if (criteria.getPlaceholderId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getPlaceholderId(),
                            root -> root.join(PaymentRequisition_.placeholders, JoinType.LEFT).get(Placeholder_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
