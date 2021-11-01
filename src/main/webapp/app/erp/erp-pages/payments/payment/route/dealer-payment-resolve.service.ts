import {Injectable} from '@angular/core';
import {Resolve} from '@angular/router';
import {IPayment, Payment} from '../payment.model';
import {Store} from '@ngrx/store';
import {State} from '../../../../store/global-store.definition';
import {Observable, of} from 'rxjs';
import {
  DEFAULT_CURRENCY,
  DEFAULT_DATE,
  DEFAULT_DESCRIPTION,
  DEFAULT_INVOICE_AMOUNT,
  DEFAULT_TRANSACTION_AMOUNT,
} from '../default-values.constants';

@Injectable({ providedIn: 'root' })
export class DealerPaymentResolveService implements Resolve<IPayment> {
  constructor(
    protected store: Store<State>
  ) {}

  resolve(): Observable<IPayment> | Observable<never> {

    const payment: Payment = {
      paymentDate: DEFAULT_DATE,
      settlementCurrency: DEFAULT_CURRENCY,
      paymentAmount: DEFAULT_TRANSACTION_AMOUNT,
      invoicedAmount: DEFAULT_INVOICE_AMOUNT,
      description: DEFAULT_DESCRIPTION
    }

    return of(payment);
  }
}
