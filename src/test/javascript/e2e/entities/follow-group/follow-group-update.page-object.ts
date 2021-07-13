import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class FollowGroupUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.followGroup.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#follow-group-uuid'));
  baseInfoSelect: ElementFinder = element(by.css('select#follow-group-baseInfo'));
  followGroupDetailsSelect: ElementFinder = element(by.css('select#follow-group-followGroupDetails'));
  masterUserSelect: ElementFinder = element(by.css('select#follow-group-masterUser'));

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

  async followGroupDetailsSelectLastOption() {
    await this.followGroupDetailsSelect.all(by.tagName('option')).last().click();
  }

  async followGroupDetailsSelectOption(option) {
    await this.followGroupDetailsSelect.sendKeys(option);
  }

  getFollowGroupDetailsSelect() {
    return this.followGroupDetailsSelect;
  }

  async getFollowGroupDetailsSelectedOption() {
    return this.followGroupDetailsSelect.element(by.css('option:checked')).getText();
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
    await this.followGroupDetailsSelectLastOption();
    await this.masterUserSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
