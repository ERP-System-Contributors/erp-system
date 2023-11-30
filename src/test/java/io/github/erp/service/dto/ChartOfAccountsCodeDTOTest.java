package io.github.erp.service.dto;

/*-
 * Erp System - Mark VIII No 2 (Hilkiah Series) Server ver 1.6.1
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

import static org.assertj.core.api.Assertions.assertThat;

import io.github.erp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ChartOfAccountsCodeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChartOfAccountsCodeDTO.class);
        ChartOfAccountsCodeDTO chartOfAccountsCodeDTO1 = new ChartOfAccountsCodeDTO();
        chartOfAccountsCodeDTO1.setId(1L);
        ChartOfAccountsCodeDTO chartOfAccountsCodeDTO2 = new ChartOfAccountsCodeDTO();
        assertThat(chartOfAccountsCodeDTO1).isNotEqualTo(chartOfAccountsCodeDTO2);
        chartOfAccountsCodeDTO2.setId(chartOfAccountsCodeDTO1.getId());
        assertThat(chartOfAccountsCodeDTO1).isEqualTo(chartOfAccountsCodeDTO2);
        chartOfAccountsCodeDTO2.setId(2L);
        assertThat(chartOfAccountsCodeDTO1).isNotEqualTo(chartOfAccountsCodeDTO2);
        chartOfAccountsCodeDTO1.setId(null);
        assertThat(chartOfAccountsCodeDTO1).isNotEqualTo(chartOfAccountsCodeDTO2);
    }
}
