package io.github.erp.modules;

/*-
 * Copyright © 2021 Edwin Njeru (mailnjeru@gmail.com)
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

import io.github.erp.modules.models.TaxRuleInt;

import javax.money.MonetaryAmount;

import static io.github.erp.modules.PaymentComputationUtils.roundToZeroDP;

/**
 * Marker for computation that contains a withholding VAT amount computation
 */
public interface HasWithholdingVATAmount {

    default MonetaryAmount calculateRoundedWithholdingVATAmount(TaxRuleInt taxRule, MonetaryAmount invoiceNetOfTax) {

        return roundToZeroDP(calculateWithholdingVATAmount(taxRule,invoiceNetOfTax));
    }

    MonetaryAmount calculateWithholdingVATAmount(TaxRuleInt taxRule, MonetaryAmount invoiceNetOfTax);
}