package com.greachconf

import groovy.transform.CompileStatic

@CompileStatic
class GreachWebsite {
    static final String WEBSITE_URL = 'http://2018.greachconf.com'
    static final String SPEAKER_PATH = 'speakers'
    static final String TALK_PATH = 'sessions'
    static final String AGENDA_PATH = 'agenda'

    static String agendaUrl() {
        "${WEBSITE_URL}/${AGENDA_PATH}/"
    }

    static String speakerRootUrl() {
        "${WEBSITE_URL}/${SPEAKER_PATH}/"
    }

    static String talkRootUrl() {
        "${WEBSITE_URL}/${TALK_PATH}/"
    }
}
