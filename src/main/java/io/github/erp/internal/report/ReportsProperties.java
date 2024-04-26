package io.github.erp.internal.report;

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
import io.github.erp.config.AppPropertyFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * This configuration gives us the exact location where we can dump the jasper templates and
 * compiled reports
 */
@Configuration
@ConfigurationProperties(prefix = "erp")
@PropertySource(value = "classpath:config/erpConfigs.yml", factory = AppPropertyFactory.class)
public class ReportsProperties {

    private String reportsDirectory;

    private String businessDocsDirectory;

    private String reportPassword;

    public String getReportPassword() {
        return reportPassword;
    }

    public void setReportPassword(String reportPassword) {
        this.reportPassword = reportPassword;
    }

    public String getReportsDirectory() {
        return reportsDirectory;
    }

    public void setReportsDirectory(String reportsDirectory) {
        this.reportsDirectory = reportsDirectory;
    }

    public String getBusinessDocsDirectory() {
        return businessDocsDirectory;
    }

    public void setBusinessDocsDirectory(String businessDocsDirectory) {
        this.businessDocsDirectory = businessDocsDirectory;
    }
}
