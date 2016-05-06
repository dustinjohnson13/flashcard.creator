package com.dustin.verbix

import com.dustin.model.Conjugation
import com.dustin.model.Mood
import com.dustin.model.Tense
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
        verb.participle == 'sido'
        verb.gerund == 'siendo'
        verb.moods == [
                new Mood('Indicativo', [
                        new Tense("Presente", [new Conjugation("yo", "soy"), new Conjugation("tú", "eres"), new Conjugation("él", "es"), new Conjugation("nosotros", "somos"), new Conjugation("vosotros", "sois"), new Conjugation("ellos", "son")]),
                        new Tense("Perfecto", [new Conjugation("yo", "he sido"), new Conjugation("tú", "has sido"), new Conjugation("él", "ha sido"), new Conjugation("nosotros", "hemos sido"), new Conjugation("vosotros", "habéis sido"), new Conjugation("ellos", "han sido")]),
                        new Tense("Imperfecto", [new Conjugation("yo", "era"), new Conjugation("tú", "eras"), new Conjugation("él", "era"), new Conjugation("nosotros", "éramos"), new Conjugation("vosotros", "erais"), new Conjugation("ellos", "eran")]),
                        new Tense("Pluscuamperfecto", [new Conjugation("yo", "había sido"), new Conjugation("tú", "habías sido"), new Conjugation("él", "había sido"), new Conjugation("nosotros", "habíamos sido"), new Conjugation("vosotros", "habíais sido"), new Conjugation("ellos", "habían sido")]),
                        new Tense("Pret�rito", [new Conjugation("yo", "fui"), new Conjugation("tú", "fuiste"), new Conjugation("él", "fue"), new Conjugation("nosotros", "fuimos"), new Conjugation("vosotros", "fuisteis"), new Conjugation("ellos", "fueron")]),
                        new Tense("Pret�rito anterior", [new Conjugation("yo", "hube sido"), new Conjugation("tú", "hubiste sido"), new Conjugation("él", "hubo sido"), new Conjugation("nosotros", "hubimos sido"), new Conjugation("vosotros", "hubisteis sido"), new Conjugation("ellos", "hubieron sido")]),
                        new Tense("Futuro", [new Conjugation("yo", "seré"), new Conjugation("tú", "serás"), new Conjugation("él", "será"), new Conjugation("nosotros", "seremos"), new Conjugation("vosotros", "seréis"), new Conjugation("ellos", "serán")]),
                        new Tense("Futuro anterior", [new Conjugation("yo", "habré sido"), new Conjugation("tú", "habrás sido"), new Conjugation("él", "habrá sido"), new Conjugation("nosotros", "habremos sido"), new Conjugation("vosotros", "habréis sido"), new Conjugation("ellos", "habrán sido")])]),

                new Mood('Subjuntivo', [
                        new Tense("Presente", [new Conjugation("yo", "sea"), new Conjugation("tú", "seas"), new Conjugation("él", "sea"), new Conjugation("nosotros", "seamos"), new Conjugation("vosotros", "seáis"), new Conjugation("ellos", "sean")]),
                        new Tense("Perfecto", [new Conjugation("yo", "haya sido"), new Conjugation("tú", "hayas sido"), new Conjugation("él", "haya sido"), new Conjugation("nosotros", "hayamos sido"), new Conjugation("vosotros", "hayáis sido"), new Conjugation("ellos", "hayan sido")]),
                        new Tense("Imperfecto", [new Conjugation("yo", "fuera"), new Conjugation("tú", "fueras"), new Conjugation("él", "fuera"), new Conjugation("nosotros", "fuéramos"), new Conjugation("vosotros", "fuerais"), new Conjugation("ellos", "fueran"), new Conjugation("yo", "fuese"), new Conjugation("tú", "fueses"), new Conjugation("él", "fuese"), new Conjugation("nosotros", "fuésemos"), new Conjugation("vosotros", "fueseis"), new Conjugation("ellos", "fuesen")]),
                        new Tense("Pluscuamperfecto", [new Conjugation("yo", "hubiera sido"), new Conjugation("tú", "hubieras sido"), new Conjugation("él", "hubiera sido"), new Conjugation("nosotros", "hubiéramos sido"), new Conjugation("vosotros", "hubierais sido"), new Conjugation("ellos", "hubieran sido"), new Conjugation("yo", "hubiese sido"), new Conjugation("tú", "hubieses sido"), new Conjugation("él", "hubiese sido"), new Conjugation("nosotros", "hubiésemos sido"), new Conjugation("vosotros", "hubieseis sido"), new Conjugation("ellos", "hubiesen sido")]),
                        new Tense("Futuro", [new Conjugation("yo", "fuere"), new Conjugation("tú", "fueres"), new Conjugation("él", "fuere"), new Conjugation("nosotros", "fuéremos"), new Conjugation("vosotros", "fuereis"), new Conjugation("ellos", "fueren")]),
                        new Tense("Futuro anterior", [new Conjugation("yo", "hubiere sido"), new Conjugation("tú", "hubieres sido"), new Conjugation("él", "hubiere sido"), new Conjugation("nosotros", "hubiéremos sido"), new Conjugation("vosotros", "hubiereis sido"), new Conjugation("ellos", "hubieren sido")])]),
                new Mood('Condicional', [
                        new Tense("Condicional", [new Conjugation("yo", "sería"), new Conjugation("tú", "serías"), new Conjugation("él", "sería"), new Conjugation("nosotros", "seríamos"), new Conjugation("vosotros", "seríais"), new Conjugation("ellos", "serían")]),
                        new Tense("Condicional perfecto", [new Conjugation("yo", "habría sido"), new Conjugation("tú", "habrías sido"), new Conjugation("él", "habría sido"), new Conjugation("nosotros", "habríamos sido"), new Conjugation("vosotros", "habríais sido"), new Conjugation("ellos", "habrían sido")])]),
                new Mood('Imperativo', [
                        new Tense('Imperativo', [new Conjugation('(tú)', 'sé'), new Conjugation('(él)', 'sea'), new Conjugation('(nosotros)', 'seamos'), new Conjugation('(vosotros)', 'sed'), new Conjugation('(ellos)', 'sean')])])
        ]
    }

}
