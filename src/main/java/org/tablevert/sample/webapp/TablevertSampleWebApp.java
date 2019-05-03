/*
 * Copyright 2019 conis Informationssysteme GmbH
 * SPDX-License-Identifier: Apache-2.0
 */

package org.tablevert.sample.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TablevertSampleWebApp {
    public static void main(String[] args) {
        new SpringApplication(TablevertSampleWebApp.class).run();
    }
}
