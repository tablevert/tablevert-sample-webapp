/*
 * Copyright 2019 conis Informationssysteme GmbH
 * SPDX-License-Identifier: Apache-2.0
 */

package org.tablevert.sample.webapp;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TablevertSampleController {

    @GetMapping("/")
    public String getDataCrack() {
        return "data-crack";
    }

    @PostMapping(value = "/tablevert", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity processTablevertRequest(@RequestBody TablevertRequest tvRequest) {
        System.out.println(tvRequest.getQueryName() == null ? "ömpf" : tvRequest.getQueryName());
        return ResponseEntity.badRequest()
                .contentType(MediaType.TEXT_PLAIN)
                .body("Muss wirklich besser werden!");
    }

}
