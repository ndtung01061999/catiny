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
  quickInfoInput: ElementFinder = element(by.css('textarea#master-user-quickInfo'));
  userSelect: ElementFinder = element(by.css('select#master-user-user'));
  myProfileSelect: ElementFinder = element(by.css('select#master-user-myProfile'));
  myAccountStatusSelect: ElementFinder = element(by.css('select#master-user-myAccountStatus'));
  myRankSelect: ElementFinder = element(by.css('select#master-user-myRank'));
  avatarSelect: ElementFinder = element(by.css('select#master-user-avatar'));
  baseInfoSelect: ElementFinder = element(by.css('select#master-user-baseInfo'));
  myGroupPostSelect: ElementFinder = element(by.css('select#master-user-myGroupPost'));
  messageGroupSelect: ElementFinder = element(by.css('select#master-user-messageGroup'));
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

  async myProfileSelectLastOption() {
    await this.myProfileSelect.all(by.tagName('option')).last().click();
  }

  async myProfileSelectOption(option) {
    await this.myProfileSelect.sendKeys(option);
  }

  getMyProfileSelect() {
    return this.myProfileSelect;
  }

  async getMyProfileSelectedOption() {
    return this.myProfileSelect.element(by.css('option:checked')).getText();
  }

  async myAccountStatusSelectLastOption() {
    await this.myAccountStatusSelect.all(by.tagName('option')).last().click();
  }

  async myAccountStatusSelectOption(option) {
    await this.myAccountStatusSelect.sendKeys(option);
  }

  getMyAccountStatusSelect() {
    return this.myAccountStatusSelect;
  }

  async getMyAccountStatusSelectedOption() {
    return this.myAccountStatusSelect.element(by.css('option:checked')).getText();
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

  async avatarSelectLastOption() {
    await this.avatarSelect.all(by.tagName('option')).last().click();
  }

  async avatarSelectOption(option) {
    await this.avatarSelect.sendKeys(option);
  }

  getAvatarSelect() {
    return this.avatarSelect;
  }

  async getAvatarSelectedOption() {
    return this.avatarSelect.element(by.css('option:checked')).getText();
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

  async myGroupPostSelectLastOption() {
    await this.myGroupPostSelect.all(by.tagName('option')).last().click();
  }

  async myGroupPostSelectOption(option) {
    await this.myGroupPostSelect.sendKeys(option);
  }

  getMyGroupPostSelect() {
    return this.myGroupPostSelect;
  }

  async getMyGroupPostSelectedOption() {
    return this.myGroupPostSelect.element(by.css('option:checked')).getText();
  }

  async messageGroupSelectLastOption() {
    await this.messageGroupSelect.all(by.tagName('option')).last().click();
  }

  async messageGroupSelectOption(option) {
    await this.messageGroupSelect.sendKeys(option);
  }

  getMessageGroupSelect() {
    return this.messageGroupSelect;
  }

  async getMessageGroupSelectedOption() {
    return this.messageGroupSelect.element(by.css('option:checked')).getText();
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
    await this.setQuickInfoInput('quickInfo');
    await this.userSelectLastOption();
    await this.myProfileSelectLastOption();
    await this.myAccountStatusSelectLastOption();
    await this.myRankSelectLastOption();
    await this.avatarSelectLastOption();
    await this.baseInfoSelectLastOption();
    // this.myGroupPostSelectLastOption();
    // this.messageGroupSelectLastOption();
    // this.topicInterestSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
