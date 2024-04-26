package io.github.erp.service.mapper;

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
import io.github.erp.domain.PrepaymentMarshalling;
import io.github.erp.service.dto.PrepaymentMarshallingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PrepaymentMarshalling} and its DTO {@link PrepaymentMarshallingDTO}.
 */
@Mapper(componentModel = "spring", uses = { PrepaymentAccountMapper.class, PlaceholderMapper.class, FiscalMonthMapper.class })
public interface PrepaymentMarshallingMapper extends EntityMapper<PrepaymentMarshallingDTO, PrepaymentMarshalling> {
    @Mapping(target = "prepaymentAccount", source = "prepaymentAccount", qualifiedByName = "catalogueNumber")
    @Mapping(target = "placeholders", source = "placeholders", qualifiedByName = "descriptionSet")
    @Mapping(target = "firstFiscalMonth", source = "firstFiscalMonth", qualifiedByName = "startDate")
    @Mapping(target = "lastFiscalMonth", source = "lastFiscalMonth", qualifiedByName = "endDate")
    PrepaymentMarshallingDTO toDto(PrepaymentMarshalling s);

    @Mapping(target = "removePlaceholder", ignore = true)
    PrepaymentMarshalling toEntity(PrepaymentMarshallingDTO prepaymentMarshallingDTO);
}
