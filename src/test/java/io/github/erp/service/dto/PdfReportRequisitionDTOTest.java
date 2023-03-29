package io.github.erp.service.dto;

/*-
 * Erp System - Mark III No 12 (Caleb Series) Server ver 1.0.2-SNAPSHOT
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

class PdfReportRequisitionDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PdfReportRequisitionDTO.class);
        PdfReportRequisitionDTO pdfReportRequisitionDTO1 = new PdfReportRequisitionDTO();
        pdfReportRequisitionDTO1.setId(1L);
        PdfReportRequisitionDTO pdfReportRequisitionDTO2 = new PdfReportRequisitionDTO();
        assertThat(pdfReportRequisitionDTO1).isNotEqualTo(pdfReportRequisitionDTO2);
        pdfReportRequisitionDTO2.setId(pdfReportRequisitionDTO1.getId());
        assertThat(pdfReportRequisitionDTO1).isEqualTo(pdfReportRequisitionDTO2);
        pdfReportRequisitionDTO2.setId(2L);
        assertThat(pdfReportRequisitionDTO1).isNotEqualTo(pdfReportRequisitionDTO2);
        pdfReportRequisitionDTO1.setId(null);
        assertThat(pdfReportRequisitionDTO1).isNotEqualTo(pdfReportRequisitionDTO2);
    }
}
