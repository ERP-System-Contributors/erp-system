
/*-
 * Erp System - Mark X No 10 (Jehoiada Series) Server ver 1.8.2
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
package io.github.erp.internal.service.leases.trxAccounts;

import io.github.erp.internal.repository.LeaseRepaymentTransactionDetailsRepository;
import io.github.erp.internal.repository.ROUAmortizationTransactionDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LeaseRepaymentTransactionDetailsServiceImpl implements LeaseRepaymentTransactionDetailsService {

    private final LeaseRepaymentTransactionDetailsRepository leaseRepaymentTransactionDetailsRepository;

    public LeaseRepaymentTransactionDetailsServiceImpl(LeaseRepaymentTransactionDetailsRepository leaseRepaymentTransactionDetailsRepository) {
        this.leaseRepaymentTransactionDetailsRepository = leaseRepaymentTransactionDetailsRepository;
    }

    @Override
    public void createTransactionDetails(UUID requisitionId, Long postedById){

        leaseRepaymentTransactionDetailsRepository.insertTransactionDetails(requisitionId, postedById, "Lease Repayment");
    }
}