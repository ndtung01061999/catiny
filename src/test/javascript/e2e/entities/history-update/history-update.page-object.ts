import {by, element, ElementArrayFinder, ElementFinder} from 'protractor';

import {click, isVisible, waitUntilAnyDisplayed, waitUntilDisplayed, waitUntilHidden} from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import HistoryUpdateUpdatePage from './history-update-update.page-object';

const expect = chai.expect;

export class HistoryUpdateDeleteDialog
{
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.historyUpdate.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-historyUpdate'));

  getDialogTitle()
  {
    return this.dialogTitle;
  }

  async clickOnConfirmButton()
  {
    await this.confirmButton.click();
  }
}

export default class HistoryUpdateComponentsPage
{
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('history-update-heading'));
  noRecords: ElementFinder = element(by.css('#app-view-container .table-responsive div.alert.alert-warning'));
  table: ElementFinder = element(by.css('#app-view-container div.table-responsive > table'));

  records: ElementArrayFinder = this.table.all(by.css('tbody tr'));

  getDetailsButton(record: ElementFinder)
  {
    return record.element(by.css('a.btn.btn-info.btn-sm'));
  }

  getEditButton(record: ElementFinder)
  {
    return record.element(by.css('a.btn.btn-primary.btn-sm'));
  }

  getDeleteButton(record: ElementFinder)
  {
    return record.element(by.css('a.btn.btn-danger.btn-sm'));
  }

  async goToPage(navBarPage: NavBarPage)
  {
    await navBarPage.getEntityPage('history-update');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateHistoryUpdate()
  {
    await this.createButton.click();
    return new HistoryUpdateUpdatePage();
  }

  async deleteHistoryUpdate()
  {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const historyUpdateDeleteDialog = new HistoryUpdateDeleteDialog();
    await waitUntilDisplayed(historyUpdateDeleteDialog.deleteModal);
    expect(await historyUpdateDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.historyUpdate.delete.question/);
    await historyUpdateDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(historyUpdateDeleteDialog.deleteModal);

    expect(await isVisible(historyUpdateDeleteDialog.deleteModal)).to.be.false;
  }
}
