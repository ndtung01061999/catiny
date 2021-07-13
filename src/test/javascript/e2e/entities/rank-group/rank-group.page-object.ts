import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import RankGroupUpdatePage from './rank-group-update.page-object';

const expect = chai.expect;
export class RankGroupDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.rankGroup.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-rankGroup'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class RankGroupComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('rank-group-heading'));
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
    await navBarPage.getEntityPage('rank-group');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateRankGroup() {
    await this.createButton.click();
    return new RankGroupUpdatePage();
  }

  async deleteRankGroup() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const rankGroupDeleteDialog = new RankGroupDeleteDialog();
    await waitUntilDisplayed(rankGroupDeleteDialog.deleteModal);
    expect(await rankGroupDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.rankGroup.delete.question/);
    await rankGroupDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(rankGroupDeleteDialog.deleteModal);

    expect(await isVisible(rankGroupDeleteDialog.deleteModal)).to.be.false;
  }
}
