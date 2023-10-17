package io.github.erp.service;

/*-
 * Erp System - Mark VI No 2 (Phoebe Series) Server ver 1.5.3
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

import io.github.erp.domain.*; // for static metamodels
import io.github.erp.domain.ShareholderType;
import io.github.erp.repository.ShareholderTypeRepository;
import io.github.erp.repository.search.ShareholderTypeSearchRepository;
import io.github.erp.service.criteria.ShareholderTypeCriteria;
import io.github.erp.service.dto.ShareholderTypeDTO;
import io.github.erp.service.mapper.ShareholderTypeMapper;
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
 * Service for executing complex queries for {@link ShareholderType} entities in the database.
 * The main input is a {@link ShareholderTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ShareholderTypeDTO} or a {@link Page} of {@link ShareholderTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ShareholderTypeQueryService extends QueryService<ShareholderType> {

    private final Logger log = LoggerFactory.getLogger(ShareholderTypeQueryService.class);

    private final ShareholderTypeRepository shareholderTypeRepository;

    private final ShareholderTypeMapper shareholderTypeMapper;

    private final ShareholderTypeSearchRepository shareholderTypeSearchRepository;

    public ShareholderTypeQueryService(
        ShareholderTypeRepository shareholderTypeRepository,
        ShareholderTypeMapper shareholderTypeMapper,
        ShareholderTypeSearchRepository shareholderTypeSearchRepository
    ) {
        this.shareholderTypeRepository = shareholderTypeRepository;
        this.shareholderTypeMapper = shareholderTypeMapper;
        this.shareholderTypeSearchRepository = shareholderTypeSearchRepository;
    }

    /**
     * Return a {@link List} of {@link ShareholderTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ShareholderTypeDTO> findByCriteria(ShareholderTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ShareholderType> specification = createSpecification(criteria);
        return shareholderTypeMapper.toDto(shareholderTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ShareholderTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ShareholderTypeDTO> findByCriteria(ShareholderTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ShareholderType> specification = createSpecification(criteria);
        return shareholderTypeRepository.findAll(specification, page).map(shareholderTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ShareholderTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ShareholderType> specification = createSpecification(criteria);
        return shareholderTypeRepository.count(specification);
    }

    /**
     * Function to convert {@link ShareholderTypeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<ShareholderType> createSpecification(ShareholderTypeCriteria criteria) {
        Specification<ShareholderType> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), ShareholderType_.id));
            }
            if (criteria.getShareHolderTypeCode() != null) {
                specification =
                    specification.and(buildStringSpecification(criteria.getShareHolderTypeCode(), ShareholderType_.shareHolderTypeCode));
            }
            if (criteria.getShareHolderType() != null) {
                specification = specification.and(buildSpecification(criteria.getShareHolderType(), ShareholderType_.shareHolderType));
            }
        }
        return specification;
    }
}
