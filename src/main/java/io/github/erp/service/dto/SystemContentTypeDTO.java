package io.github.erp.service.dto;

import io.github.erp.domain.enumeration.SystemContentTypeAvailability;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link io.github.erp.domain.SystemContentType} entity.
 */
public class SystemContentTypeDTO implements Serializable {

    private Long id;

    @NotNull
    private String contentTypeName;

    @NotNull
    private String contentTypeHeader;

    @Lob
    private String comments;

    @NotNull
    private SystemContentTypeAvailability availability;

    private Set<PlaceholderDTO> placeholders = new HashSet<>();

    private Set<UniversallyUniqueMappingDTO> sysMaps = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentTypeName() {
        return contentTypeName;
    }

    public void setContentTypeName(String contentTypeName) {
        this.contentTypeName = contentTypeName;
    }

    public String getContentTypeHeader() {
        return contentTypeHeader;
    }

    public void setContentTypeHeader(String contentTypeHeader) {
        this.contentTypeHeader = contentTypeHeader;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public SystemContentTypeAvailability getAvailability() {
        return availability;
    }

    public void setAvailability(SystemContentTypeAvailability availability) {
        this.availability = availability;
    }

    public Set<PlaceholderDTO> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(Set<PlaceholderDTO> placeholders) {
        this.placeholders = placeholders;
    }

    public Set<UniversallyUniqueMappingDTO> getSysMaps() {
        return sysMaps;
    }

    public void setSysMaps(Set<UniversallyUniqueMappingDTO> sysMaps) {
        this.sysMaps = sysMaps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SystemContentTypeDTO)) {
            return false;
        }

        SystemContentTypeDTO systemContentTypeDTO = (SystemContentTypeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, systemContentTypeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SystemContentTypeDTO{" +
            "id=" + getId() +
            ", contentTypeName='" + getContentTypeName() + "'" +
            ", contentTypeHeader='" + getContentTypeHeader() + "'" +
            ", comments='" + getComments() + "'" +
            ", availability='" + getAvailability() + "'" +
            ", placeholders=" + getPlaceholders() +
            ", sysMaps=" + getSysMaps() +
            "}";
    }
}
