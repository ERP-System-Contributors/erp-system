package io.github.erp.domain;

/*-
 * Erp System - Mark II No 28 (Baruch Series) Server ver 0.0.8-SNAPSHOT
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

class CustomerIDDocumentTypeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerIDDocumentType.class);
        CustomerIDDocumentType customerIDDocumentType1 = new CustomerIDDocumentType();
        customerIDDocumentType1.setId(1L);
        CustomerIDDocumentType customerIDDocumentType2 = new CustomerIDDocumentType();
        customerIDDocumentType2.setId(customerIDDocumentType1.getId());
        assertThat(customerIDDocumentType1).isEqualTo(customerIDDocumentType2);
        customerIDDocumentType2.setId(2L);
        assertThat(customerIDDocumentType1).isNotEqualTo(customerIDDocumentType2);
        customerIDDocumentType1.setId(null);
        assertThat(customerIDDocumentType1).isNotEqualTo(customerIDDocumentType2);
    }
}
