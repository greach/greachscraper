package com.greachconf.geb

import com.greachconf.GreachWebsite
import com.greachconf.PlistGenerator
import com.greachconf.Speaker
import com.greachconf.Sponsor
import com.greachconf.Talk
import geb.Browser
import spock.lang.Specification

class AgendaPageSpec extends Specification {

    def "test agenda is scrapped"() {
        when:
        def browser = new Browser()
        browser.go 'http://2017.greachconf.com/agenda/'
        def page = browser.page AgendaPage
        def urls = page.talksUrls()

        then:
        urls.size() >= 20

        when:
        def talks = [] as List<Talk>
        urls.each { String talkUrl ->
            browser.go talkUrl
            page = browser.page TalkPage
            Talk talk = page as Talk
            talks << talk
        }

        then:
        talks.size() >= 20

        when:
        def speakers = [] as List<Speaker>

        talks.each { Talk talk ->
            talk.speakersSlugs.each { String speakerSlug ->
                browser.go "${GreachWebsite.speakerRootUrl()}/${speakerSlug}/"
                page = browser.page SpeakerPage
                def speaker = page as Speaker
                speakers << speaker
            }
        }

        then:
        speakers.size() >= 20

        when:
        new PlistGenerator().savePlist(speakers, talks)

        then:
        true
    }
}

