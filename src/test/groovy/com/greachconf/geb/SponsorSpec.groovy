package com.greachconf.geb

import com.greachconf.PlistGenerator
import com.greachconf.Sponsor
import spock.lang.Specification

class SponsorSpec extends Specification {

    def "test generated sponosrs plist"() {

        when:
        def sponsors = [
                new Sponsor(kind: 'Diamond', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2016/10/OCI.png', name: 'Object Computing Inc', url: 'http://www.objectcomputing.com'),
                new Sponsor(kind: 'Platinum', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2016/10/Salenda_10Years.png', name: 'Salenda', url: 'https://www.salenda.es/'),
                new Sponsor(kind: 'Platinum', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2016/10/Infortelecom.png', name: 'Infortelecom', url: 'https://infortelecom.es/en'),
                new Sponsor(kind: 'Gold', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2014/01/osoco.png', name: 'Osoco', url: 'http://osoco.es/'),
                new Sponsor(kind: 'Gold', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2014/01/autentia.png', name: 'Autentia', url: 'https://www.autentia.com/'),
                new Sponsor(kind: 'Bronze', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2014/01/virtual.png', name: 'Virtual Software', url: 'http://www.virtualsw.com/'),
                new Sponsor(kind: 'Bronze', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2014/01/aluman.png', name: 'Grupo Aluman', url: 'http://www.grupoaluman.com/'),
                new Sponsor(kind: 'Bronze', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2017/01/energized-word.png', name: 'Energized Work', url: 'https://www.energizedwork.com/'),
                new Sponsor(kind: 'Bronze', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2014/01/kaleidos.png', name: 'Kaleidos', url: 'http://kaleidos.net/'),
                new Sponsor(kind: 'Bronze', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2014/01/diputacio-tarragona.png', name: 'Diputació Tarragona', url: 'http://www.diputaciodetarragona.cat'),
                new Sponsor(kind: 'Bronze', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2014/01/pronoide-logo.png', name: 'Pronoide', url: 'http://www.pronoide.com/'),
                new Sponsor(kind: 'Bronze', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2016/10/Koliseo.png', name: 'Koliseo', url: 'https://www.koliseo.com/'),
                new Sponsor(kind: 'Collaborators', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2017/03/zeroturnaround.png', name: 'ZeroTurnaround', url: 'https://zeroturnaround.com/'),
                new Sponsor(kind: 'Collaborators', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2017/01/groovy-calamari.png', name: 'Groovy Calamari', url: 'http://groovycalamari.com/'),
                new Sponsor(kind: 'Collaborators', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2014/01/jetbrains.png', name: 'JetBrains', url: 'https://www.jetbrains.com/'),
                new Sponsor(kind: 'Collaborators', imageUrl: 'http://2017.greachconf.com/wp-content/uploads/2014/01/madrid-gug.png', name: 'Madrid-GUG', url: 'http://www.madridgug.com'),

        ]
        new PlistGenerator().saveSponsors(sponsors)

        then:
        true
    }
}
