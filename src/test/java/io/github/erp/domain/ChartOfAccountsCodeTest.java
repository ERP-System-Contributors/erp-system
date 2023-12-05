package io.github.erp.domain;

/*-
 * Erp System - Mark VIII No 3 (Hilkiah Series) Server ver 1.6.2
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

class ChartOfAccountsCodeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChartOfAccountsCode.class);
        ChartOfAccountsCode chartOfAccountsCode1 = new ChartOfAccountsCode();
        chartOfAccountsCode1.setId(1L);
        ChartOfAccountsCode chartOfAccountsCode2 = new ChartOfAccountsCode();
        chartOfAccountsCode2.setId(chartOfAccountsCode1.getId());
        assertThat(chartOfAccountsCode1).isEqualTo(chartOfAccountsCode2);
        chartOfAccountsCode2.setId(2L);
        assertThat(chartOfAccountsCode1).isNotEqualTo(chartOfAccountsCode2);
        chartOfAccountsCode1.setId(null);
        assertThat(chartOfAccountsCode1).isNotEqualTo(chartOfAccountsCode2);
    }
}
