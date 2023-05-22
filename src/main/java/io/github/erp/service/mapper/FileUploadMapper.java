package io.github.erp.service.mapper;

import io.github.erp.domain.FileUpload;
import io.github.erp.service.dto.FileUploadDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FileUpload} and its DTO {@link FileUploadDTO}.
 */
@Mapper(componentModel = "spring", uses = { PlaceholderMapper.class })
public interface FileUploadMapper extends EntityMapper<FileUploadDTO, FileUpload> {
    @Mapping(target = "placeholders", source = "placeholders", qualifiedByName = "descriptionSet")
    FileUploadDTO toDto(FileUpload s);

    @Mapping(target = "removePlaceholder", ignore = true)
    FileUpload toEntity(FileUploadDTO fileUploadDTO);
}
