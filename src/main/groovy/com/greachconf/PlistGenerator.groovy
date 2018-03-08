package com.greachconf

import com.dd.plist.NSArray
import com.dd.plist.NSDate
import com.dd.plist.NSDictionary
import com.dd.plist.PropertyListParser

class PlistGenerator {

    static final String PLIST_NAME =  'botconf.plist'
    static final String SPONSORS_PLIST_NAME =  'sponsors.plist'

    void saveSponsors(List<Sponsor> sponsors) {
        try {

            NSArray arr = new NSArray(sponsors.size())

            int i = 0
            sponsors.each { Sponsor sponsor ->
                NSDictionary sponsorDict = new NSDictionary()
                sponsorDict.put('kind', sponsor.kind)
                sponsorDict.put('url', sponsor.url)
                sponsorDict.put('image_url', sponsor.imageUrl)
                arr.setValue(i, sponsorDict)
                i++
            }

            def f = new File(SPONSORS_PLIST_NAME)
            PropertyListParser.saveAsXML(arr, f.newOutputStream())

        } catch(Exception ex) {
            println ex.getMessage()
        }
    }

    void savePlist(List<Speaker> speakers, List<Talk> talks) {

        speakers.findAll { it.twitter == null }.each { println "Speaker without Twitter set: ${it.slug}"}

        Long primaryKey = 2017001

        List<String> roomNames = talks.collect { it.location }.unique()
        List<Map> trackNames = talks.collect { it.tracks }.flatten().unique()

        Map<String, String> twitterSpeakerSlug = [:]

        speakers.each { Speaker speaker ->
            twitterSpeakerSlug[speaker.slug] = speaker.twitter
        }

        try {
            NSDictionary root = new NSDictionary();

            NSDictionary metadata = new NSDictionary()

            metadata.put("lastUpdated", new NSDate(NSDate.makeDateString(new Date())))
            root.put("metadata", metadata);

            int column = 1

            NSDictionary sessions = new NSDictionary()

            trackNames.each { String track ->

                int i = 0
                talks.each { Talk talk ->
                    if ( talk.tracks ) {
                        NSDictionary sessionDict = new NSDictionary()
                        sessionDict.put('primaryKey', primaryKey)
                        sessionDict.put('active', talk.active)
                        sessionDict.put('date', talk.dateStart)
                        sessionDict.put('duration', talk.duration)
                        sessionDict.put('trackId', trackNames.indexOf(talk.tracks.first()))
                        sessionDict.put('column', column)
                        sessionDict.put('sessionNumber', "${track}-${i}")
                        sessionDict.put('title', talk.name)
                        sessionDict.put('sessionDescription', talk.about)
                        sessionDict.put('presenters', talk.speakersSlugs.collect { twitterSpeakerSlug[it] } )
                        sessionDict.put('roomId', roomNames.indexOf(talk.location))
                        sessions.put(talk.slug, sessionDict)
                        i++
                        primaryKey++
                    }
                }
            }

            root.put("sessions", sessions)

            NSDictionary people = new NSDictionary()

            speakers.each { Speaker speaker ->
                NSDictionary speakerDict = new NSDictionary()
                speakerDict.put('primaryKey', primaryKey)
                speakerDict.put('active', speaker.active)
                speakerDict.put('first', speaker.firstName)
                speakerDict.put('last', speaker.lastName)
                speakerDict.put('twitter', speaker.twitter)
                speakerDict.put('bio', speaker.about)
                speakerDict.put('imageUrl', speaker.imgSrc)
                people.put(speaker.twitter, speakerDict)
                primaryKey++
            }

            root.put("people", people);

            NSArray rooms = new NSArray(roomNames.size())
            for(int i=0;i < roomNames.size();i++) {
                NSDictionary roomDict = new NSDictionary()
                roomDict.put('name',roomNames[i])
                roomDict.put('image','')
                rooms.array[i] = roomDict
            }
            root.put("rooms", rooms);

            def tracks = new NSArray(trackNames.size())
            for(int i = 0; i < trackNames.size(); i++) {
                if(trackNames[i] instanceof String) {
                    tracks.setValue(i, trackNames[i])

                } else if(trackNames[i] instanceof Map) {
                    tracks.setValue(i, trackNames[i].name)
                }
            }
            root.put("tracks", tracks);

            def f = new File(PLIST_NAME)
            PropertyListParser.saveAsXML(root, f.newOutputStream());

        } catch(Exception ex) {
            println ex.getMessage()
        }

    }

}
