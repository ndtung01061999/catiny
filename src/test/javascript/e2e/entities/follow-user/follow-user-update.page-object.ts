import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class FollowUserUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.followUser.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#follow-user-uuid'));
  baseInfoSelect: ElementFinder = element(by.css('select#follow-user-baseInfo'));
  followUserDetailsSelect: ElementFinder = element(by.css('select#follow-user-followUserDetails'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
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

  async followUserDetailsSelectLastOption() {
    await this.followUserDetailsSelect.all(by.tagName('option')).last().click();
  }

  async followUserDetailsSelectOption(option) {
    await this.followUserDetailsSelect.sendKeys(option);
  }

  getFollowUserDetailsSelect() {
    return this.followUserDetailsSelect;
  }

  async getFollowUserDetailsSelectedOption() {
    return this.followUserDetailsSelect.element(by.css('option:checked')).getText();
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
    await this.baseInfoSelectLastOption();
    await this.followUserDetailsSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
