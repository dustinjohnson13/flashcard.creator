package com.dustin.verbix

import com.dustin.model.Conjugation
import com.dustin.model.Mood
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

        def tableElem = page.'**'.findAll { it.@border.toString() == '1' }
        assert tableElem.size() == 1

        def table = tableElem.iterator().next()
        def row = table.tr[0]
        def infinitive = getRowValue(row, 'Infinitivo:')
        def participle = getRowValue(row, 'Participio:')
        def gerund = getRowValue(row, 'Gerundio:')

        def moodRow = table.tr[1]

        def indicativeIndex = getIndex(moodRow.td, { it.b[0].text() == 'Indicativo' })
        def subjunctiveIndex = getIndex(moodRow.td, { it.b[0].text() == 'Subjuntivo' })

        def conditionalIndex = getIndex(table.tr[3].td, { it.b[0].text() == 'Condicional' })
        def imperativeIndex = getIndex(table.tr[3].td, { it.b[0].text() == 'Imperativo' })

        def moodRows = ['Indicativo': table.tr[2], 'Subjuntivo': table.tr[2], 'Condicional': table.tr[4], 'Imperativo': table.tr[4]]
        def moodIndexes = ['Indicativo': indicativeIndex, 'Subjuntivo': subjunctiveIndex, 'Condicional': conditionalIndex, 'Imperativo': imperativeIndex]

        List<Mood> moods = []

        moodIndexes.each { moodName, index ->
            List<Tense> tenses = []

            def tensesRow = moodRows[moodName]

            def mood = tensesRow.td[index].table

            if (mood.isEmpty()) { // Imperative mood doesn't have a table
                mood = tensesRow.td[index]

                def tenseName = moodName

                def conjugations = []
                def spans = mood.'**'.findAll { node -> node.name() == 'span' }.collect { it.text().trim() }.findAll {
                    !it.isEmpty()
                }
                spans.eachWithIndex { word, idx ->

                    if (idx % 2 == 1) {

                        def person = spans[idx - 1]
                        def conjugated = word

                        conjugations += new Conjugation(person, conjugated)
                    }
                }
                tenses += new Tense(tenseName, conjugations)

            } else {
                mood.tr.each { tenseRow ->
                    def tense = tenseRow.td

                    tense.each {
                        def tenseName = it.b[0].text().trim()

                        if (tenseName == '') {
                            return
                        }

                        def conjugations = []
                        def spans = it.'**'.findAll { node -> node.name() == 'span' }
                        spans.eachWithIndex { word, idx ->

                            if (idx % 2 == 1) {

                                def person = spans[idx - 1].text()
                                def conjugated = word.text()

                                conjugations += new Conjugation(person, conjugated)
                            }
                        }
                        tenses += new Tense(tenseName, conjugations)
                    }
                }
            }

            moods += new Mood(moodName, tenses)
        }

        return new Verb(infinitive, participle, gerund, moods)
    }

    private String getRowValue(def row, String expectedName) {
        def column = row.td[0]

        def index = getIndex(column.b, { it.text().trim() == expectedName })

        return column.span[index].text()
    }

    private int getIndex(def nodes, def matcher) {
        def found = nodes.find(matcher)
        assert found

        def index = nodes.list().indexOf(found)
        assert index > -1

        return index
    }
}
