package com.dustin.verbix

import com.dustin.model.Tense
import com.dustin.model.Verb
import org.ccil.cowan.tagsoup.Parser

/**
 * Created by djohnson on 5/3/16.
 */
class VerbixVerbParser {

    private String text

    def VerbixVerbParser(InputStream is) {
        this.text = is.text;
    }

    Verb parse() {
        def slurper = new XmlSlurper(new Parser())
        def page = slurper.parseText(text)

//        <table border="1" cellpadding="2" cellspacing="0" width="600">
//        <tr>
//        <td><b>Infinitivo: </b><span class="normal">ser</span><br>
//        <b>Participio:</b> <span class="normal">sido</span><br>
//        <b>Gerundio:</b>
//            <span class="normal">siendo</span><br>
//        </td>
//        <td>&nbsp;</td>
//        </tr>
//    <tr>
//        <td valign="top" width="50%"><b>Indicativo</b></td>
//        <td valign="top" width="50%"><b>Subjuntivo</b></td>
//    </tr>
//        <tr>
        def table = page.'**'.findAll { it.@border.toString() == '1' }
        assert table.size() == 1

        def row = table.iterator().next().tr[0]
        println row
//        verbtable.'**'.findAll { it.name() == 'div'}.each {
//            println it
//        }

        List<Tense> tenses = []
        return new Verb('', tenses)
    }
}
