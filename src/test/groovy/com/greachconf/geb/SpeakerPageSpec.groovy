package com.greachconf.geb

import com.greachconf.Speaker
import geb.Browser
import groovy.util.logging.Slf4j
import spock.lang.IgnoreIf
import spock.lang.Specification

class SpeakerPageSpec extends Specification {

    @IgnoreIf({ !System.getProperty('geb.env') })
    def "test a speaker page is scrapped"() {

        when:
        def browser = new Browser()
        browser.go 'http://2017.greachconf.com/speakers/vladimir-orany/'
        def page = browser.page SpeakerPage
        def speaker = page as Speaker

        then:
        speaker
        speaker.name == 'Vladimir Orany'
        speaker.imgSrc
        speaker.about
        speaker.slug == 'vladimir-orany'
    }
}
