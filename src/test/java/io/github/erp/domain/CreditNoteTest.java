package io.github.erp.domain;

/*-
 * Copyright © 2021 - 2024 Edwin Njeru and the ERP System Contributors (mailnjeru@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import static org.assertj.core.api.Assertions.assertThat;

import io.github.erp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CreditNoteTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CreditNote.class);
        CreditNote creditNote1 = new CreditNote();
        creditNote1.setId(1L);
        CreditNote creditNote2 = new CreditNote();
        creditNote2.setId(creditNote1.getId());
        assertThat(creditNote1).isEqualTo(creditNote2);
        creditNote2.setId(2L);
        assertThat(creditNote1).isNotEqualTo(creditNote2);
        creditNote1.setId(null);
        assertThat(creditNote1).isNotEqualTo(creditNote2);
    }
}
