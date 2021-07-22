package io.github.erp.service.impl;

import io.github.erp.service.TaxRuleService;
import io.github.erp.domain.TaxRule;
import io.github.erp.repository.TaxRuleRepository;
import io.github.erp.repository.search.TaxRuleSearchRepository;
import io.github.erp.service.dto.TaxRuleDTO;
import io.github.erp.service.mapper.TaxRuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link TaxRule}.
 */
@Service
@Transactional
public class TaxRuleServiceImpl implements TaxRuleService {

    private final Logger log = LoggerFactory.getLogger(TaxRuleServiceImpl.class);

    private final TaxRuleRepository taxRuleRepository;

    private final TaxRuleMapper taxRuleMapper;

    private final TaxRuleSearchRepository taxRuleSearchRepository;

    public TaxRuleServiceImpl(TaxRuleRepository taxRuleRepository, TaxRuleMapper taxRuleMapper, TaxRuleSearchRepository taxRuleSearchRepository) {
        this.taxRuleRepository = taxRuleRepository;
        this.taxRuleMapper = taxRuleMapper;
        this.taxRuleSearchRepository = taxRuleSearchRepository;
    }

    @Override
    public TaxRuleDTO save(TaxRuleDTO taxRuleDTO) {
        log.debug("Request to save TaxRule : {}", taxRuleDTO);
        TaxRule taxRule = taxRuleMapper.toEntity(taxRuleDTO);
        taxRule = taxRuleRepository.save(taxRule);
        TaxRuleDTO result = taxRuleMapper.toDto(taxRule);
        taxRuleSearchRepository.save(taxRule);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaxRuleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TaxRules");
        return taxRuleRepository.findAll(pageable)
            .map(taxRuleMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TaxRuleDTO> findOne(Long id) {
        log.debug("Request to get TaxRule : {}", id);
        return taxRuleRepository.findById(id)
            .map(taxRuleMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaxRule : {}", id);
        taxRuleRepository.deleteById(id);
        taxRuleSearchRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaxRuleDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TaxRules for query {}", query);
        return taxRuleSearchRepository.search(queryStringQuery(query), pageable)
            .map(taxRuleMapper::toDto);
    }
}
