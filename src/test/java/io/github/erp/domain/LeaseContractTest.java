package io.github.erp.domain;

/*-
 * Erp System - Mark III No 11 (Caleb Series) Server ver 0.7.0
 * Copyright © 2021 - 2022 Edwin Njeru (mailnjeru@gmail.com)
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
import static org.assertj.core.api.Assertions.assertThat;

import io.github.erp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LeaseContractTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LeaseContract.class);
        LeaseContract leaseContract1 = new LeaseContract();
        leaseContract1.setId(1L);
        LeaseContract leaseContract2 = new LeaseContract();
        leaseContract2.setId(leaseContract1.getId());
        assertThat(leaseContract1).isEqualTo(leaseContract2);
        leaseContract2.setId(2L);
        assertThat(leaseContract1).isNotEqualTo(leaseContract2);
        leaseContract1.setId(null);
        assertThat(leaseContract1).isNotEqualTo(leaseContract2);
    }
}
