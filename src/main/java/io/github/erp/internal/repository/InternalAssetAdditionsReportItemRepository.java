package io.github.erp.internal.repository;

/*-
 * Erp System - Mark X No 2 (Jehoiada Series) Server ver 1.7.1
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

import io.github.erp.domain.AssetAdditionsReportItem;
import io.github.erp.domain.AssetAdditionsReportItemInternal;
import io.github.erp.repository.AssetAdditionsReportItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Spring Data SQL repository for the AssetAdditionsReportItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InternalAssetAdditionsReportItemRepository
    extends AssetAdditionsReportItemRepository,
    JpaRepository<AssetAdditionsReportItem, Long>,
    JpaSpecificationExecutor<AssetAdditionsReportItem> {

    Page<AssetAdditionsReportItemInternal> getAssetAdditionsReportItemByReportDates(
        @Param("reportPeriodStartDate") LocalDate reportPeriodStartDate,
        @Param("reportPeriodEndDate") LocalDate reportPeriodEndDate,
        Pageable pageable);
}
