import { element, by, ElementFinder, ElementArrayFinder } from 'protractor';

import { waitUntilAnyDisplayed, waitUntilDisplayed, click, waitUntilHidden, isVisible } from '../../util/utils';

import NavBarPage from './../../page-objects/navbar-page';

import DeviceStatusUpdatePage from './device-status-update.page-object';

const expect = chai.expect;
export class DeviceStatusDeleteDialog {
  deleteModal = element(by.className('modal'));
  private dialogTitle: ElementFinder = element(by.id('catinyApp.deviceStatus.delete.question'));
  private confirmButton = element(by.id('jhi-confirm-delete-deviceStatus'));

  getDialogTitle() {
    return this.dialogTitle;
  }

  async clickOnConfirmButton() {
    await this.confirmButton.click();
  }
}

export default class DeviceStatusComponentsPage {
  createButton: ElementFinder = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('div table .btn-danger'));
  title: ElementFinder = element(by.id('device-status-heading'));
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
    await navBarPage.getEntityPage('device-status');
    await waitUntilAnyDisplayed([this.noRecords, this.table]);
    return this;
  }

  async goToCreateDeviceStatus() {
    await this.createButton.click();
    return new DeviceStatusUpdatePage();
  }

  async deleteDeviceStatus() {
    const deleteButton = this.getDeleteButton(this.records.last());
    await click(deleteButton);

    const deviceStatusDeleteDialog = new DeviceStatusDeleteDialog();
    await waitUntilDisplayed(deviceStatusDeleteDialog.deleteModal);
    expect(await deviceStatusDeleteDialog.getDialogTitle().getAttribute('id')).to.match(/catinyApp.deviceStatus.delete.question/);
    await deviceStatusDeleteDialog.clickOnConfirmButton();

    await waitUntilHidden(deviceStatusDeleteDialog.deleteModal);

    expect(await isVisible(deviceStatusDeleteDialog.deleteModal)).to.be.false;
  }
}
