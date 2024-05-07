package io.github.erp.internal.repository;

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
import io.github.erp.domain.PrepaymentAmortization;
import io.github.erp.domain.PrepaymentCompilationRequest;
import io.github.erp.repository.PrepaymentAmortizationRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface InternalPrepaymentAmortizationRepository extends
    PrepaymentAmortizationRepository,
    JpaRepository<PrepaymentAmortization, Long>,
    JpaSpecificationExecutor<PrepaymentAmortization> {

    List<PrepaymentAmortization> findAllByPrepaymentCompilationRequest(@NotNull PrepaymentCompilationRequest prepaymentCompilationRequest);
}
