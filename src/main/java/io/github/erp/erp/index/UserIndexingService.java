package io.github.erp.erp.index;

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

import io.github.erp.erp.index.engine_v1.AbstractStartupRegisteredIndexService;
import io.github.erp.erp.index.engine_v1.IndexingServiceChainSingleton;
import io.github.erp.internal.IndexProperties;
import io.github.erp.internal.service.applicationUser.InternalSystemUserService;
import io.github.erp.repository.search.UserSearchRepository;
import io.github.erp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Transactional
public class UserIndexingService extends AbstractStartupRegisteredIndexService {


    private static final String TAG = "AlgorithmIndex";
    private static final Logger log = LoggerFactory.getLogger(TAG);

    private final UserService userService;
    private final InternalSystemUserService internalSystemUserService;
    private final UserSearchRepository searchRepository;

    public UserIndexingService(
        IndexProperties indexProperties,
        UserService userService, InternalSystemUserService internalSystemUserService,
        UserSearchRepository searchRepository
    ) {
        super(indexProperties, indexProperties.getRebuild());
        this.userService = userService;
        this.internalSystemUserService = internalSystemUserService;
        this.searchRepository = searchRepository;
    }

    /**
     * This method is called to register a service which is to respond to the callback
     */
    @Override
    public void register() {

        log.info("Registering {} Service", TAG);

        IndexingServiceChainSingleton.getInstance().registerService(this);
    }

    private static final Lock reindexLock = new ReentrantLock();

    @Async
    public void index() {
        try {
            reindexLock.lockInterruptibly();

            indexerSequence();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            reindexLock.unlock();
        }
    }

    private void indexerSequence() {
        log.info("Initiating {} build sequence", TAG);
        long startup = System.currentTimeMillis();

        internalSystemUserService.findAll().ifPresent(entities ->
            entities.forEach(user ->
                userService.getUserWithAuthoritiesByLogin(user.getLogin()).ifPresent(this.searchRepository::save)
            )
        );

        log.trace("{} initiated and ready for queries. Index build has taken {} milliseconds", TAG, System.currentTimeMillis() - startup);
    }

    @Override
    public void tearDown() {

        if (reindexLock.tryLock()) {
            this.searchRepository.deleteAll();
        } else {
            log.trace("{} ReIndexer: Concurrent reindexing attempt", TAG);
        }
    }
}