package io.github.erp.service.dto;

/*-
 * Erp System - Mark VIII No 3 (Hilkiah Series) Server ver 1.6.2
 * Copyright © 2021 - 2023 Edwin Njeru and the ERP System Contributors (mailnjeru@gmail.com)
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
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link io.github.erp.domain.CategoryOfSecurity} entity.
 */
public class CategoryOfSecurityDTO implements Serializable {

    private Long id;

    @NotNull
    private String categoryOfSecurity;

    @NotNull
    private String categoryOfSecurityDetails;

    @Lob
    private String categoryOfSecurityDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryOfSecurity() {
        return categoryOfSecurity;
    }

    public void setCategoryOfSecurity(String categoryOfSecurity) {
        this.categoryOfSecurity = categoryOfSecurity;
    }

    public String getCategoryOfSecurityDetails() {
        return categoryOfSecurityDetails;
    }

    public void setCategoryOfSecurityDetails(String categoryOfSecurityDetails) {
        this.categoryOfSecurityDetails = categoryOfSecurityDetails;
    }

    public String getCategoryOfSecurityDescription() {
        return categoryOfSecurityDescription;
    }

    public void setCategoryOfSecurityDescription(String categoryOfSecurityDescription) {
        this.categoryOfSecurityDescription = categoryOfSecurityDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryOfSecurityDTO)) {
            return false;
        }

        CategoryOfSecurityDTO categoryOfSecurityDTO = (CategoryOfSecurityDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, categoryOfSecurityDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryOfSecurityDTO{" +
            "id=" + getId() +
            ", categoryOfSecurity='" + getCategoryOfSecurity() + "'" +
            ", categoryOfSecurityDetails='" + getCategoryOfSecurityDetails() + "'" +
            ", categoryOfSecurityDescription='" + getCategoryOfSecurityDescription() + "'" +
            "}";
    }
}
