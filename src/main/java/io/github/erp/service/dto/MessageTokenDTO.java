package io.github.erp.service.dto;

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
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link io.github.erp.domain.MessageToken} entity.
 */
public class MessageTokenDTO implements Serializable {

    private Long id;

    private String description;

    @NotNull
    private Long timeSent;

    @NotNull
    private String tokenValue;

    private Boolean received;

    private Boolean actioned;

    private Boolean contentFullyEnqueued;

    private Set<PlaceholderDTO> placeholders = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTimeSent() {
        return timeSent;
    }

    public void setTimeSent(Long timeSent) {
        this.timeSent = timeSent;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Boolean getReceived() {
        return received;
    }

    public void setReceived(Boolean received) {
        this.received = received;
    }

    public Boolean getActioned() {
        return actioned;
    }

    public void setActioned(Boolean actioned) {
        this.actioned = actioned;
    }

    public Boolean getContentFullyEnqueued() {
        return contentFullyEnqueued;
    }

    public void setContentFullyEnqueued(Boolean contentFullyEnqueued) {
        this.contentFullyEnqueued = contentFullyEnqueued;
    }

    public Set<PlaceholderDTO> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(Set<PlaceholderDTO> placeholders) {
        this.placeholders = placeholders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MessageTokenDTO)) {
            return false;
        }

        MessageTokenDTO messageTokenDTO = (MessageTokenDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, messageTokenDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MessageTokenDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", timeSent=" + getTimeSent() +
            ", tokenValue='" + getTokenValue() + "'" +
            ", received='" + getReceived() + "'" +
            ", actioned='" + getActioned() + "'" +
            ", contentFullyEnqueued='" + getContentFullyEnqueued() + "'" +
            ", placeholders=" + getPlaceholders() +
            "}";
    }
}
