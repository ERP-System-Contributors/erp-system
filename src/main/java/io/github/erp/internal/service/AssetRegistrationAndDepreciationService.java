package io.github.erp.internal.service;

import io.github.erp.service.dto.AssetRegistrationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AssetRegistrationAndDepreciationService {

    /**
     * Save a assetRegistration.
     *
     * @param assetRegistrationDTO the entity to save.
     * @return the persisted entity.
     */
    AssetRegistrationDTO save(AssetRegistrationDTO assetRegistrationDTO);

    /**
     * Partially updates a assetRegistration.
     *
     * @param assetRegistrationDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AssetRegistrationDTO> partialUpdate(AssetRegistrationDTO assetRegistrationDTO);

    /**
     * Get all the assetRegistrations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AssetRegistrationDTO> findAll(Pageable pageable);

    /**
     * Get all the assetRegistrations with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AssetRegistrationDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" assetRegistration.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AssetRegistrationDTO> findOne(Long id);

    /**
     * Delete the "id" assetRegistration.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the assetRegistration corresponding to the query.
     *
     * @param query the query of the search.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AssetRegistrationDTO> search(String query, Pageable pageable);

    /**
     * Returns a list of assets acquired by a particular date, normally for purposes of depreciation
     *
     * @param capitalizationDate End date which is the cut off for new assets included in the list
     * @return List of assetIds
     */
    List<Long> getAssetIdsByCapitalizationDateBefore(LocalDate capitalizationDate);

    /**
     * Initial Cost of the assets being depreciated
     * @param capitalizationDate End date by which the newly acquired assets are included in the depreciation scope
     * @return The summation of asset costs of assets within the depreciation scope defined by capitalization date
     */
    BigDecimal getInitialAssetCostByCapitalizationDateBefore(LocalDate capitalizationDate);
}
