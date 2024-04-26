package io.github.erp.service.criteria;

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
import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link io.github.erp.domain.ProfessionalQualification} entity. This class is used
 * in {@link io.github.erp.web.rest.ProfessionalQualificationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /professional-qualifications?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProfessionalQualificationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter professionalQualificationsCode;

    private StringFilter professionalQualificationsType;

    private Boolean distinct;

    public ProfessionalQualificationCriteria() {}

    public ProfessionalQualificationCriteria(ProfessionalQualificationCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.professionalQualificationsCode =
            other.professionalQualificationsCode == null ? null : other.professionalQualificationsCode.copy();
        this.professionalQualificationsType =
            other.professionalQualificationsType == null ? null : other.professionalQualificationsType.copy();
        this.distinct = other.distinct;
    }

    @Override
    public ProfessionalQualificationCriteria copy() {
        return new ProfessionalQualificationCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getProfessionalQualificationsCode() {
        return professionalQualificationsCode;
    }

    public StringFilter professionalQualificationsCode() {
        if (professionalQualificationsCode == null) {
            professionalQualificationsCode = new StringFilter();
        }
        return professionalQualificationsCode;
    }

    public void setProfessionalQualificationsCode(StringFilter professionalQualificationsCode) {
        this.professionalQualificationsCode = professionalQualificationsCode;
    }

    public StringFilter getProfessionalQualificationsType() {
        return professionalQualificationsType;
    }

    public StringFilter professionalQualificationsType() {
        if (professionalQualificationsType == null) {
            professionalQualificationsType = new StringFilter();
        }
        return professionalQualificationsType;
    }

    public void setProfessionalQualificationsType(StringFilter professionalQualificationsType) {
        this.professionalQualificationsType = professionalQualificationsType;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProfessionalQualificationCriteria that = (ProfessionalQualificationCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(professionalQualificationsCode, that.professionalQualificationsCode) &&
            Objects.equals(professionalQualificationsType, that.professionalQualificationsType) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, professionalQualificationsCode, professionalQualificationsType, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfessionalQualificationCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (professionalQualificationsCode != null ? "professionalQualificationsCode=" + professionalQualificationsCode + ", " : "") +
            (professionalQualificationsType != null ? "professionalQualificationsType=" + professionalQualificationsType + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
