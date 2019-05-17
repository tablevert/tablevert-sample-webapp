/*
 * Copyright 2019 conis Informationssysteme GmbH
 * SPDX-License-Identifier: Apache-2.0
 */

package org.tablevert.sample.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.tablevert.sample.webapp.config.TablevertApiDetails;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
class TablevertRequestService {

    public static final String TABLEVERT_API_VERSION = "1.0.0";

    private static final String VERSIONED_MEDIATYPE_TV_HTML = "application/vnd.org.tablevert.api.html.v" + TABLEVERT_API_VERSION + "+json";
    private static final String VERSIONED_MEDIATYPE_TV_XLSX = "application/vnd.org.tablevert.api.xlsx.v" + TABLEVERT_API_VERSION + "+json";


    private final TablevertApiDetails connectionDetails;
    private final WebClient tablevertClient;

    @Autowired
    TablevertRequestService(TablevertApiDetails connectionDetails, WebClient.Builder webClientBuilder) {
        this.connectionDetails = connectionDetails;
        this.tablevertClient = webClientBuilder.baseUrl(tablevertBaseUrl()).build();
    }

    byte[] fetchHtml(TablevertRequest tvRequest) {
        return fetch(tvRequest, VERSIONED_MEDIATYPE_TV_HTML);
    }

    byte[] fetchXlsx(TablevertRequest tvRequest) {
        return fetch(tvRequest, VERSIONED_MEDIATYPE_TV_XLSX);
    }

    private byte[] fetch(TablevertRequest tvRequest, String mediaType) {
        byte[] result = tablevertClient
                .post()
                .uri(connectionDetails.getEndpoints().getTvrequest())
                .header(HttpHeaders.ACCEPT, mediaType)
                .header(HttpHeaders.AUTHORIZATION, base64Authorization())
                .syncBody(tvRequest)
                .retrieve()
                .bodyToMono(byte[].class)
                .block();
        return result;
    }

    private String tablevertBaseUrl() {
        return connectionDetails.getProtocol()
                + "://"
                + connectionDetails.getHost()
                + ":"
                + connectionDetails.getPort()
                + connectionDetails.getEndpoints().getBase();
    }

    private String base64Authorization() {
        String plainAuthorization = connectionDetails.getUser().getName() + ":" + connectionDetails.getUser().getSecret();
        return "Basic " + Base64.getEncoder().encodeToString(plainAuthorization.getBytes(StandardCharsets.UTF_8));
    }


}
