import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class MasterUserUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.masterUser.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#master-user-uuid'));
  fullNameInput: ElementFinder = element(by.css('input#master-user-fullName'));
  nicknameInput: ElementFinder = element(by.css('input#master-user-nickname'));
  avatarInput: ElementFinder = element(by.css('textarea#master-user-avatar'));
  quickInfoInput: ElementFinder = element(by.css('textarea#master-user-quickInfo'));
  userSelect: ElementFinder = element(by.css('select#master-user-user'));
  myRankSelect: ElementFinder = element(by.css('select#master-user-myRank'));
  baseInfoSelect: ElementFinder = element(by.css('select#master-user-baseInfo'));
  topicInterestSelect: ElementFinder = element(by.css('select#master-user-topicInterest'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setFullNameInput(fullName) {
    await this.fullNameInput.sendKeys(fullName);
  }

  async getFullNameInput() {
    return this.fullNameInput.getAttribute('value');
  }

  async setNicknameInput(nickname) {
    await this.nicknameInput.sendKeys(nickname);
  }

  async getNicknameInput() {
    return this.nicknameInput.getAttribute('value');
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

  async userSelectLastOption() {
    await this.userSelect.all(by.tagName('option')).last().click();
  }

  async userSelectOption(option) {
    await this.userSelect.sendKeys(option);
  }

  getUserSelect() {
    return this.userSelect;
  }

  async getUserSelectedOption() {
    return this.userSelect.element(by.css('option:checked')).getText();
  }

  async myRankSelectLastOption() {
    await this.myRankSelect.all(by.tagName('option')).last().click();
  }

  async myRankSelectOption(option) {
    await this.myRankSelect.sendKeys(option);
  }

  getMyRankSelect() {
    return this.myRankSelect;
  }

  async getMyRankSelectedOption() {
    return this.myRankSelect.element(by.css('option:checked')).getText();
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

  async topicInterestSelectLastOption() {
    await this.topicInterestSelect.all(by.tagName('option')).last().click();
  }

  async topicInterestSelectOption(option) {
    await this.topicInterestSelect.sendKeys(option);
  }

  getTopicInterestSelect() {
    return this.topicInterestSelect;
  }

  async getTopicInterestSelectedOption() {
    return this.topicInterestSelect.element(by.css('option:checked')).getText();
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
    await this.setFullNameInput('fullName');
    await waitUntilDisplayed(this.saveButton);
    await this.setNicknameInput('nickname');
    await waitUntilDisplayed(this.saveButton);
    await this.setAvatarInput('avatar');
    await waitUntilDisplayed(this.saveButton);
    await this.setQuickInfoInput('quickInfo');
    await this.userSelectLastOption();
    await this.myRankSelectLastOption();
    await this.baseInfoSelectLastOption();
    // this.topicInterestSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
