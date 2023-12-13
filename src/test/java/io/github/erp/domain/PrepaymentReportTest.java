package io.github.erp.domain;

/*-
 * Erp System - Mark IX No 1 (Iddo Series) Server ver 1.6.3
 * Copyright © 2021 - 2023 Edwin Njeru and the ERP System Contributors (mailnjeru@gmail.com)
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

class PrepaymentReportTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrepaymentReport.class);
        PrepaymentReport prepaymentReport1 = new PrepaymentReport();
        prepaymentReport1.setId(1L);
        PrepaymentReport prepaymentReport2 = new PrepaymentReport();
        prepaymentReport2.setId(prepaymentReport1.getId());
        assertThat(prepaymentReport1).isEqualTo(prepaymentReport2);
        prepaymentReport2.setId(2L);
        assertThat(prepaymentReport1).isNotEqualTo(prepaymentReport2);
        prepaymentReport1.setId(null);
        assertThat(prepaymentReport1).isNotEqualTo(prepaymentReport2);
    }
}