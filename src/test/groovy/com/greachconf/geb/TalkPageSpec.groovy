package com.greachconf.geb

import com.greachconf.Talk
import geb.Browser
import spock.lang.IgnoreIf
import spock.lang.Specification

class TalkPageSpec extends Specification {

    @IgnoreIf({ !System.getProperty('geb.env') })
    def "test a talk page is scrapped"() {

        when:
        def browser = new Browser()
        browser.go 'http://2017.greachconf.com/sessions/make-concurrency-groovy-again/'
        def page = browser.page TalkPage
        def talk = page as Talk

        println talk.about

        then:
        talk
        talk.name == 'Make concurrency groovy again'
        talk.speakersSlugs == ['alonso-torres']
        talk.tracks == ['Track 2']
        talk.location == 'Luchana theatre'
        talk.dateStart == new Date(2017 - 1900, 2, 31, 10, 00)
        talk.dateEnd == new Date(2017 - 1900, 2, 31, 10, 45)
        talk.about

        when:
        browser.go 'http://2017.greachconf.com/sessions/excel-in-groovy/'
        page = browser.page TalkPage
        talk = page as Talk

        then:
        talk.about.indexOf('IntelliJ IDEA')

    }
}
