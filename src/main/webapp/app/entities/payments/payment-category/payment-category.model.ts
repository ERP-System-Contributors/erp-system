import { IPaymentCalculation } from 'app/entities/payments/payment-calculation/payment-calculation.model';
import { IPayment } from 'app/entities/payments/payment/payment.model';
import { CategoryTypes } from 'app/entities/enumerations/category-types.model';

export interface IPaymentCategory {
  id?: number;
  categoryName?: string;
  categoryDescription?: string | null;
  categoryType?: CategoryTypes;
  paymentCalculations?: IPaymentCalculation[] | null;
  payments?: IPayment[] | null;
}

export class PaymentCategory implements IPaymentCategory {
  constructor(
    public id?: number,
    public categoryName?: string,
    public categoryDescription?: string | null,
    public categoryType?: CategoryTypes,
    public paymentCalculations?: IPaymentCalculation[] | null,
    public payments?: IPayment[] | null
  ) {}
}

export function getPaymentCategoryIdentifier(paymentCategory: IPaymentCategory): number | undefined {
  return paymentCategory.id;
}