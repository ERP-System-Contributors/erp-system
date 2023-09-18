package io.github.erp.erp.depreciation.calculation;

/*-
 * Erp System - Mark V No 8 (Ehud Series) Server ver 1.5.1
 * Copyright © 2021 - 2023 Edwin Njeru (mailnjeru@gmail.com)
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
import io.github.erp.domain.enumeration.DepreciationTypes;
import io.github.erp.service.dto.AssetCategoryDTO;
import io.github.erp.service.dto.AssetRegistrationDTO;
import io.github.erp.service.dto.DepreciationMethodDTO;
import io.github.erp.service.dto.DepreciationPeriodDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Executes calculation for each individual asset
 */
@Service("depreciationCalculatorService")
public class DepreciationCalculatorService implements CalculatesDepreciation {

    private final ReducingBalanceDepreciationCalculator reducingBalanceDepreciationCalculator;
    private final StraightLineDepreciationCalculator straightLineDepreciationCalculator;

    public DepreciationCalculatorService(ReducingBalanceDepreciationCalculator reducingBalanceDepreciationCalculator, StraightLineDepreciationCalculator straightLineDepreciationCalculator) {
        this.reducingBalanceDepreciationCalculator = reducingBalanceDepreciationCalculator;
        this.straightLineDepreciationCalculator = straightLineDepreciationCalculator;
    }

    @Override
    public BigDecimal calculateDepreciation(AssetRegistrationDTO asset, DepreciationPeriodDTO period, AssetCategoryDTO assetCategory, DepreciationMethodDTO depreciationMethod) {

        if (depreciationMethod.getDepreciationType() == DepreciationTypes.STRAIGHT_LINE) {
            return straightLineDepreciationCalculator.calculateDepreciation(asset, period, assetCategory, depreciationMethod);
        }
        if (depreciationMethod.getDepreciationType() == DepreciationTypes.DECLINING_BALANCE) {
            return reducingBalanceDepreciationCalculator.calculateDepreciation(asset, period, assetCategory, depreciationMethod);
        }
        return BigDecimal.ZERO;
    }
}
