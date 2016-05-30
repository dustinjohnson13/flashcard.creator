package com.dustin.service
/**
 * Created by djohnson on 5/27/16.
 */
class AnkiCSVCreator {

    public static void main(String[] args) {
    def input = """

Infinitivo:
Gerundio:
Participio:\thacer
haciendo
hecho\tDefinición\tEn Inglés
En Francés
En Portugués\t \t
Indicativo
presente
yo\thago
tú\thaces
él, ella, Ud.\thace
nosotros\thacemos
vosotros\thacéis
ellos, ellas, Uds.\thacen
vos\thacés
imperfecto
hacía
hacías
hacía
hacíamos
hacíais
hacían
hacías
pretérito
hice
hiciste
hizo
hicimos
hicisteis
hicieron
hiciste
futuro
haré
harás
hará
haremos
haréis
harán
harás
condicional
haría
harías
haría
haríamos
haríais
harían
harías
Tiempos compuestos comunes
pretérito perfecto
yo\the hecho
tú\thas hecho
él, ella, Ud.\tha hecho
nosotros\themos hecho
vosotros\thabéis hecho
ellos, ellas, Uds.\than hecho
vos\thas hecho
pluscuamperfecto
había hecho
habías hecho
había hecho
habíamos hecho
habíais hecho
habían hecho
habías hecho
futuro perfecto
habré hecho
habrás hecho
habrá hecho
habremos hecho
habréis hecho
habrán hecho
habrás hecho
condicional perfecto
habría hecho
habrías hecho
habría hecho
habríamos hecho
habríais hecho
habrían hecho
habrías hecho
Subjuntivo
presente
yo\thaga
tú\thagas
él, ella, Ud.\thaga
nosotros\thagamos
vosotros\thagáis
ellos, ellas, Uds.\thagan
vos\thagas, *hagás
imperfecto
hiciera o hiciese
hicieras o hicieses
hiciera o hiciese
hiciéramos o hiciésemos
hicierais o hicieseis
hicieran o hiciesen
hicieras o hicieses
futuro
hiciere
hicieres
hiciere
hiciéremos
hiciereis
hicieren
hicieres
Tiempos compuestos del subjuntivo
pretérito perfecto
yo\thaya hecho
tú\thayas hecho
él, ella, Ud.\thaya hecho
nosotros\thayamos hecho
vosotros\thayáis hecho
ellos, ellas, Uds.\thayan hecho
vos\thayas hecho
pluscuamperfecto
hubiera o hubiese hecho
hubieras o hubieses hecho
hubiera o hubiese hecho
hubiéramos o hubiésemos hecho
hubierais o hubieseis hecho
hubieran o hubiesen hecho
hubieras o hubieses hecho
futuro perfecto
hubiere hecho
hubieres hecho
hubiere hecho
hubiéremos hecho
hubiereis hecho
hubieren hecho
hubieres hecho
Imperativo
afirmativo
(yo)\t-
(tú)\thaz
(usted)\thaga
(nosotros)\thagamos
(vosotros)\thaced
(ustedes)\thagan
(vos)\thacé
negativo
-
no hagas
no haga
no hagamos
no hagáis
no hagan
no hagas, *hagás
Indicativo
pretérito anterior
(yo)\thube hecho
(tú)\thubiste hecho
(usted)\thubo hecho
(nosotros)\thubimos hecho
(vosotros)\thubisteis hecho
(ustedes)\thubieron hecho
(vos)\thubiste hecho

        """

        def moods = ['Indicative', 'Subjunctive', 'Imperative', 'Conditional']
        String trimmed = input.trim()
        def lines = trimmed.readLines()
        def filteredLines = []

        def mood = null
        int clozeNum = 1

        filteredLines += lines[0]

        for (int i = 1; i < lines.size(); i++) {
            def line = lines.get(i)
            line = line.trim()

            if (moods.contains(line)) {
                mood = line
            } else if (line.contains('Pluperfect')) { // Skip tense
                i = i+12;
            } else if (line.contains('Perfect') || line.contains('perfect')) { // Skip tense
                i = i+6;
            } else if (line.contains(' ')) { // conjugation
                def lineWithCloze = line.replaceAll(/(\s+)(\w+.*)/, '$1{{c' + clozeNum + '::$2}}')

                filteredLines += lineWithCloze
            } else if (!line.isEmpty()) { // Tense name
                filteredLines += ''
                filteredLines += (mood + " " + line)
                clozeNum++;
            }
        }

        for (int i = 0; i < filteredLines.size(); i++) {
            println "${filteredLines.get(i)}"
        }


    }

}
