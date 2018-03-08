package com.greachconf

import com.greachconf.geb.AgendaPage
import com.greachconf.geb.SpeakerPage
import com.greachconf.geb.TalkPage
import geb.Browser

class Main {

    static void main(String[] args) {

        def browser = new Browser()
        browser.go GreachWebsite.agendaUrl()
        def page = browser.page AgendaPage
        def urls = page.talksUrls()

        def talks = [] as List<Talk>
        urls.each { String talkUrl ->
            browser.go talkUrl
            page = browser.page TalkPage
            Talk talk = page as Talk
            talks << talk
        }

        def speakers = [] as List<Speaker>

        talks.each { Talk talk ->
            talk.speakersSlugs.each { String speakerSlug ->
                browser.go "${GreachWebsite.speakerRootUrl()}/${speakerSlug}/"
                page = browser.page SpeakerPage
                def speaker = page as Speaker
                speakers << speaker
            }
        }

        new PlistGenerator().savePlist(speakers, talks)
    }
}