package io.github.erp.internal.framework.batch;

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
import com.google.common.collect.Lists;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for handling bits as sub-lists and for batches should have a job-scope
 * The object is initialized once for every list that is being processed and should exist
 * the entire time the list if still in progress. If a new one is initialized it will cause
 * trouble
 */
public class ListPartition<T> {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ListPartition.class);

    private final int dataSize;

    private final int listPageSize;
    private final List<T> unProcessedItems;

    private int listPageNumber;
    private int processedDataSize;

    public ListPartition(final int listPageSize, final List<T> unProcessedItems) {
        this.listPageSize = listPageSize;
        this.unProcessedItems = unProcessedItems;
        this.dataSize = unProcessedItems.size();
        this.listPageNumber = -1;
        this.processedDataSize = 0;
    }



    public List<T> getPartition() {

        List<T> forProcessing = new ArrayList<>();

        if (dataSize > processedDataSize) {

            listPageNumber++;

            log.info("Getting page number : {}", listPageNumber +1);

            try {
                // todo replace forProcessing = ImmutableList.copyOf(Lists.partition(unProcessedItems, listPageSize).get(listPageNumber));
                forProcessing = List.copyOf(Lists.partition(unProcessedItems, listPageSize).get(listPageNumber));
            } catch (Exception e) {
                // ? DO NOTHING?
                // An error is quietly thrown, making the forProcessing list null
                String errorMessage = "An error occurred while processing page # " + listPageNumber +
                    " There remains " + (dataSize-processedDataSize) + " pending for processing, " +
                    " and " + processedDataSize + " items have been processed so far. " +
                    " Don't worry; life is hard. Check trace for further details, before killing the messenger.";

                throw new RuntimeException(errorMessage, e);
            }

            processedDataSize = processedDataSize + forProcessing.size();

            log.info("{} items enqueued for processing", processedDataSize);
        } else {

            // todo replace forProcessing = ImmutableList.of();
            forProcessing = List.of();
        }
        return forProcessing;
    }
}
