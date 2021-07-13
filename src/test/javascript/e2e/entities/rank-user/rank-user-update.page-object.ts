import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class RankUserUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.rankUser.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#rank-user-uuid'));
  ratingPointsInput: ElementFinder = element(by.css('input#rank-user-ratingPoints'));
  baseInfoSelect: ElementFinder = element(by.css('select#rank-user-baseInfo'));
  rankGroupSelect: ElementFinder = element(by.css('select#rank-user-rankGroup'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setRatingPointsInput(ratingPoints) {
    await this.ratingPointsInput.sendKeys(ratingPoints);
  }

  async getRatingPointsInput() {
    return this.ratingPointsInput.getAttribute('value');
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

  async rankGroupSelectLastOption() {
    await this.rankGroupSelect.all(by.tagName('option')).last().click();
  }

  async rankGroupSelectOption(option) {
    await this.rankGroupSelect.sendKeys(option);
  }

  getRankGroupSelect() {
    return this.rankGroupSelect;
  }

  async getRankGroupSelectedOption() {
    return this.rankGroupSelect.element(by.css('option:checked')).getText();
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
    await this.setRatingPointsInput('5');
    await this.baseInfoSelectLastOption();
    await this.rankGroupSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
