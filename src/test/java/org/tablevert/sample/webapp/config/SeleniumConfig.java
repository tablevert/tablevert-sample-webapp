/*
 * Copyright 2019 conis Informationssysteme GmbH
 * SPDX-License-Identifier: Apache-2.0
 */

package org.tablevert.sample.webapp.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Component;


@Component
public class SeleniumConfig {

    private static final String DRIVERS_DIR = "src/test/resources/drivers/";

    private WebDriver firefoxDriver;
    private String osDriversDir;

    public SeleniumConfig() {
        // TODO: Check and adapt to OS
        this.osDriversDir = DRIVERS_DIR + "mac/";
    }

    public WebDriver getFirefoxDriver() {
        if (firefoxDriver == null) {
            initFirefoxDriver();
        }
        return firefoxDriver;
    }

    private void initFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", osDriversDir + "geckodriver");
        firefoxDriver = new FirefoxDriver();
    }

}
