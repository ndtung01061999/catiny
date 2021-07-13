import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class NotificationUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.notification.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#notification-uuid'));
  notifyTypeSelect: ElementFinder = element(by.css('select#notification-notifyType'));
  titleInput: ElementFinder = element(by.css('input#notification-title'));
  contentInput: ElementFinder = element(by.css('textarea#notification-content'));
  baseInfoSelect: ElementFinder = element(by.css('select#notification-baseInfo'));
  masterUserSelect: ElementFinder = element(by.css('select#notification-masterUser'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setNotifyTypeSelect(notifyType) {
    await this.notifyTypeSelect.sendKeys(notifyType);
  }

  async getNotifyTypeSelect() {
    return this.notifyTypeSelect.element(by.css('option:checked')).getText();
  }

  async notifyTypeSelectLastOption() {
    await this.notifyTypeSelect.all(by.tagName('option')).last().click();
  }
  async setTitleInput(title) {
    await this.titleInput.sendKeys(title);
  }

  async getTitleInput() {
    return this.titleInput.getAttribute('value');
  }

  async setContentInput(content) {
    await this.contentInput.sendKeys(content);
  }

  async getContentInput() {
    return this.contentInput.getAttribute('value');
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

  async masterUserSelectLastOption() {
    await this.masterUserSelect.all(by.tagName('option')).last().click();
  }

  async masterUserSelectOption(option) {
    await this.masterUserSelect.sendKeys(option);
  }

  getMasterUserSelect() {
    return this.masterUserSelect;
  }

  async getMasterUserSelectedOption() {
    return this.masterUserSelect.element(by.css('option:checked')).getText();
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
    await this.notifyTypeSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.setTitleInput('title');
    await waitUntilDisplayed(this.saveButton);
    await this.setContentInput('content');
    await this.baseInfoSelectLastOption();
    await this.masterUserSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
