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

class RouAssetNBVReportItemTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RouAssetNBVReportItem.class);
        RouAssetNBVReportItem rouAssetNBVReportItem1 = new RouAssetNBVReportItem();
        rouAssetNBVReportItem1.setId(1L);
        RouAssetNBVReportItem rouAssetNBVReportItem2 = new RouAssetNBVReportItem();
        rouAssetNBVReportItem2.setId(rouAssetNBVReportItem1.getId());
        assertThat(rouAssetNBVReportItem1).isEqualTo(rouAssetNBVReportItem2);
        rouAssetNBVReportItem2.setId(2L);
        assertThat(rouAssetNBVReportItem1).isNotEqualTo(rouAssetNBVReportItem2);
        rouAssetNBVReportItem1.setId(null);
        assertThat(rouAssetNBVReportItem1).isNotEqualTo(rouAssetNBVReportItem2);
    }
}
