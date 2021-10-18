import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IInvoice, Invoice } from '../invoice.model';
import { InvoiceService } from '../service/invoice.service';
import { IPlaceholder } from 'app/entities/erpService/placeholder/placeholder.model';
import { PlaceholderService } from 'app/entities/erpService/placeholder/service/placeholder.service';
import {IPaymentLabel} from '../../../payment-label/payment-label.model';
import {IPayment} from '../../payment/payment.model';
import {PaymentLabelService} from '../../../payment-label/service/payment-label.service';
import {PaymentService} from '../../payment/service/payment.service';
import {select, Store} from "@ngrx/store";
import {State} from "../../../../store/global-store.definition";
import {
  dealerInvoicePaymentWorkflowCancelled,
  recordInvoicePaymentButtonClicked
} from "../../../../store/actions/dealer-invoice-workflows-status.actions";
import {Dealer, IDealer} from "../../../dealers/dealer/dealer.model";
import {DealerService} from "../../../dealers/dealer/service/dealer.service";
import {
  dealerInvoicePaymentState,
  dealerInvoiceSelectedDealer
} from "../../../../store/selectors/dealer-invoice-worklows-status.selectors";

@Component({
  selector: 'jhi-invoice-update',
  templateUrl: './invoice-update.component.html',
})
export class InvoiceUpdateComponent implements OnInit {
  isSaving = false;

  paymentLabelsSharedCollection: IPaymentLabel[] = [];
  paymentsSharedCollection: IPayment[] = [];
  dealersSharedCollection: IDealer[] = [];
  placeholdersSharedCollection: IPlaceholder[] = [];

  editForm = this.fb.group({
    id: [],
    invoiceNumber: [null, [Validators.required]],
    invoiceDate: [],
    invoiceAmount: [],
    currency: [null, [Validators.required]],
    conversionRate: [null, [Validators.required, Validators.min(1.0)]],
    paymentLabels: [],
    payment: [],
    dealer: [],
    placeholders: [],
  });

  weArePayingAnInvoiceDealer = false;
  storedDealer: IDealer = {...new Dealer()}

  constructor(
    protected invoiceService: InvoiceService,
    protected paymentLabelService: PaymentLabelService,
    protected paymentService: PaymentService,
    protected dealerService: DealerService,
    protected placeholderService: PlaceholderService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder,
    protected router: Router,
    protected store: Store<State>
  ) {
    this.store.pipe(select(dealerInvoicePaymentState)).subscribe(payingDealer => this.weArePayingAnInvoiceDealer = payingDealer);

    this.store.select<IDealer>(dealerInvoiceSelectedDealer).subscribe(dealer => {
      this.storedDealer = dealer;
    });
  }

  ngOnInit(): void {

    if (this.weArePayingAnInvoiceDealer) {
      this.activatedRoute.data.subscribe(({invoice}) => {
        this.updateFormWithStoredDealer(invoice, this.storedDealer);
      });
    } else {
      this.activatedRoute.data.subscribe(({invoice}) => {
        this.updateForm(invoice);
      });
    }

    this.loadRelationshipsOptions();
  }

  previousState(): void {
    this.store.dispatch(dealerInvoicePaymentWorkflowCancelled());
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invoice = this.createFromForm();
    if (invoice.id !== undefined) {
      this.subscribeToSaveResponse(this.invoiceService.update(invoice));
    } else {
      this.subscribeToSaveResponse(this.invoiceService.create(invoice));
    }
  }

  recordPayment(): void {
    this.isSaving = true;
    const invoice = this.createFromForm();
    if (invoice.id !== undefined) {
      this.subscribeToRecordPaymentResponse(this.invoiceService.update(invoice));
    } else {
      this.subscribeToRecordPaymentResponse(this.invoiceService.create(invoice));
    }
  }

  trackPaymentLabelById(index: number, item: IPaymentLabel): number {
    return item.id!;
  }

  trackPaymentById(index: number, item: IPayment): number {
    return item.id!;
  }

  trackDealerById(index: number, item: IDealer): number {
    return item.id!;
  }

  trackPlaceholderById(index: number, item: IPlaceholder): number {
    return item.id!;
  }

  getSelectedPaymentLabel(option: IPaymentLabel, selectedVals?: IPaymentLabel[]): IPaymentLabel {
    if (selectedVals) {
      for (const selectedVal of selectedVals) {
        if (option.id === selectedVal.id) {
          return selectedVal;
        }
      }
    }
    return option;
  }

  getSelectedPlaceholder(option: IPlaceholder, selectedVals?: IPlaceholder[]): IPlaceholder {
    if (selectedVals) {
      for (const selectedVal of selectedVals) {
        if (option.id === selectedVal.id) {
          return selectedVal;
        }
      }
    }
    return option;
  }

  protected subscribeToRecordPaymentResponse(result: Observable<HttpResponse<IInvoice>>): void {

    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      (res: HttpResponse<IInvoice>) => this.navigateToPayment(res),
      () => this.onSaveError()
    );
  }

  protected navigateToPayment(res: HttpResponse<IInvoice>): void {

    // TODO Add placeholders, payment-labels, ownedInvoices in the store
    if (res.body) {
      this.store.dispatch(recordInvoicePaymentButtonClicked({selectedInvoice: res.body}));
    }

    const paymentPath = 'payment/dealer/invoice';
    this.router.navigate([paymentPath]);
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvoice>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateFormWithStoredDealer(invoice: IInvoice, storedDealer: IDealer): void {
    this.editForm.patchValue({
      id: invoice.id,
      invoiceNumber: invoice.invoiceNumber,
      invoiceDate: invoice.invoiceDate,
      invoiceAmount: invoice.invoiceAmount,
      currency: invoice.currency,
      conversionRate: invoice.conversionRate,
      paymentLabels: storedDealer.paymentLabels,
      payment: invoice.payment,
      dealer: storedDealer,
      placeholders: storedDealer.placeholders,
    });

    this.paymentLabelsSharedCollection = this.paymentLabelService.addPaymentLabelToCollectionIfMissing(
      this.paymentLabelsSharedCollection,
      ...(invoice.paymentLabels ?? [])
    );
    this.paymentsSharedCollection = this.paymentService.addPaymentToCollectionIfMissing(this.paymentsSharedCollection, invoice.payment);
    this.dealersSharedCollection = this.dealerService.addDealerToCollectionIfMissing(this.dealersSharedCollection, invoice.dealer);
    this.placeholdersSharedCollection = this.placeholderService.addPlaceholderToCollectionIfMissing(
      this.placeholdersSharedCollection,
      ...(invoice.placeholders ?? [])
    );
  }

  protected updateForm(invoice: IInvoice): void {
    this.editForm.patchValue({
      id: invoice.id,
      invoiceNumber: invoice.invoiceNumber,
      invoiceDate: invoice.invoiceDate,
      invoiceAmount: invoice.invoiceAmount,
      currency: invoice.currency,
      conversionRate: invoice.conversionRate,
      paymentLabels: invoice.paymentLabels,
      payment: invoice.payment,
      dealer: invoice.dealer,
      placeholders: invoice.placeholders,
    });

    this.paymentLabelsSharedCollection = this.paymentLabelService.addPaymentLabelToCollectionIfMissing(
      this.paymentLabelsSharedCollection,
      ...(invoice.paymentLabels ?? [])
    );
    this.paymentsSharedCollection = this.paymentService.addPaymentToCollectionIfMissing(this.paymentsSharedCollection, invoice.payment);
    this.dealersSharedCollection = this.dealerService.addDealerToCollectionIfMissing(this.dealersSharedCollection, invoice.dealer);
    this.placeholdersSharedCollection = this.placeholderService.addPlaceholderToCollectionIfMissing(
      this.placeholdersSharedCollection,
      ...(invoice.placeholders ?? [])
    );
  }

  protected loadRelationshipsOptions(): void {
    this.paymentLabelService
      .query()
      .pipe(map((res: HttpResponse<IPaymentLabel[]>) => res.body ?? []))
      .pipe(
        map((paymentLabels: IPaymentLabel[]) =>
          this.paymentLabelService.addPaymentLabelToCollectionIfMissing(paymentLabels, ...(this.editForm.get('paymentLabels')!.value ?? []))
        )
      )
      .subscribe((paymentLabels: IPaymentLabel[]) => (this.paymentLabelsSharedCollection = paymentLabels));

    this.paymentService
      .query()
      .pipe(map((res: HttpResponse<IPayment[]>) => res.body ?? []))
      .pipe(
        map((payments: IPayment[]) => this.paymentService.addPaymentToCollectionIfMissing(payments, this.editForm.get('payment')!.value))
      )
      .subscribe((payments: IPayment[]) => (this.paymentsSharedCollection = payments));

    this.dealerService
      .query()
      .pipe(map((res: HttpResponse<IDealer[]>) => res.body ?? []))
      .pipe(map((dealers: IDealer[]) => this.dealerService.addDealerToCollectionIfMissing(dealers, this.editForm.get('dealer')!.value)))
      .subscribe((dealers: IDealer[]) => (this.dealersSharedCollection = dealers));

    this.placeholderService
      .query()
      .pipe(map((res: HttpResponse<IPlaceholder[]>) => res.body ?? []))
      .pipe(
        map((placeholders: IPlaceholder[]) =>
          this.placeholderService.addPlaceholderToCollectionIfMissing(placeholders, ...(this.editForm.get('placeholders')!.value ?? []))
        )
      )
      .subscribe((placeholders: IPlaceholder[]) => (this.placeholdersSharedCollection = placeholders));
  }

  protected createFromForm(): IInvoice {
    return {
      ...new Invoice(),
      id: this.editForm.get(['id'])!.value,
      invoiceNumber: this.editForm.get(['invoiceNumber'])!.value,
      invoiceDate: this.editForm.get(['invoiceDate'])!.value,
      invoiceAmount: this.editForm.get(['invoiceAmount'])!.value,
      currency: this.editForm.get(['currency'])!.value,
      conversionRate: this.editForm.get(['conversionRate'])!.value,
      paymentLabels: this.editForm.get(['paymentLabels'])!.value,
      payment: this.editForm.get(['payment'])!.value,
      dealer: this.editForm.get(['dealer'])!.value,
      placeholders: this.editForm.get(['placeholders'])!.value,
    };
  }
}