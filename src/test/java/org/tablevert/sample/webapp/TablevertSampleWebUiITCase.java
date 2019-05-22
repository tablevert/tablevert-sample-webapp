/*
 * Copyright 2019 conis Informationssysteme GmbH
 * SPDX-License-Identifier: Apache-2.0
 */

package org.tablevert.sample.webapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.tablevert.sample.webapp.config.SeleniumConfig;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("DEV")
class TablevertSampleWebUiITCase {

    private static final String TAGNAME_TABLEVERT = "first-table";

    @Autowired
    private SeleniumConfig seleniumConfig;

    @LocalServerPort
    private int serverPort;

    private String rootEndpoint;

    @BeforeEach
    void setup() {
        this.rootEndpoint = String.format("http://localhost:%s/", serverPort);
    }

    @AfterEach
    void closeWebDriver() {
        WebDriver driver = seleniumConfig.getFirefoxDriver();
        if (driver != null) {
            driver.close();
        }
    }

    @Test
    void loadsInitialTableInFirefox() throws InterruptedException {

        WebDriver driver = seleniumConfig.getFirefoxDriver();
        driver.get(rootEndpoint);
        Thread.sleep(3000);

        WebElement div = Assertions.assertDoesNotThrow(() -> tablevertTableDiv(driver, TAGNAME_TABLEVERT));
        Assertions.assertTrue(div.findElements(By.tagName("table")).size() > 0);

    }

    private WebElement tablevertTableDiv(WebDriver driver, String tablevertElementId) throws JavascriptException {
        return (WebElement) ((JavascriptExecutor) driver).executeScript(
                "return document.querySelector(arguments[0]).shadowRoot.firstChild", "#" + tablevertElementId);
    }

}
