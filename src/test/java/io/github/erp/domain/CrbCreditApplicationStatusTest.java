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

class CrbCreditApplicationStatusTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CrbCreditApplicationStatus.class);
        CrbCreditApplicationStatus crbCreditApplicationStatus1 = new CrbCreditApplicationStatus();
        crbCreditApplicationStatus1.setId(1L);
        CrbCreditApplicationStatus crbCreditApplicationStatus2 = new CrbCreditApplicationStatus();
        crbCreditApplicationStatus2.setId(crbCreditApplicationStatus1.getId());
        assertThat(crbCreditApplicationStatus1).isEqualTo(crbCreditApplicationStatus2);
        crbCreditApplicationStatus2.setId(2L);
        assertThat(crbCreditApplicationStatus1).isNotEqualTo(crbCreditApplicationStatus2);
        crbCreditApplicationStatus1.setId(null);
        assertThat(crbCreditApplicationStatus1).isNotEqualTo(crbCreditApplicationStatus2);
    }
}
