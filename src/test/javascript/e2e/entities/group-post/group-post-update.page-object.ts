import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class GroupPostUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.groupPost.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#group-post-uuid'));
  nameInput: ElementFinder = element(by.css('input#group-post-name'));
  avatarInput: ElementFinder = element(by.css('textarea#group-post-avatar'));
  quickInfoInput: ElementFinder = element(by.css('textarea#group-post-quickInfo'));
  profileSelect: ElementFinder = element(by.css('select#group-post-profile'));
  baseInfoSelect: ElementFinder = element(by.css('select#group-post-baseInfo'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setNameInput(name) {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput() {
    return this.nameInput.getAttribute('value');
  }

  async setAvatarInput(avatar) {
    await this.avatarInput.sendKeys(avatar);
  }

  async getAvatarInput() {
    return this.avatarInput.getAttribute('value');
  }

  async setQuickInfoInput(quickInfo) {
    await this.quickInfoInput.sendKeys(quickInfo);
  }

  async getQuickInfoInput() {
    return this.quickInfoInput.getAttribute('value');
  }

  async profileSelectLastOption() {
    await this.profileSelect.all(by.tagName('option')).last().click();
  }

  async profileSelectOption(option) {
    await this.profileSelect.sendKeys(option);
  }

  getProfileSelect() {
    return this.profileSelect;
  }

  async getProfileSelectedOption() {
    return this.profileSelect.element(by.css('option:checked')).getText();
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
    await this.setNameInput('name');
    await waitUntilDisplayed(this.saveButton);
    await this.setAvatarInput('avatar');
    await waitUntilDisplayed(this.saveButton);
    await this.setQuickInfoInput('quickInfo');
    await this.profileSelectLastOption();
    await this.baseInfoSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
