package io.github.erp.service.dto;

/*-
 * Erp System - Mark III No 12 (Caleb Series) Server ver 0.9.0
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

class AlgorithmDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AlgorithmDTO.class);
        AlgorithmDTO algorithmDTO1 = new AlgorithmDTO();
        algorithmDTO1.setId(1L);
        AlgorithmDTO algorithmDTO2 = new AlgorithmDTO();
        assertThat(algorithmDTO1).isNotEqualTo(algorithmDTO2);
        algorithmDTO2.setId(algorithmDTO1.getId());
        assertThat(algorithmDTO1).isEqualTo(algorithmDTO2);
        algorithmDTO2.setId(2L);
        assertThat(algorithmDTO1).isNotEqualTo(algorithmDTO2);
        algorithmDTO1.setId(null);
        assertThat(algorithmDTO1).isNotEqualTo(algorithmDTO2);
    }
}
