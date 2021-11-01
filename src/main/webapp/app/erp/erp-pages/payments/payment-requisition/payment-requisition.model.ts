import { IDealer } from 'app/entities/dealers/dealer/dealer.model';
import {IPaymentLabel} from '../../payment-label/payment-label.model';
import {IPlaceholder} from '../../../../entities/erpService/placeholder/placeholder.model';

export interface IPaymentRequisition {
  id?: number;
  invoicedAmount?: number | null;
  disbursementCost?: number | null;
  vatableAmount?: number | null;
  paymentLabels?: IPaymentLabel[] | null;
  dealer?: IDealer | null;
  placeholders?: IPlaceholder[] | null;
}

export class PaymentRequisition implements IPaymentRequisition {
  constructor(
    public id?: number,
    public invoicedAmount?: number | null,
    public disbursementCost?: number | null,
    public vatableAmount?: number | null,
    public paymentLabels?: IPaymentLabel[] | null,
    public dealer?: IDealer | null,
    public placeholders?: IPlaceholder[] | null
  ) {}
}

export function getPaymentRequisitionIdentifier(paymentRequisition: IPaymentRequisition): number | undefined {
  return paymentRequisition.id;
}
