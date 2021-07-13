import { element, by, ElementFinder } from 'protractor';
import { waitUntilDisplayed, waitUntilHidden, isVisible } from '../../util/utils';

const expect = chai.expect;

export default class UserProfileUpdatePage {
  pageTitle: ElementFinder = element(by.id('catinyApp.userProfile.home.createOrEditLabel'));
  saveButton: ElementFinder = element(by.id('save-entity'));
  cancelButton: ElementFinder = element(by.id('cancel-save'));
  uuidInput: ElementFinder = element(by.css('input#user-profile-uuid'));
  workInput: ElementFinder = element(by.css('textarea#user-profile-work'));
  educationInput: ElementFinder = element(by.css('textarea#user-profile-education'));
  placesLivedInput: ElementFinder = element(by.css('textarea#user-profile-placesLived'));
  contactInfoInput: ElementFinder = element(by.css('textarea#user-profile-contactInfo'));
  webSocialLinksInput: ElementFinder = element(by.css('textarea#user-profile-webSocialLinks'));
  basicInfoInput: ElementFinder = element(by.css('textarea#user-profile-basicInfo'));
  relationshipInfoInput: ElementFinder = element(by.css('textarea#user-profile-relationshipInfo'));
  familyInput: ElementFinder = element(by.css('textarea#user-profile-family'));
  detailAboutInput: ElementFinder = element(by.css('textarea#user-profile-detailAbout'));
  lifeEventsInput: ElementFinder = element(by.css('textarea#user-profile-lifeEvents'));
  hobbiesInput: ElementFinder = element(by.css('textarea#user-profile-hobbies'));
  featuredInput: ElementFinder = element(by.css('textarea#user-profile-featured'));
  baseInfoSelect: ElementFinder = element(by.css('select#user-profile-baseInfo'));

  getPageTitle() {
    return this.pageTitle;
  }

  async setUuidInput(uuid) {
    await this.uuidInput.sendKeys(uuid);
  }

  async getUuidInput() {
    return this.uuidInput.getAttribute('value');
  }

  async setWorkInput(work) {
    await this.workInput.sendKeys(work);
  }

  async getWorkInput() {
    return this.workInput.getAttribute('value');
  }

  async setEducationInput(education) {
    await this.educationInput.sendKeys(education);
  }

  async getEducationInput() {
    return this.educationInput.getAttribute('value');
  }

  async setPlacesLivedInput(placesLived) {
    await this.placesLivedInput.sendKeys(placesLived);
  }

  async getPlacesLivedInput() {
    return this.placesLivedInput.getAttribute('value');
  }

  async setContactInfoInput(contactInfo) {
    await this.contactInfoInput.sendKeys(contactInfo);
  }

  async getContactInfoInput() {
    return this.contactInfoInput.getAttribute('value');
  }

  async setWebSocialLinksInput(webSocialLinks) {
    await this.webSocialLinksInput.sendKeys(webSocialLinks);
  }

  async getWebSocialLinksInput() {
    return this.webSocialLinksInput.getAttribute('value');
  }

  async setBasicInfoInput(basicInfo) {
    await this.basicInfoInput.sendKeys(basicInfo);
  }

  async getBasicInfoInput() {
    return this.basicInfoInput.getAttribute('value');
  }

  async setRelationshipInfoInput(relationshipInfo) {
    await this.relationshipInfoInput.sendKeys(relationshipInfo);
  }

  async getRelationshipInfoInput() {
    return this.relationshipInfoInput.getAttribute('value');
  }

  async setFamilyInput(family) {
    await this.familyInput.sendKeys(family);
  }

  async getFamilyInput() {
    return this.familyInput.getAttribute('value');
  }

  async setDetailAboutInput(detailAbout) {
    await this.detailAboutInput.sendKeys(detailAbout);
  }

  async getDetailAboutInput() {
    return this.detailAboutInput.getAttribute('value');
  }

  async setLifeEventsInput(lifeEvents) {
    await this.lifeEventsInput.sendKeys(lifeEvents);
  }

  async getLifeEventsInput() {
    return this.lifeEventsInput.getAttribute('value');
  }

  async setHobbiesInput(hobbies) {
    await this.hobbiesInput.sendKeys(hobbies);
  }

  async getHobbiesInput() {
    return this.hobbiesInput.getAttribute('value');
  }

  async setFeaturedInput(featured) {
    await this.featuredInput.sendKeys(featured);
  }

  async getFeaturedInput() {
    return this.featuredInput.getAttribute('value');
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
    await this.setWorkInput('work');
    await waitUntilDisplayed(this.saveButton);
    await this.setEducationInput('education');
    await waitUntilDisplayed(this.saveButton);
    await this.setPlacesLivedInput('placesLived');
    await waitUntilDisplayed(this.saveButton);
    await this.setContactInfoInput('contactInfo');
    await waitUntilDisplayed(this.saveButton);
    await this.setWebSocialLinksInput('webSocialLinks');
    await waitUntilDisplayed(this.saveButton);
    await this.setBasicInfoInput('basicInfo');
    await waitUntilDisplayed(this.saveButton);
    await this.setRelationshipInfoInput('relationshipInfo');
    await waitUntilDisplayed(this.saveButton);
    await this.setFamilyInput('family');
    await waitUntilDisplayed(this.saveButton);
    await this.setDetailAboutInput('detailAbout');
    await waitUntilDisplayed(this.saveButton);
    await this.setLifeEventsInput('lifeEvents');
    await waitUntilDisplayed(this.saveButton);
    await this.setHobbiesInput('hobbies');
    await waitUntilDisplayed(this.saveButton);
    await this.setFeaturedInput('featured');
    await this.baseInfoSelectLastOption();
    await this.save();
    await waitUntilHidden(this.saveButton);
  }
}
