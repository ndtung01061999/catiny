import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import VideoUpdatePage from './video-update.page-object';

const expect = chai.expect;
export class VideoDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.video.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-video'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class VideoComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('video-heading'));
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
    await navBarPage.getEntityPage('video');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateVideo() {
    await this.createButton.click();
    return new VideoUpdatePage();
  }

  async deleteVideo() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const videoDeleteDialog = new VideoDeleteDialog();
    await waitUntilDisplayed(videoDeleteDialog.deleteModal);
    expect(await videoDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.video.delete.question/);
    await videoDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(videoDeleteDialog.deleteModal);

    expect(await isVisible(videoDeleteDialog.deleteModal)).to.be.false;
  }
}
