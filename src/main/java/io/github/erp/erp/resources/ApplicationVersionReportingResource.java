package io.github.erp.erp.resources;

import io.github.erp.internal.model.ApplicationStatus;
import io.github.erp.service.dto.ApplicationUserDTO;
import lombok.Data;
import lombok.Builder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.jhipster.web.util.ResponseUtil;

import java.util.Optional;

@RestController("ApplicationVersionReportingResource")
@RequestMapping("/api/app")
public class ApplicationVersionReportingResource {

    private static final Logger log = LoggerFactory.getLogger(ApplicationVersionReportingResource.class);

    @Value("${eureka.instance.metadata-map.git-commit}")
    private String build;

    @Value("${eureka.instance.metadata-map.git-version}")
    private String version;

    @Value("${eureka.instance.metadata-map.profile}")
    private String profile;

    @Value("${eureka.instance.metadata-map.git-branch}")
    private String branch;

    /**
     * {@code GET  /application-users/:id} : get the "id" applicationUser.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the applicationUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/application-status")
    public ResponseEntity<ApplicationStatus> getApplicationStatus() {
        log.debug("REST request to get Application Status");
        Optional<ApplicationStatus> applicationUserDTO = Optional.ofNullable(
            ApplicationStatus.builder()
                .build(build)
                .version(version)
                .profile(profile)
                .branch(branch)
                .build());
        return ResponseUtil.wrapOrNotFound(applicationUserDTO);
    }
}
