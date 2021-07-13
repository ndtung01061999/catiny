import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import FollowPageUpdatePage from './follow-page-update.page-object';

const expect = chai.expect;
export class FollowPageDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.followPage.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-followPage'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class FollowPageComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('follow-page-heading'));
  noRecords: ElementFinder = element(by.css('#app-view-container .table-responsive div.alert.alert-warning'));
  table: ElementFinder = element(by.css('#app-view-container div.table-responsive > table'));

  records: ElementArrayFinder = this.table.all(by.css('tbody tr'));

  getDetailsButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-info.btn-sm'));
  }

  getEditButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-primary.btn-sm'));
  }

  getDeleteButton(record: ElementFinder) {
    return record.element(by.css('a.btn.btn-danger.btn-sm'));
  }

  async goToPage(navBarPage: NavBarPage) {
    await navBarPage.getEntityPage('follow-page');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateFollowPage() {
    await this.createButton.click();
    return new FollowPageUpdatePage();
  }

  async deleteFollowPage() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const followPageDeleteDialog = new FollowPageDeleteDialog();
    await waitUntilDisplayed(followPageDeleteDialog.deleteModal);
    expect(await followPageDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.followPage.delete.question/);
    await followPageDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(followPageDeleteDialog.deleteModal);

    expect(await isVisible(followPageDeleteDialog.deleteModal)).to.be.false;
  }
}
