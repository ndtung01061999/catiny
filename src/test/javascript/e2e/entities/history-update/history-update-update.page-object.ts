import {by, element, ElementFinder} from 'protractor';
import {waitUntilDisplayed, waitUntilHidden} from '../../util/utils';

const expect = chai.expect;

export default class HistoryUpdateUpdatePage
{
  pageTitle: ElementFinder = element(by.id('catinyApp.historyUpdate.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#history-update-uuid'));
  versionInput: ElementFinder = element(by.css('input#history-update-version'));
  contentInput: ElementFinder = element(by.css('textarea#history-update-content'));
  baseInfoSelect: ElementFinder = element(by.css('select#history-update-baseInfo'));

  getPageTitle()
  {
    return this.pageTitle;
  }

  async setUuidInput(uuid)
  {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput()
  {
    return this.uuidInput.getAttribute('value');
  }

  async setVersionInput(version)
  {
    await this.versionInput.sendKeys(version);
  }

  async getVersionInput()
  {
    return this.versionInput.getAttribute('value');
  }

  async setContentInput(content)
  {
    await this.contentInput.sendKeys(content);
  }

  async getContentInput()
  {
    return this.contentInput.getAttribute('value');
  }

  async baseInfoSelectLastOption()
  {
    await this.baseInfoSelect.all(by.tagName('option')).last().click();
  }

  async baseInfoSelectOption(option)
  {
    await this.baseInfoSelect.sendKeys(option);
  }

  getBaseInfoSelect()
  {
    return this.baseInfoSelect;
  }

  async getBaseInfoSelectedOption()
  {
    return this.baseInfoSelect.element(by.css('option:checked')).getText();
  }

  async save()
  {
    await this.saveButton.click();
  }

  async cancel()
  {
    await this.cancelButton.click();
  }

  getSaveButton()
  {
    return this.saveButton;
  }

  async enterData()
  {
    await waitUntilDisplayed(this.saveButton);
    await this.setUuidInput('64c99148-3908-465d-8c4a-e510e3ade974');
    await waitUntilDisplayed(this.saveButton);
    await this.setVersionInput('5');
    await waitUntilDisplayed(this.saveButton);
    await this.setContentInput('content');
    await this.baseInfoSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
