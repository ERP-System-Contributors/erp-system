package io.github.erp.service.dto;

/*-
 * Erp System - Mark VII No 4 (Gideon Series) Server ver 1.5.8
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

class DerivativeUnderlyingAssetDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DerivativeUnderlyingAssetDTO.class);
        DerivativeUnderlyingAssetDTO derivativeUnderlyingAssetDTO1 = new DerivativeUnderlyingAssetDTO();
        derivativeUnderlyingAssetDTO1.setId(1L);
        DerivativeUnderlyingAssetDTO derivativeUnderlyingAssetDTO2 = new DerivativeUnderlyingAssetDTO();
        assertThat(derivativeUnderlyingAssetDTO1).isNotEqualTo(derivativeUnderlyingAssetDTO2);
        derivativeUnderlyingAssetDTO2.setId(derivativeUnderlyingAssetDTO1.getId());
        assertThat(derivativeUnderlyingAssetDTO1).isEqualTo(derivativeUnderlyingAssetDTO2);
        derivativeUnderlyingAssetDTO2.setId(2L);
        assertThat(derivativeUnderlyingAssetDTO1).isNotEqualTo(derivativeUnderlyingAssetDTO2);
        derivativeUnderlyingAssetDTO1.setId(null);
        assertThat(derivativeUnderlyingAssetDTO1).isNotEqualTo(derivativeUnderlyingAssetDTO2);
    }
}
