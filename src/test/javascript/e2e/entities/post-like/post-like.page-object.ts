import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import PostLikeUpdatePage from './post-like-update.page-object';

const expect = chai.expect;
export class PostLikeDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.postLike.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-postLike'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class PostLikeComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('post-like-heading'));
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
    await navBarPage.getEntityPage('post-like');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreatePostLike() {
    await this.createButton.click();
    return new PostLikeUpdatePage();
  }

  async deletePostLike() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const postLikeDeleteDialog = new PostLikeDeleteDialog();
    await waitUntilDisplayed(postLikeDeleteDialog.deleteModal);
    expect(await postLikeDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.postLike.delete.question/);
    await postLikeDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(postLikeDeleteDialog.deleteModal);

    expect(await isVisible(postLikeDeleteDialog.deleteModal)).to.be.false;
  }
}
