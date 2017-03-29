package com.greachconf

import groovy.time.TimeCategory
import groovy.transform.CompileStatic
import groovy.transform.TypeCheckingMode

@CompileStatic
class Talk {
    Long primaryKey
    String name
    String about
    String location
    Date dateStart
    Date dateEnd
    List<String> tracks
    List<String> speakersSlugs
    boolean active = true
    String slug
    int minutes

    @CompileStatic(TypeCheckingMode.SKIP)
    int getDuration() {
        use(TimeCategory) {
            def duration = dateEnd - dateStart
            duration.toMilliseconds()  / 1000 / 60
        } as int
    }
}
