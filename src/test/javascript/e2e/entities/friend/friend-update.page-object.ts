import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class FriendUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.friend.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#friend-uuid'));
  friendTypeSelect: ElementFinder = element(by.css('select#friend-friendType'));
  baseInfoSelect: ElementFinder = element(by.css('select#friend-baseInfo'));
  friendDetailsSelect: ElementFinder = element(by.css('select#friend-friendDetails'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setFriendTypeSelect(friendType) {
    await this.friendTypeSelect.sendKeys(friendType);
  }

  async getFriendTypeSelect() {
    return this.friendTypeSelect.element(by.css('option:checked')).getText();
  }

  async friendTypeSelectLastOption() {
    await this.friendTypeSelect.all(by.tagName('option')).last().click();
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

  async friendDetailsSelectLastOption() {
    await this.friendDetailsSelect.all(by.tagName('option')).last().click();
  }

  async friendDetailsSelectOption(option) {
    await this.friendDetailsSelect.sendKeys(option);
  }

  getFriendDetailsSelect() {
    return this.friendDetailsSelect;
  }

  async getFriendDetailsSelectedOption() {
    return this.friendDetailsSelect.element(by.css('option:checked')).getText();
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
    await this.friendTypeSelectLastOption();
    await this.baseInfoSelectLastOption();
    await this.friendDetailsSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
