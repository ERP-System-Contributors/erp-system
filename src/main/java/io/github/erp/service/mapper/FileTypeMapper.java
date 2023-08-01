package io.github.erp.service.mapper;

import io.github.erp.domain.FileType;
import io.github.erp.service.dto.FileTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FileType} and its DTO {@link FileTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = { PlaceholderMapper.class })
public interface FileTypeMapper extends EntityMapper<FileTypeDTO, FileType> {
    @Mapping(target = "placeholders", source = "placeholders", qualifiedByName = "descriptionSet")
    FileTypeDTO toDto(FileType s);

    @Mapping(target = "removePlaceholder", ignore = true)
    FileType toEntity(FileTypeDTO fileTypeDTO);
}
