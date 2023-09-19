package io.github.erp.service;

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

import io.github.erp.service.dto.InstitutionContactDetailsDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link io.github.erp.domain.InstitutionContactDetails}.
 */
public interface InstitutionContactDetailsService {
    /**
     * Save a institutionContactDetails.
     *
     * @param institutionContactDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    InstitutionContactDetailsDTO save(InstitutionContactDetailsDTO institutionContactDetailsDTO);

    /**
     * Partially updates a institutionContactDetails.
     *
     * @param institutionContactDetailsDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<InstitutionContactDetailsDTO> partialUpdate(InstitutionContactDetailsDTO institutionContactDetailsDTO);

    /**
     * Get all the institutionContactDetails.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InstitutionContactDetailsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" institutionContactDetails.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InstitutionContactDetailsDTO> findOne(Long id);

    /**
     * Delete the "id" institutionContactDetails.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the institutionContactDetails corresponding to the query.
     *
     * @param query the query of the search.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InstitutionContactDetailsDTO> search(String query, Pageable pageable);
}