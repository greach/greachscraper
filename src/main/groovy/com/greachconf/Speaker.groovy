package com.greachconf

import groovy.transform.CompileStatic

@CompileStatic
class Speaker {
    Long primaryKey
    String slug
    String name
    String about
    String imgSrc
    String twitter
    boolean active = true

    String getFirstName() {
        def arr = name.split(' ')
        if ( arr ) {
            return arr.first()
        }
    }

    String getTwitter() {
        if ( this.twitter == null ) {
            return SpeakerTwitter.SPEAKER_TWITTER[name]

        }
    }

    String getLastName() {
        List<String> arr = name.split(' ') as List<String>
        if ( arr ) {
            arr.remove(0)
            return arr.join(' ')
        }
    }
}
