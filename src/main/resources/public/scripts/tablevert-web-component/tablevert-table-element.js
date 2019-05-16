/*
 * Copyright 2019 conis Informationssysteme GmbH
 * SPDX-License-Identifier: Apache-2.0
 */

const ATTRIBUTE_TV_ID = 'data-tv-id';
const ATTRIBUTE_TV_QUERY = 'data-tv-query';

export class TablevertTableElement extends HTMLElement {

    constructor() {
        super();
        const shadow = this.attachShadow({mode: 'open'});
        const tvDiv = document.createElement('div');
        const tvForm = document.createElement('form');
        tvForm.setAttribute(ATTRIBUTE_TV_ID, this.id);
        tvForm.setAttribute(ATTRIBUTE_TV_QUERY, this.getAttribute(ATTRIBUTE_TV_QUERY));
        tvForm.innerText = 'Element-ID: ' + tvForm.getAttribute(ATTRIBUTE_TV_ID) + ', Query: ' + tvForm.getAttribute(ATTRIBUTE_TV_QUERY) + '. Please wait for the functionality to be implemented...';
        tvDiv.appendChild(tvForm);
        shadow.appendChild(tvDiv);
        this.tvForm = tvForm;
    }

    connectedCallback() {
        let formData = {};
        formData.queryName = this.tvForm.getAttribute(ATTRIBUTE_TV_QUERY);
        fetch('/tablevert', {
            method: 'POST',
            headers: {
                'Accept': 'text/html',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        }).then(response => {
            response.text().then(x => alert(x));
        }).catch(error => alert('Error: ' + error));
    }
}
