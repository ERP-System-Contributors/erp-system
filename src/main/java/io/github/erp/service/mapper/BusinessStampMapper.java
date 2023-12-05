package io.github.erp.service.mapper;

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
import io.github.erp.domain.BusinessStamp;
import io.github.erp.service.dto.BusinessStampDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BusinessStamp} and its DTO {@link BusinessStampDTO}.
 */
@Mapper(componentModel = "spring", uses = { DealerMapper.class, PlaceholderMapper.class })
public interface BusinessStampMapper extends EntityMapper<BusinessStampDTO, BusinessStamp> {
    @Mapping(target = "stampHolder", source = "stampHolder", qualifiedByName = "dealerName")
    @Mapping(target = "placeholders", source = "placeholders", qualifiedByName = "descriptionSet")
    BusinessStampDTO toDto(BusinessStamp s);

    @Mapping(target = "removePlaceholder", ignore = true)
    BusinessStamp toEntity(BusinessStampDTO businessStampDTO);

    @Named("detailsSet")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "details", source = "details")
    Set<BusinessStampDTO> toDtoDetailsSet(Set<BusinessStamp> businessStamp);
}
