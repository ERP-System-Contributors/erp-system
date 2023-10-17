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

import io.github.erp.domain.enumeration.SubmittedFileStatusTypes;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link io.github.erp.domain.CrbFileTransmissionStatus} entity.
 */
public class CrbFileTransmissionStatusDTO implements Serializable {

    private Long id;

    @NotNull
    private String submittedFileStatusTypeCode;

    @NotNull
    private SubmittedFileStatusTypes submittedFileStatusType;

    @Lob
    private String submittedFileStatusTypeDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubmittedFileStatusTypeCode() {
        return submittedFileStatusTypeCode;
    }

    public void setSubmittedFileStatusTypeCode(String submittedFileStatusTypeCode) {
        this.submittedFileStatusTypeCode = submittedFileStatusTypeCode;
    }

    public SubmittedFileStatusTypes getSubmittedFileStatusType() {
        return submittedFileStatusType;
    }

    public void setSubmittedFileStatusType(SubmittedFileStatusTypes submittedFileStatusType) {
        this.submittedFileStatusType = submittedFileStatusType;
    }

    public String getSubmittedFileStatusTypeDescription() {
        return submittedFileStatusTypeDescription;
    }

    public void setSubmittedFileStatusTypeDescription(String submittedFileStatusTypeDescription) {
        this.submittedFileStatusTypeDescription = submittedFileStatusTypeDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CrbFileTransmissionStatusDTO)) {
            return false;
        }

        CrbFileTransmissionStatusDTO crbFileTransmissionStatusDTO = (CrbFileTransmissionStatusDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, crbFileTransmissionStatusDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CrbFileTransmissionStatusDTO{" +
            "id=" + getId() +
            ", submittedFileStatusTypeCode='" + getSubmittedFileStatusTypeCode() + "'" +
            ", submittedFileStatusType='" + getSubmittedFileStatusType() + "'" +
            ", submittedFileStatusTypeDescription='" + getSubmittedFileStatusTypeDescription() + "'" +
            "}";
    }
}
