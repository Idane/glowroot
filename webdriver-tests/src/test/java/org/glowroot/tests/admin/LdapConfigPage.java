/*
 * Copyright 2016-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.glowroot.tests.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.glowroot.tests.util.Page;

import static org.openqa.selenium.By.xpath;

public class LdapConfigPage extends Page {

    public LdapConfigPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getHostTextField() {
        return getWithWait(xpath("//div[@gt-label='Host']//input"));
    }

    public WebElement getPortTextField() {
        return getWithWait(xpath("//div[@gt-label='Port']//input"));
    }

    public void clickUseSslCheckBox() {
        clickWithWait(xpath("//div[@gt-label='SSL']//label"));
    }

    public boolean getUseSslCheckBoxValue() {
        return getWithWait(xpath("//div[@gt-label='SSL']//label//input")).isSelected();
    }

    public WebElement getUsernameTextField() {
        return getWithWait(xpath("//div[@gt-label='Username']//input"));
    }

    public WebElement getPasswordTextField() {
        return getWithWait(xpath("//input[@ng-model='password']"));
    }

    public WebElement getUserBaseDnTextField() {
        return getWithWait(xpath("//div[@gt-label='User base DN']//input"));
    }

    public WebElement getUserSearchFilterTextField() {
        return getWithWait(xpath("//div[@gt-label='User search filter']//input"));
    }

    public WebElement getGroupBaseDnTextField() {
        return getWithWait(xpath("//div[@gt-label='Group base DN']//input"));
    }

    public WebElement getGroupSearchFilterTextField() {
        return getWithWait(xpath("//div[@gt-label='Group search filter']//input"));
    }

    public void clickSaveButton() {
        clickWithWait(xpath("//button[normalize-space()='Save changes']"));
    }
}
