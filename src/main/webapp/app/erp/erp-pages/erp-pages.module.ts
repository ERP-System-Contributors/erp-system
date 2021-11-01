import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';

@NgModule({
  imports: [RouterModule.forChild([
    {
      path: 'erp/file-type',
      data: { pageTitle: 'ERP | File Types' },
      loadChildren: () => import('./files/file-type/file-type.module').then(m => m.ErpServiceFileTypeModule),
    },
    {
      path: 'erp/file-upload',
      data: { pageTitle: 'ERP | File Uploads' },
      loadChildren: () => import('./files/file-upload/file-upload.module').then(m => m.ErpServiceFileUploadModule),
    },
    {
      path: 'erp/message-token',
      data: { pageTitle: 'ERP | Message Tokens' },
      loadChildren: () => import('./files/message-token/message-token.module').then(m => m.ErpServiceMessageTokenModule),
    },
    {
      path: 'erp/invoice',
      data: { pageTitle: 'ERP | Invoices' },
      loadChildren: () => import('./payments/invoice/invoice.module').then(m => m.ErpServiceInvoiceModule),
    },
    {
      path: 'erp/payment',
      data: { pageTitle: 'ERP | Payments' },
      loadChildren: () => import('./payments/payment/payment.module').then(m => m.ErpServicePaymentModule),
    },
    {
      path: 'erp/payment-calculation',
      data: { pageTitle: 'ERP | PaymentCalculations' },
      loadChildren: () =>
        import('./payments/payment-calculation/payment-calculation.module').then(m => m.ErpServicePaymentCalculationModule),
    },
    {
      path: 'erp/payment-requisition',
      data: { pageTitle: 'ERP | PaymentRequisitions' },
      loadChildren: () =>
        import('./payments/payment-requisition/payment-requisition.module').then(m => m.ErpServicePaymentRequisitionModule),
    },
    {
      path: 'erp/tax-reference',
      data: { pageTitle: 'ERP | Tax Ref' },
      loadChildren: () => import('./payments/tax-reference/tax-reference.module').then(m => m.ErpServiceTaxReferenceModule),
    },
    {
      path: 'erp/tax-rule',
      data: { pageTitle: 'ERP | Tax Rules' },
      loadChildren: () => import('./payments/tax-rule/tax-rule.module').then(m => m.ErpServiceTaxRuleModule),
    },
    {
      path: 'erp/payment-category',
      data: { pageTitle: 'ERP | Payment Categories' },
      loadChildren: () => import('./payments/payment-category/payment-category.module').then(m => m.ErpServicePaymentCategoryModule),
    },
    {
      path: 'erp/payment-label',
      data: { pageTitle: 'ERP | Payment Labels' },
      loadChildren: () => import('./payment-label/payment-label.module').then(m => m.PaymentLabelModule),
    },
    {
      path: 'erp/signed-payment',
      data: { pageTitle: 'ERP | Signed Payments' },
      loadChildren: () => import('./signed-payment/signed-payment.module').then(m => m.SignedPaymentModule),
    },
    {
      path: 'erp/dealer',
      data: { pageTitle: 'ERP | Payment Dealers' },
      loadChildren: () => import('./dealers/dealer/dealer.module').then(m => m.ErpServiceDealerModule),
    },
  ])]
})
export class ErpPagesModule {}
