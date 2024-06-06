package io.github.erp.service.mapper;

/*-
 * Erp System - Mark X No 9 (Jehoiada Series) Server ver 1.8.1
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
import io.github.erp.domain.RouDepreciationPostingReport;
import io.github.erp.service.dto.RouDepreciationPostingReportDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RouDepreciationPostingReport} and its DTO {@link RouDepreciationPostingReportDTO}.
 */
@Mapper(componentModel = "spring", uses = { ApplicationUserMapper.class, LeasePeriodMapper.class })
public interface RouDepreciationPostingReportMapper extends EntityMapper<RouDepreciationPostingReportDTO, RouDepreciationPostingReport> {
    @Mapping(target = "requestedBy", source = "requestedBy", qualifiedByName = "applicationIdentity")
    @Mapping(target = "leasePeriod", source = "leasePeriod", qualifiedByName = "periodCode")
    RouDepreciationPostingReportDTO toDto(RouDepreciationPostingReport s);
}
