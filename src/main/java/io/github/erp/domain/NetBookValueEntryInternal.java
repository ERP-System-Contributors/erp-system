package io.github.erp.domain;

/*-
 * Erp System - Mark X No 7 (Jehoiada Series) Server ver 1.7.9
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface NetBookValueEntryInternal {

    Long getId();

    String getAssetNumber();

    String getAssetTag();

    String getAssetDescription();

    UUID getNbvIdentifier();

    UUID getCompilationJobIdentifier();

    UUID getCompilationBatchIdentifier();

    Integer getElapsedMonths();

    Integer getPriorMonths();

    Integer getUsefulLifeYears();

    BigDecimal getNetBookValueAmount();

    BigDecimal getPreviousNetBookValueAmount();

    BigDecimal getHistoricalCost();

    LocalDate getCapitalizationDate();

    String getServiceOutlet();

    String getDepreciationPeriod();

    String getFiscalMonth();

    String getDepreciationMethod();

    String getAssetCategory();
}
