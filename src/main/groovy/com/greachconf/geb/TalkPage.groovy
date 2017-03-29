package com.greachconf.geb

import com.greachconf.GreachWebsite
import com.greachconf.Speaker
import com.greachconf.Talk
import geb.Page

class TalkPage extends Page {
    final String DATE_FORMAT = 'MMM dd, yyyy HH:mm'

    static content = {
        nameEl(required: false) { $('.heading h1', 0) }
        locationEl(required: false) { $('.session span.location strong', 0) }
        dateEl(required: false) { $('.session span.date strong', 0) }
        timeEl(required: false) { $('.session span.time strong', 0) }
        trackElements(required: false) { $('.container span.single-session-link') }
        speakerElements(required: false) { $('.speakers-thumbs a') }
        aboutElements (required: false) { $('.container .row .col-md-8') }
    }

    static String urlToTalkSlug(String url) {
        url.replaceAll(GreachWebsite.talkRootUrl(), '').replaceAll('/', '')
    }

    private String slug() {
        urlToTalkSlug(this.browser.currentUrl)
    }

    private String name() {
        nameEl.text()
    }

    private List<String> speakersSlugs() {
        speakerElements.collect { SpeakerPage.urlToSpeakerSlug(it.getAttribute('href')) }
    }

    private List<String> tracks() {
        trackElements.collect { it.text() }
    }

    private String location() {
        locationEl.text()
    }

    private String date() {
        dateEl.text()
    }

    private String time() {
        timeEl.text()
    }

    private String about() {
        String text = aboutElements.collect { it.text() }.findAll { it }.join('\n')
        tracks().each { track ->
            text = text.replaceAll(track, '')
        }
        text
    }

    private Talk asTalk() {
        def talk = new Talk(name: name(),
                location: location(),
                tracks: tracks(),
                speakersSlugs: speakersSlugs(),
                about: about(),
                slug: slug())

        def date = date()
        def time = time()
        def arr = time?.split(' - ')
        if ( arr ) {
            def timeStart = arr[0]
            def dateStart = new Date().parse(DATE_FORMAT, "${date} ${timeStart}")
            talk.dateStart = dateStart
        }

        if ( arr?.size() >= 2 ) {
            def timeEnd = arr[1]
            def dateEnd = new Date().parse(DATE_FORMAT, "${date} ${timeEnd}")
            talk.dateEnd = dateEnd
        }

        talk
    }

    Object asType(Class clazz) {
        if (clazz == Talk) {
            return asTalk()
        }
        else {
            super.asType(clazz)
        }
    }
}
