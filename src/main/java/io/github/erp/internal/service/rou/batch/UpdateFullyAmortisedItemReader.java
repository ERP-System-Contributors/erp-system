package io.github.erp.internal.service.rou.batch;

import io.github.erp.internal.service.rou.InternalRouModelMetadataService;
import io.github.erp.service.dto.RouModelMetadataDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class UpdateFullyAmortisedItemReader implements ItemReader<RouModelMetadataDTO> {

    private final InternalRouModelMetadataService internalRouModelMetadataService;

    private List<RouModelMetadataDTO> metadataList;
    private Iterator<RouModelMetadataDTO> metadataIterator;
    private final UUID batchJobIdentifier;


    public UpdateFullyAmortisedItemReader(InternalRouModelMetadataService internalRouModelMetadataService, UUID batchJobIdentifier) {
        this.internalRouModelMetadataService = internalRouModelMetadataService;
        this.batchJobIdentifier = batchJobIdentifier;
    }

    /**
     * Reads a piece of input data and advance to the next one. Implementations
     * <strong>must</strong> return <code>null</code> at the end of the input
     * data set. In a transactional setting, caller might get the same item
     * twice from successive calls (or otherwise), if the first call was in a
     * transaction that rolled back.
     *
     * @return T the item to be processed or {@code null} if the data source is
     * exhausted
     * @throws ParseException                if there is a problem parsing the current record
     *                                       (but the next one may still be valid)
     * @throws NonTransientResourceException if there is a fatal exception in
     *                                       the underlying resource. After throwing this exception implementations
     *                                       should endeavour to return null from subsequent calls to read.
     * @throws UnexpectedInputException      if there is an uncategorised problem
     *                                       with the input data. Assume potentially transient, so subsequent calls to
     *                                       read might succeed.
     * @throws Exception                     if an there is a non-specific error.
     */
    @Override
    public RouModelMetadataDTO read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (metadataList == null) {
            metadataList = internalRouModelMetadataService.getProcessedItems(batchJobIdentifier).orElse(Collections.emptyList());
            metadataIterator = metadataList.iterator();
        }

        return metadataIterator.hasNext() ? metadataIterator.next() : null;
    }
}
