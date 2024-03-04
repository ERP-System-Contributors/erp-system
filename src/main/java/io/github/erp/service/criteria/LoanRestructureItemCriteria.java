package io.github.erp.service.criteria;

/*-
 * Erp System - Mark X No 4 (Jehoiada Series) Server ver 1.7.4
 * Copyright © 2021 - 2024 Edwin Njeru and the ERP System Contributors (mailnjeru@gmail.com)
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
 * Criteria class for the {@link io.github.erp.domain.LoanRestructureItem} entity. This class is used
 * in {@link io.github.erp.web.rest.LoanRestructureItemResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /loan-restructure-items?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class LoanRestructureItemCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter loanRestructureItemCode;

    private StringFilter loanRestructureItemType;

    private Boolean distinct;

    public LoanRestructureItemCriteria() {}

    public LoanRestructureItemCriteria(LoanRestructureItemCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.loanRestructureItemCode = other.loanRestructureItemCode == null ? null : other.loanRestructureItemCode.copy();
        this.loanRestructureItemType = other.loanRestructureItemType == null ? null : other.loanRestructureItemType.copy();
        this.distinct = other.distinct;
    }

    @Override
    public LoanRestructureItemCriteria copy() {
        return new LoanRestructureItemCriteria(this);
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

    public StringFilter getLoanRestructureItemCode() {
        return loanRestructureItemCode;
    }

    public StringFilter loanRestructureItemCode() {
        if (loanRestructureItemCode == null) {
            loanRestructureItemCode = new StringFilter();
        }
        return loanRestructureItemCode;
    }

    public void setLoanRestructureItemCode(StringFilter loanRestructureItemCode) {
        this.loanRestructureItemCode = loanRestructureItemCode;
    }

    public StringFilter getLoanRestructureItemType() {
        return loanRestructureItemType;
    }

    public StringFilter loanRestructureItemType() {
        if (loanRestructureItemType == null) {
            loanRestructureItemType = new StringFilter();
        }
        return loanRestructureItemType;
    }

    public void setLoanRestructureItemType(StringFilter loanRestructureItemType) {
        this.loanRestructureItemType = loanRestructureItemType;
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
        final LoanRestructureItemCriteria that = (LoanRestructureItemCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(loanRestructureItemCode, that.loanRestructureItemCode) &&
            Objects.equals(loanRestructureItemType, that.loanRestructureItemType) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loanRestructureItemCode, loanRestructureItemType, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LoanRestructureItemCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (loanRestructureItemCode != null ? "loanRestructureItemCode=" + loanRestructureItemCode + ", " : "") +
            (loanRestructureItemType != null ? "loanRestructureItemType=" + loanRestructureItemType + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
