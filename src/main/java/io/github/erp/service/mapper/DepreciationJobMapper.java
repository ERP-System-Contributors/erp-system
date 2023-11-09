package io.github.erp.service.mapper;

/*-
 * Erp System - Mark VII No 1 (Gideon Series) Server ver 1.5.5
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

import io.github.erp.domain.DepreciationJob;
import io.github.erp.service.dto.DepreciationJobDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DepreciationJob} and its DTO {@link DepreciationJobDTO}.
 */
@Mapper(componentModel = "spring", uses = { ApplicationUserMapper.class, DepreciationPeriodMapper.class })
public interface DepreciationJobMapper extends EntityMapper<DepreciationJobDTO, DepreciationJob> {
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "designation")
    @Mapping(target = "depreciationPeriod", source = "depreciationPeriod", qualifiedByName = "endDate")
    DepreciationJobDTO toDto(DepreciationJob s);

    @Named("id")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DepreciationJobDTO toDtoId(DepreciationJob depreciationJob);
}
