import { element, by, ElementFinder, protractor } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class AccountStatusUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.accountStatus.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#account-status-uuid'));
  accountStatusSelect: ElementFinder = element(by.css('select#account-status-accountStatus'));
  lastVisitedInput: ElementFinder = element(by.css('input#account-status-lastVisited'));
  statusCommentInput: ElementFinder = element(by.css('input#account-status-statusComment'));
  baseInfoSelect: ElementFinder = element(by.css('select#account-status-baseInfo'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setAccountStatusSelect(accountStatus) {
    await this.accountStatusSelect.sendKeys(accountStatus);
  }

  async getAccountStatusSelect() {
    return this.accountStatusSelect.element(by.css('option:checked')).getText();
  }

  async accountStatusSelectLastOption() {
    await this.accountStatusSelect.all(by.tagName('option')).last().click();
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
    await this.accountStatusSelectLastOption();
    await waitUntilDisplayed(this.saveButton);
    await this.setLastVisitedInput('01/01/2001' + protractor.Key.TAB + '02:30AM');
    await waitUntilDisplayed(this.saveButton);
    await this.setStatusCommentInput('statusComment');
    await this.baseInfoSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
