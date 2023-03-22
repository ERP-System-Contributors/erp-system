package io.github.erp.repository;

/*-
 * Erp System - Mark III No 12 (Caleb Series) Server ver 0.9.0
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

import io.github.erp.domain.OutletStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the OutletStatus entity.
 */
@Repository
public interface OutletStatusRepository extends JpaRepository<OutletStatus, Long>, JpaSpecificationExecutor<OutletStatus> {
    @Query(
        value = "select distinct outletStatus from OutletStatus outletStatus left join fetch outletStatus.placeholders",
        countQuery = "select count(distinct outletStatus) from OutletStatus outletStatus"
    )
    Page<OutletStatus> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct outletStatus from OutletStatus outletStatus left join fetch outletStatus.placeholders")
    List<OutletStatus> findAllWithEagerRelationships();

    @Query("select outletStatus from OutletStatus outletStatus left join fetch outletStatus.placeholders where outletStatus.id =:id")
    Optional<OutletStatus> findOneWithEagerRelationships(@Param("id") Long id);
}
