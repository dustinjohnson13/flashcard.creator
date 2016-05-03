package com.dustin.verbix

import spock.lang.Specification
/**
 * Created by djohnson on 5/3/16.
 */
class VerbixVerbParserSpec extends Specification {

    def 'should parse all tenses'() {

        VerbixVerbParser parser = new VerbixVerbParser(this.getClass().getResourceAsStream('/verbix/ser.html'))
        def verb = parser.parse()

        expect:
        verb.infinitive == 'ser'
//        parser.tenses.size() == 10
    }

}
