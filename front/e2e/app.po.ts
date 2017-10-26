import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.getVehicle('/');
  }

  getParagraphText() {
    return element(by.css('app-root h1')).getText();
  }
}
