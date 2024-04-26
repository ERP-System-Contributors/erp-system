package io.github.erp.erp.assets.depreciation;

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
import io.github.erp.domain.enumeration.DepreciationJobStatusType;
import io.github.erp.erp.assets.depreciation.model.DepreciationBatchMessage;
import io.github.erp.service.DepreciationJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DepreciationJobCompleteCallbackImpl implements DepreciationJobCompleteCallback {

    private final static Logger log = LoggerFactory.getLogger(DepreciationJobCompleteCallbackImpl.class);

    private final DepreciationJobService depreciationJobService;

    public DepreciationJobCompleteCallbackImpl(DepreciationJobService depreciationJobService) {
        this.depreciationJobService = depreciationJobService;
    }

    /**
     * Called when process is complete
     *
     * @param message
     */
    @Override
    public void onComplete(DepreciationBatchMessage message) {

        depreciationJobService.findOne(Long.valueOf(message.getJobId())).ifPresent(depreciationJob -> {
            if (depreciationJob.getDepreciationJobStatus() != DepreciationJobStatusType.COMPLETE) {
                depreciationJob.setDepreciationJobStatus(DepreciationJobStatusType.COMPLETE);
                depreciationJobService.save(depreciationJob);

                log.info("The depreciation job id: {} {}, has been completed", depreciationJob.getId(), depreciationJob.getDescription());
            }
        });
    }
}
