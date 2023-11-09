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

import io.github.erp.domain.FiscalYear;
import io.github.erp.service.dto.FiscalYearDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FiscalYear} and its DTO {@link FiscalYearDTO}.
 */
@Mapper(componentModel = "spring", uses = { PlaceholderMapper.class, UniversallyUniqueMappingMapper.class, ApplicationUserMapper.class })
public interface FiscalYearMapper extends EntityMapper<FiscalYearDTO, FiscalYear> {
    @Mapping(target = "placeholders", source = "placeholders", qualifiedByName = "descriptionSet")
    @Mapping(target = "universallyUniqueMappings", source = "universallyUniqueMappings", qualifiedByName = "universalKeySet")
    @Mapping(target = "createdBy", source = "createdBy", qualifiedByName = "applicationIdentity")
    @Mapping(target = "lastUpdatedBy", source = "lastUpdatedBy", qualifiedByName = "applicationIdentity")
    FiscalYearDTO toDto(FiscalYear s);

    @Mapping(target = "removePlaceholder", ignore = true)
    @Mapping(target = "removeUniversallyUniqueMapping", ignore = true)
    FiscalYear toEntity(FiscalYearDTO fiscalYearDTO);

    @Named("fiscalYearCode")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "fiscalYearCode", source = "fiscalYearCode")
    FiscalYearDTO toDtoFiscalYearCode(FiscalYear fiscalYear);
}
