/*
 * Copyright 2019 conis Informationssysteme GmbH
 * SPDX-License-Identifier: Apache-2.0
 */

package org.tablevert.sample.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
class TablevertSampleController {

    private static final String MEDIATYPE_APPLICATION_OPENXML_SPREADSHEET = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String MEDIATYPE_TV_HTML = "application/vnd.org.tablevert.api.html+json";
    private static final String MEDIATYPE_TV_XLSX = "application/vnd.org.tablevert.api.xlsx+json";

    private final TablevertRequestService tablevertRequestService;

    @Autowired
    TablevertSampleController(TablevertRequestService tablevertRequestService) {
        this.tablevertRequestService = tablevertRequestService;
    }

    @GetMapping("/")
    String getDataCrack() {
        return "data-crack";
    }

    @PostMapping(value = "/tablevert", produces = MEDIATYPE_TV_HTML)
    ResponseEntity tablevertToHtml(@RequestBody TablevertRequest tvRequest) {
        ByteArrayResource byteArrayResource = new ByteArrayResource(tablevertRequestService.fetchHtml(tvRequest));
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .contentLength(byteArrayResource.contentLength())
                .body(byteArrayResource);
    }

    @PostMapping(value = "/tablevert", produces = MEDIATYPE_TV_XLSX)
    ResponseEntity tablevertToXlsx(@RequestBody TablevertRequest tvRequest) {
        ByteArrayResource byteArrayResource = new ByteArrayResource(tablevertRequestService.fetchXlsx(tvRequest));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=result.xlsx")
                .header(HttpHeaders.CONTENT_TYPE, MEDIATYPE_APPLICATION_OPENXML_SPREADSHEET)
                .contentLength(byteArrayResource.contentLength())
                .body(byteArrayResource);
    }

}
