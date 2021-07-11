import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class DeviceStatusUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.deviceStatus.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#device-status-uuid'));
  deviceNameInput: ElementFinder = element(by.css('input#device-status-deviceName'));
  deviceTypeSelect: ElementFinder = element(by.css('select#device-status-deviceType'));
  deviceStatusSelect: ElementFinder = element(by.css('select#device-status-deviceStatus'));
  lastVisitedInput: ElementFinder = element(by.css('input#device-status-lastVisited'));
  statusCommentInput: ElementFinder = element(by.css('input#device-status-statusComment'));
  baseInfoSelect: ElementFinder = element(by.css('select#device-status-baseInfo'));
  accountStatusSelect: ElementFinder = element(by.css('select#device-status-accountStatus'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setDeviceNameInput(deviceName) {
    await this.deviceNameInput.sendKeys(deviceName);
  }

  async getDeviceNameInput() {
    return this.deviceNameInput.getAttribute('value');
  }

  async setDeviceTypeSelect(deviceType) {
    await this.deviceTypeSelect.sendKeys(deviceType);
  }

  async getDeviceTypeSelect() {
    return this.deviceTypeSelect.element(by.css('option:checked')).getText();
  }

  async deviceTypeSelectLastOption() {
    await this.deviceTypeSelect.all(by.tagName('option')).last().click();
  }
  async setDeviceStatusSelect(deviceStatus) {
    await this.deviceStatusSelect.sendKeys(deviceStatus);
  }

  async getDeviceStatusSelect() {
    return this.deviceStatusSelect.element(by.css('option:checked')).getText();
  }

  async deviceStatusSelectLastOption() {
    await this.deviceStatusSelect.all(by.tagName('option')).last().click();
  }
  async setLastVisitedInput(lastVisited) {
    await this.lastVisitedInput.sendKeys(lastVisited);
  }

  async getLastVisitedInput() {
    return this.lastVisitedInput.getAttribute('value');
  }

  async setStatusCommentInput(statusComment) {
    await this.statusCommentInput.sendKeys(statusComment);
  }

  async getStatusCommentInput() {
    return this.statusCommentInput.getAttribute('value');
  }

  async baseInfoSelectLastOption() {
    await this.baseInfoSelect.all(by.tagName('option')).last().click();
  }

  async baseInfoSelectOption(option) {
    await this.baseInfoSelect.sendKeys(option);
  }

  getBaseInfoSelect() {
    return this.baseInfoSelect;
  }

  async getBaseInfoSelectedOption() {
    return this.baseInfoSelect.element(by.css('option:checked')).getText();
  }

  async accountStatusSelectLastOption() {
    await this.accountStatusSelect.all(by.tagName('option')).last().click();
  }

  async accountStatusSelectOption(option) {
    await this.accountStatusSelect.sendKeys(option);
  }

  getAccountStatusSelect() {
    return this.accountStatusSelect;
  }

  async getAccountStatusSelectedOption() {
    return this.accountStatusSelect.element(by.css('option:checked')).getText();
  }

  async save() {
    await this.saveButton.click();
  }

  async cancel() {
    await this.cancelButton.click();
  }

  getSaveButton() {
    return this.saveButton;
  }

  async enterData() {
    await waitUntilDisplayed(this.saveButton);
    await this.setUuidInput('64c99148-3908-465d-8c4a-e510e3ade974');
    await waitUntilDisplayed(this.saveButton);
    await this.setDeviceNameInput('deviceName');
    await waitUntilDisplayed(this.saveButton);
    await this.deviceTypeSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.deviceStatusSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.setLastVisitedInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setStatusCommentInput('statusComment');
    await this.baseInfoSelectLastOption();
    await this.accountStatusSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
