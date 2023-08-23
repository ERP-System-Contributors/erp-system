package io.github.erp.service.mapper;

/*-
 * Erp System - Mark IV No 5 (Ehud Series) Server ver 1.3.6
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
import io.github.erp.domain.DepreciationJobNotice;
import io.github.erp.service.dto.DepreciationJobNoticeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DepreciationJobNotice} and its DTO {@link DepreciationJobNoticeDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = {
        DepreciationJobMapper.class,
        DepreciationBatchSequenceMapper.class,
        DepreciationPeriodMapper.class,
        PlaceholderMapper.class,
        UniversallyUniqueMappingMapper.class,
        ApplicationUserMapper.class,
    }
)
public interface DepreciationJobNoticeMapper extends EntityMapper<DepreciationJobNoticeDTO, DepreciationJobNotice> {
    @Mapping(target = "depreciationJob", source = "depreciationJob", qualifiedByName = "id")
    @Mapping(target = "depreciationBatchSequence", source = "depreciationBatchSequence", qualifiedByName = "id")
    @Mapping(target = "depreciationPeriod", source = "depreciationPeriod", qualifiedByName = "id")
    @Mapping(target = "placeholders", source = "placeholders", qualifiedByName = "descriptionSet")
    @Mapping(target = "universallyUniqueMappings", source = "universallyUniqueMappings", qualifiedByName = "universalKeySet")
    @Mapping(target = "superintended", source = "superintended", qualifiedByName = "applicationIdentity")
    DepreciationJobNoticeDTO toDto(DepreciationJobNotice s);

    @Mapping(target = "removePlaceholder", ignore = true)
    @Mapping(target = "removeUniversallyUniqueMapping", ignore = true)
    DepreciationJobNotice toEntity(DepreciationJobNoticeDTO depreciationJobNoticeDTO);
}
