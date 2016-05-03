package com.dustin.wordref

import com.dustin.model.Tense
import com.dustin.model.Verb
import org.ccil.cowan.tagsoup.Parser

/**
 * Created by djohnson on 5/3/16.
 */
class WordRefVerbParser {

    private String text

    def WordRefVerbParser(InputStream is) {
        this.text = is.text;
    }

    Verb parse() {
        def slurper = new XmlSlurper(new Parser())
        def page = slurper.parseText(text)

        def header = page.'**'.findAll { it.@id == 'cheader' }
        def infinitive = header.td.each {
            println it.text() + "\n"
        }

        List<Tense> tenses = []
        return new Verb(infinitive, tenses)
    }
}
