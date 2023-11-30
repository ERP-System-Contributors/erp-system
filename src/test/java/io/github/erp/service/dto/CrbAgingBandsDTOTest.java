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

class CrbAgingBandsDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CrbAgingBandsDTO.class);
        CrbAgingBandsDTO crbAgingBandsDTO1 = new CrbAgingBandsDTO();
        crbAgingBandsDTO1.setId(1L);
        CrbAgingBandsDTO crbAgingBandsDTO2 = new CrbAgingBandsDTO();
        assertThat(crbAgingBandsDTO1).isNotEqualTo(crbAgingBandsDTO2);
        crbAgingBandsDTO2.setId(crbAgingBandsDTO1.getId());
        assertThat(crbAgingBandsDTO1).isEqualTo(crbAgingBandsDTO2);
        crbAgingBandsDTO2.setId(2L);
        assertThat(crbAgingBandsDTO1).isNotEqualTo(crbAgingBandsDTO2);
        crbAgingBandsDTO1.setId(null);
        assertThat(crbAgingBandsDTO1).isNotEqualTo(crbAgingBandsDTO2);
    }
}
