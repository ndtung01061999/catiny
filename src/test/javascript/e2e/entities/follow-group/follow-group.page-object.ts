import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import FollowGroupUpdatePage from './follow-group-update.page-object';

const expect = chai.expect;
export class FollowGroupDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.followGroup.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-followGroup'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class FollowGroupComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('follow-group-heading'));
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
    await navBarPage.getEntityPage('follow-group');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateFollowGroup() {
    await this.createButton.click();
    return new FollowGroupUpdatePage();
  }

  async deleteFollowGroup() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const followGroupDeleteDialog = new FollowGroupDeleteDialog();
    await waitUntilDisplayed(followGroupDeleteDialog.deleteModal);
    expect(await followGroupDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.followGroup.delete.question/);
    await followGroupDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(followGroupDeleteDialog.deleteModal);

    expect(await isVisible(followGroupDeleteDialog.deleteModal)).to.be.false;
  }
}
