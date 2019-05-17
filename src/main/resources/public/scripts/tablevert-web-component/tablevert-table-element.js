/*
 * Copyright 2019 conis Informationssysteme GmbH
 * SPDX-License-Identifier: Apache-2.0
 */

const ATTRIBUTE_TV_ID = 'data-tv-id';
const ATTRIBUTE_TV_QUERY = 'data-tv-query';

const MEDIATYPE_TV_HTML = 'application/vnd.org.tablevert.api.html+json';
const MEDIATYPE_TV_XLSX = 'application/vnd.org.tablevert.api.xlsx+json';


export class TablevertTableElement extends HTMLElement {

    constructor() {
        super();
        const shadow = this.attachShadow({mode: 'open'});
        const tvDiv = document.createElement('div');
        const tvForm = document.createElement('form');
        tvForm.setAttribute(ATTRIBUTE_TV_ID, this.id);
        tvForm.setAttribute(ATTRIBUTE_TV_QUERY, this.getAttribute(ATTRIBUTE_TV_QUERY));
        tvForm.innerText = 'Table is being loaded...';
        tvDiv.appendChild(tvForm);
        shadow.appendChild(tvDiv);
        this.tvForm = tvForm;
    }

    connectedCallback() {
        let formData = {};
        formData.queryName = this.tvForm.getAttribute(ATTRIBUTE_TV_QUERY);
        formData.type = 'HTML';
        fetch('/tablevert', {
            method: 'POST',
            headers: {
                'Accept': MEDIATYPE_TV_HTML,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        }).then(response => {
            response.text().then(x => this.tvForm.innerHTML = x); //.insertAdjacentHTML('beforeend', x));
        }).catch(error => alert('Error: ' + error));
    }
}
