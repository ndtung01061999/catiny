import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class MessageGroupUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.messageGroup.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#message-group-uuid'));
  groupNameInput: ElementFinder = element(by.css('input#message-group-groupName'));
  avatarInput: ElementFinder = element(by.css('textarea#message-group-avatar'));
  addByInput: ElementFinder = element(by.css('input#message-group-addBy'));
  baseInfoSelect: ElementFinder = element(by.css('select#message-group-baseInfo'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setGroupNameInput(groupName) {
    await this.groupNameInput.sendKeys(groupName);
  }

  async getGroupNameInput() {
    return this.groupNameInput.getAttribute('value');
  }

  async setAvatarInput(avatar) {
    await this.avatarInput.sendKeys(avatar);
  }

  async getAvatarInput() {
    return this.avatarInput.getAttribute('value');
  }

  async setAddByInput(addBy) {
    await this.addByInput.sendKeys(addBy);
  }

  async getAddByInput() {
    return this.addByInput.getAttribute('value');
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
    await this.setGroupNameInput('groupName');
    await waitUntilDisplayed(this.saveButton);
    await this.setAvatarInput('avatar');
    await waitUntilDisplayed(this.saveButton);
    await this.setAddByInput('addBy');
    await this.baseInfoSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
