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
import io.github.erp.domain.OutletStatus;
import io.github.erp.repository.OutletStatusRepository;
import io.github.erp.repository.search.OutletStatusSearchRepository;
import io.github.erp.service.criteria.OutletStatusCriteria;
import io.github.erp.service.dto.OutletStatusDTO;
import io.github.erp.service.mapper.OutletStatusMapper;
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
 * Service for executing complex queries for {@link OutletStatus} entities in the database.
 * The main input is a {@link OutletStatusCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OutletStatusDTO} or a {@link Page} of {@link OutletStatusDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OutletStatusQueryService extends QueryService<OutletStatus> {

    private final Logger log = LoggerFactory.getLogger(OutletStatusQueryService.class);

    private final OutletStatusRepository outletStatusRepository;

    private final OutletStatusMapper outletStatusMapper;

    private final OutletStatusSearchRepository outletStatusSearchRepository;

    public OutletStatusQueryService(
        OutletStatusRepository outletStatusRepository,
        OutletStatusMapper outletStatusMapper,
        OutletStatusSearchRepository outletStatusSearchRepository
    ) {
        this.outletStatusRepository = outletStatusRepository;
        this.outletStatusMapper = outletStatusMapper;
        this.outletStatusSearchRepository = outletStatusSearchRepository;
    }

    /**
     * Return a {@link List} of {@link OutletStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OutletStatusDTO> findByCriteria(OutletStatusCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OutletStatus> specification = createSpecification(criteria);
        return outletStatusMapper.toDto(outletStatusRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OutletStatusDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OutletStatusDTO> findByCriteria(OutletStatusCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OutletStatus> specification = createSpecification(criteria);
        return outletStatusRepository.findAll(specification, page).map(outletStatusMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OutletStatusCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OutletStatus> specification = createSpecification(criteria);
        return outletStatusRepository.count(specification);
    }

    /**
     * Function to convert {@link OutletStatusCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<OutletStatus> createSpecification(OutletStatusCriteria criteria) {
        Specification<OutletStatus> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), OutletStatus_.id));
            }
            if (criteria.getBranchStatusTypeCode() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getBranchStatusTypeCode(), OutletStatus_.branchStatusTypeCode));
            }
            if (criteria.getBranchStatusType() != null) {
                specification = specification.and(buildSpecification(criteria.getBranchStatusType(), OutletStatus_.branchStatusType));
            }
            if (criteria.getBranchStatusTypeDescription() != null) {
                specification =
                    specification.and(
                        buildStringSpecification(criteria.getBranchStatusTypeDescription(), OutletStatus_.branchStatusTypeDescription)
                    );
            }
            if (criteria.getPlaceholderId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getPlaceholderId(),
                            root -> root.join(OutletStatus_.placeholders, JoinType.LEFT).get(Placeholder_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
