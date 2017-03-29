package com.greachconf.geb

import com.greachconf.GreachWebsite
import com.greachconf.Speaker
import geb.Page

class SpeakerPage extends Page {

    static content = {
        nameEl(required: false) { $('.heading h1', 0) }
        imgEl(required: false) { $('.heading h1 img', 0) }
        aboutEl(required: false) { $('.container p', 0) }
    }

    static String urlToSpeakerSlug(String url) {
        url.replaceAll(GreachWebsite.speakerRootUrl(), '').replaceAll('/', '')
    }

    private String slug() {
        urlToSpeakerSlug(this.browser.currentUrl)
    }

    private String name() {
        nameEl.text()
    }

    private String imgSrc()  {
        imgEl.getAttribute('src')
    }

    private String about() {
        aboutEl.text()
    }

    Object asType(Class clazz) {
        if (clazz == Speaker) {
            return new Speaker(name: name(), imgSrc: imgSrc(), about: about(), slug: slug())
        }
        else {
            super.asType(clazz)
        }
    }
}
