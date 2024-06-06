package io.github.erp.internal.repository;

/*-
 * Erp System - Mark X No 9 (Jehoiada Series) Server ver 1.8.1
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
import io.github.erp.domain.AmortizationPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data SQL repository for the AmortizationPeriod entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InternalAmortizationPeriodRepository
    extends JpaRepository<AmortizationPeriod, Long>, JpaSpecificationExecutor<AmortizationPeriod> {


    /**
     * Retrieve the nth item in the sequence after the current instance given the
     * currentPeriod_id
     *
     * @param currentPeriodId
     * @param nthValue
     * @return
     */
    @Query(
        nativeQuery = true,
        value = "" +
            "SELECT * " +
            "FROM amortization_period " +
            "WHERE sequence_number = ( " +
            "    SELECT sequence_number + :n_value " +
            "    FROM amortization_period " +
            "    WHERE id = :currentPeriodId " +
            ");"
    )
    Optional<AmortizationPeriod> getNextAmortizationPeriod(@Param("currentPeriodId") long currentPeriodId, @Param("n_value") long nthValue);
}
