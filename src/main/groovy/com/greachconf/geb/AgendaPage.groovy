package com.greachconf.geb

import geb.Page

class AgendaPage extends Page {

    static content = {
        talkLinkElements(required: false, wait: true) { $('.session a.title') }
    }

    List<String> talksUrls() {
        talkLinkElements.collect { it.getAttribute('href') }.unique()
    }
}
