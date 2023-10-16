package io.github.erp.service.dto;

/*-
 * Erp System - Mark VI No 2 (Phoebe Series) Server ver 1.5.3
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

class KenyanCurrencyDenominationDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(KenyanCurrencyDenominationDTO.class);
        KenyanCurrencyDenominationDTO kenyanCurrencyDenominationDTO1 = new KenyanCurrencyDenominationDTO();
        kenyanCurrencyDenominationDTO1.setId(1L);
        KenyanCurrencyDenominationDTO kenyanCurrencyDenominationDTO2 = new KenyanCurrencyDenominationDTO();
        assertThat(kenyanCurrencyDenominationDTO1).isNotEqualTo(kenyanCurrencyDenominationDTO2);
        kenyanCurrencyDenominationDTO2.setId(kenyanCurrencyDenominationDTO1.getId());
        assertThat(kenyanCurrencyDenominationDTO1).isEqualTo(kenyanCurrencyDenominationDTO2);
        kenyanCurrencyDenominationDTO2.setId(2L);
        assertThat(kenyanCurrencyDenominationDTO1).isNotEqualTo(kenyanCurrencyDenominationDTO2);
        kenyanCurrencyDenominationDTO1.setId(null);
        assertThat(kenyanCurrencyDenominationDTO1).isNotEqualTo(kenyanCurrencyDenominationDTO2);
    }
}
