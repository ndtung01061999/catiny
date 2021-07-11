import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class FollowPageUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.followPage.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#follow-page-uuid'));
  baseInfoSelect: ElementFinder = element(by.css('select#follow-page-baseInfo'));
  followPageDetailsSelect: ElementFinder = element(by.css('select#follow-page-followPageDetails'));
  masterUserSelect: ElementFinder = element(by.css('select#follow-page-masterUser'));

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

  async followPageDetailsSelectLastOption() {
    await this.followPageDetailsSelect.all(by.tagName('option')).last().click();
  }

  async followPageDetailsSelectOption(option) {
    await this.followPageDetailsSelect.sendKeys(option);
  }

  getFollowPageDetailsSelect() {
    return this.followPageDetailsSelect;
  }

  async getFollowPageDetailsSelectedOption() {
    return this.followPageDetailsSelect.element(by.css('option:checked')).getText();
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
    await this.baseInfoSelectLastOption();
    await this.followPageDetailsSelectLastOption();
    await this.masterUserSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
