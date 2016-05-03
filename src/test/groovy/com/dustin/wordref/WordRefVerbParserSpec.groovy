package com.dustin.wordref

import spock.lang.Specification
/**
 * Created by djohnson on 5/3/16.
 */
class WordRefVerbParserSpec extends Specification {

    def 'should parse all tenses'() {

        WordRefVerbParser parser = new WordRefVerbParser(this.getClass().getResourceAsStream('/wordref/ser.html'))
        def verb = parser.parse()

        expect:
        verb.infinitive == 'ser'
//        parser.tenses.size() == 10
    }

}
