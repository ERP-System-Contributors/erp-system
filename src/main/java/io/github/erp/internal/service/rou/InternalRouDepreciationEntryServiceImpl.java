package io.github.erp.internal.service.rou;

/*-
 * Erp System - Mark X No 8 (Jehoiada Series) Server ver 1.8.0
 * Copyright © 2021 - 2024 Edwin Njeru and the ERP System Contributors (mailnjeru@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

import io.github.erp.domain.RouDepreciationEntry;
import io.github.erp.repository.RouDepreciationEntryRepository;
import io.github.erp.repository.search.RouDepreciationEntrySearchRepository;
import io.github.erp.service.RouDepreciationEntryService;
import io.github.erp.service.dto.RouDepreciationEntryDTO;
import io.github.erp.service.mapper.RouDepreciationEntryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link RouDepreciationEntry}.
 */
@Service
@Transactional
public class InternalRouDepreciationEntryServiceImpl implements InternalRouDepreciationEntryService {

    private final Logger log = LoggerFactory.getLogger(InternalRouDepreciationEntryServiceImpl.class);

    private final RouDepreciationEntryRepository rouDepreciationEntryRepository;

    private final RouDepreciationEntryMapper rouDepreciationEntryMapper;

    private final RouDepreciationEntrySearchRepository rouDepreciationEntrySearchRepository;

    public InternalRouDepreciationEntryServiceImpl(
        RouDepreciationEntryRepository rouDepreciationEntryRepository,
        RouDepreciationEntryMapper rouDepreciationEntryMapper,
        RouDepreciationEntrySearchRepository rouDepreciationEntrySearchRepository
    ) {
        this.rouDepreciationEntryRepository = rouDepreciationEntryRepository;
        this.rouDepreciationEntryMapper = rouDepreciationEntryMapper;
        this.rouDepreciationEntrySearchRepository = rouDepreciationEntrySearchRepository;
    }

    @Override
    public RouDepreciationEntryDTO save(RouDepreciationEntryDTO rouDepreciationEntryDTO) {
        log.debug("Request to save RouDepreciationEntry : {}", rouDepreciationEntryDTO);
        RouDepreciationEntry rouDepreciationEntry = rouDepreciationEntryMapper.toEntity(rouDepreciationEntryDTO);
        rouDepreciationEntry = rouDepreciationEntryRepository.save(rouDepreciationEntry);
        RouDepreciationEntryDTO result = rouDepreciationEntryMapper.toDto(rouDepreciationEntry);
        rouDepreciationEntrySearchRepository.save(rouDepreciationEntry);
        return result;
    }

    @Override
    public Optional<RouDepreciationEntryDTO> partialUpdate(RouDepreciationEntryDTO rouDepreciationEntryDTO) {
        log.debug("Request to partially update RouDepreciationEntry : {}", rouDepreciationEntryDTO);

        return rouDepreciationEntryRepository
            .findById(rouDepreciationEntryDTO.getId())
            .map(existingRouDepreciationEntry -> {
                rouDepreciationEntryMapper.partialUpdate(existingRouDepreciationEntry, rouDepreciationEntryDTO);

                return existingRouDepreciationEntry;
            })
            .map(rouDepreciationEntryRepository::save)
            .map(savedRouDepreciationEntry -> {
                rouDepreciationEntrySearchRepository.save(savedRouDepreciationEntry);

                return savedRouDepreciationEntry;
            })
            .map(rouDepreciationEntryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RouDepreciationEntryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RouDepreciationEntries");
        return rouDepreciationEntryRepository.findAll(pageable).map(rouDepreciationEntryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RouDepreciationEntryDTO> findOne(Long id) {
        log.debug("Request to get RouDepreciationEntry : {}", id);
        return rouDepreciationEntryRepository.findById(id).map(rouDepreciationEntryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete RouDepreciationEntry : {}", id);
        rouDepreciationEntryRepository.deleteById(id);
        rouDepreciationEntrySearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RouDepreciationEntryDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of RouDepreciationEntries for query {}", query);
        return rouDepreciationEntrySearchRepository.search(query, pageable).map(rouDepreciationEntryMapper::toDto);
    }

    /**
     * Save a rouDepreciationEntry.
     *
     * @param rouDepreciationEntryDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public List<RouDepreciationEntryDTO> save(List<RouDepreciationEntryDTO> rouDepreciationEntryDTO) {

        List<RouDepreciationEntry> rouDepreciationEntry = rouDepreciationEntryMapper.toEntity(rouDepreciationEntryDTO);


        rouDepreciationEntry = rouDepreciationEntryRepository.saveAllAndFlush(rouDepreciationEntry);

        List<RouDepreciationEntryDTO> result = rouDepreciationEntryMapper.toDto(rouDepreciationEntry);

        rouDepreciationEntrySearchRepository.saveAll(rouDepreciationEntry);

        return result;

    }
}
