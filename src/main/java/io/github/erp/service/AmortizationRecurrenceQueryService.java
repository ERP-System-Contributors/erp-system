package io.github.erp.service;

import io.github.erp.domain.*; // for static metamodels
import io.github.erp.domain.AmortizationRecurrence;
import io.github.erp.repository.AmortizationRecurrenceRepository;
import io.github.erp.repository.search.AmortizationRecurrenceSearchRepository;
import io.github.erp.service.criteria.AmortizationRecurrenceCriteria;
import io.github.erp.service.dto.AmortizationRecurrenceDTO;
import io.github.erp.service.mapper.AmortizationRecurrenceMapper;
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
 * Service for executing complex queries for {@link AmortizationRecurrence} entities in the database.
 * The main input is a {@link AmortizationRecurrenceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AmortizationRecurrenceDTO} or a {@link Page} of {@link AmortizationRecurrenceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AmortizationRecurrenceQueryService extends QueryService<AmortizationRecurrence> {

    private final Logger log = LoggerFactory.getLogger(AmortizationRecurrenceQueryService.class);

    private final AmortizationRecurrenceRepository amortizationRecurrenceRepository;

    private final AmortizationRecurrenceMapper amortizationRecurrenceMapper;

    private final AmortizationRecurrenceSearchRepository amortizationRecurrenceSearchRepository;

    public AmortizationRecurrenceQueryService(
        AmortizationRecurrenceRepository amortizationRecurrenceRepository,
        AmortizationRecurrenceMapper amortizationRecurrenceMapper,
        AmortizationRecurrenceSearchRepository amortizationRecurrenceSearchRepository
    ) {
        this.amortizationRecurrenceRepository = amortizationRecurrenceRepository;
        this.amortizationRecurrenceMapper = amortizationRecurrenceMapper;
        this.amortizationRecurrenceSearchRepository = amortizationRecurrenceSearchRepository;
    }

    /**
     * Return a {@link List} of {@link AmortizationRecurrenceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AmortizationRecurrenceDTO> findByCriteria(AmortizationRecurrenceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AmortizationRecurrence> specification = createSpecification(criteria);
        return amortizationRecurrenceMapper.toDto(amortizationRecurrenceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AmortizationRecurrenceDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AmortizationRecurrenceDTO> findByCriteria(AmortizationRecurrenceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AmortizationRecurrence> specification = createSpecification(criteria);
        return amortizationRecurrenceRepository.findAll(specification, page).map(amortizationRecurrenceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AmortizationRecurrenceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AmortizationRecurrence> specification = createSpecification(criteria);
        return amortizationRecurrenceRepository.count(specification);
    }

    /**
     * Function to convert {@link AmortizationRecurrenceCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<AmortizationRecurrence> createSpecification(AmortizationRecurrenceCriteria criteria) {
        Specification<AmortizationRecurrence> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), AmortizationRecurrence_.id));
            }
            if (criteria.getFirstAmortizationDate() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getFirstAmortizationDate(), AmortizationRecurrence_.firstAmortizationDate)
                    );
            }
            if (criteria.getAmortizationFrequency() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getAmortizationFrequency(), AmortizationRecurrence_.amortizationFrequency)
                    );
            }
            if (criteria.getNumberOfRecurrences() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getNumberOfRecurrences(), AmortizationRecurrence_.numberOfRecurrences)
                    );
            }
            if (criteria.getParticulars() != null) {
                specification = specification.and(buildStringSpecification(criteria.getParticulars(), AmortizationRecurrence_.particulars));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), AmortizationRecurrence_.isActive));
            }
            if (criteria.getIsOverWritten() != null) {
                specification = specification.and(buildSpecification(criteria.getIsOverWritten(), AmortizationRecurrence_.isOverWritten));
            }
            if (criteria.getTimeOfInstallation() != null) {
                specification =
                    specification.and(
                        buildRangeSpecification(criteria.getTimeOfInstallation(), AmortizationRecurrence_.timeOfInstallation)
                    );
            }
            if (criteria.getRecurrenceGuid() != null) {
                specification = specification.and(buildSpecification(criteria.getRecurrenceGuid(), AmortizationRecurrence_.recurrenceGuid));
            }
            if (criteria.getPrepaymentAccountGuid() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getPrepaymentAccountGuid(), AmortizationRecurrence_.prepaymentAccountGuid)
                    );
            }
            if (criteria.getPlaceholderId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getPlaceholderId(),
                            root -> root.join(AmortizationRecurrence_.placeholders, JoinType.LEFT).get(Placeholder_.id)
                        )
                    );
            }
            if (criteria.getParametersId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getParametersId(),
                            root -> root.join(AmortizationRecurrence_.parameters, JoinType.LEFT).get(PrepaymentMapping_.id)
                        )
                    );
            }
            if (criteria.getApplicationParametersId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getApplicationParametersId(),
                            root ->
                                root.join(AmortizationRecurrence_.applicationParameters, JoinType.LEFT).get(UniversallyUniqueMapping_.id)
                        )
                    );
            }
            if (criteria.getDepreciationMethodId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getDepreciationMethodId(),
                            root -> root.join(AmortizationRecurrence_.depreciationMethod, JoinType.LEFT).get(DepreciationMethod_.id)
                        )
                    );
            }
            if (criteria.getPrepaymentAccountId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getPrepaymentAccountId(),
                            root -> root.join(AmortizationRecurrence_.prepaymentAccount, JoinType.LEFT).get(PrepaymentAccount_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
