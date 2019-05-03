/*
 * Copyright 2019 conis Informationssysteme GmbH
 * SPDX-License-Identifier: Apache-2.0
 */

package org.tablevert.sample.webapp;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TablevertSampleController {

    @GetMapping("/")
    public String getDataCrack() {
        return "data-crack";
    }

    @PostMapping("/tablevert")
    public ResponseEntity processTablevertRequest() {
        return ResponseEntity.badRequest().body("Muss wirklich besser werden!");
    }

}
