package io.github.erp.service;

import io.github.erp.service.dto.PaymentRequisitionDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link io.github.erp.domain.PaymentRequisition}.
 */
public interface PaymentRequisitionService {
    /**
     * Save a paymentRequisition.
     *
     * @param paymentRequisitionDTO the entity to save.
     * @return the persisted entity.
     */
    PaymentRequisitionDTO save(PaymentRequisitionDTO paymentRequisitionDTO);

    /**
     * Partially updates a paymentRequisition.
     *
     * @param paymentRequisitionDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PaymentRequisitionDTO> partialUpdate(PaymentRequisitionDTO paymentRequisitionDTO);

    /**
     * Get all the paymentRequisitions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PaymentRequisitionDTO> findAll(Pageable pageable);

    /**
     * Get all the paymentRequisitions with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PaymentRequisitionDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" paymentRequisition.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PaymentRequisitionDTO> findOne(Long id);

    /**
     * Delete the "id" paymentRequisition.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the paymentRequisition corresponding to the query.
     *
     * @param query the query of the search.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PaymentRequisitionDTO> search(String query, Pageable pageable);
}
