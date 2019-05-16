/*
 * Copyright 2019 conis Informationssysteme GmbH
 * SPDX-License-Identifier: Apache-2.0
 */

package org.tablevert.sample.webapp;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
class TablevertRequestService {


    byte[] fetchXlsx() {
//        RestTemplate restTemplate = new RestTemplate();
        WebClient webClient = WebClient.create("http://localhost:8080");
        byte[] result = webClient
                .post()
                .uri("/tvrequest")
               // .body()
                .retrieve()
                .bodyToMono(byte[].class)
                .block();
        return result;
    }

}
