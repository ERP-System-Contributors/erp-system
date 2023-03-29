package io.github.erp.service.mapper;

/*-
 * Erp System - Mark III No 12 (Caleb Series) Server ver 1.0.1-SNAPSHOT
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

import io.github.erp.domain.OutletType;
import io.github.erp.service.dto.OutletTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OutletType} and its DTO {@link OutletTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = { PlaceholderMapper.class })
public interface OutletTypeMapper extends EntityMapper<OutletTypeDTO, OutletType> {
    @Mapping(target = "placeholders", source = "placeholders", qualifiedByName = "descriptionSet")
    OutletTypeDTO toDto(OutletType s);

    @Mapping(target = "removePlaceholder", ignore = true)
    OutletType toEntity(OutletTypeDTO outletTypeDTO);

    @Named("outletType")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "outletType", source = "outletType")
    OutletTypeDTO toDtoOutletType(OutletType outletType);
}
