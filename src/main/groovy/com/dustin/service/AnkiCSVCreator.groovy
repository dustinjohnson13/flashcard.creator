package com.dustin.service
/**
 * Created by djohnson on 5/27/16.
 */
class AnkiCSVCreator {

    public static void main(String[] args) {
        def input = """

Infinitivo:
Gerundio:
Participio:\tver
viendo
visto\tDefinición\tEn Inglés
En Francés
En Portugués\t \t
Indicativo
presente
yo\tveo
tú\tves
él, ella, Ud.\tve
nosotros\tvemos
vosotros\tveis
ellos, ellas, Uds.\tven
vos\tves
imperfecto
veía
veías
veía
veíamos
veíais
veían
veías
pretérito
vi
viste
vio
vimos
visteis
vieron
viste
futuro
veré
verás
verá
veremos
veréis
verán
verás
condicional
vería
verías
vería
veríamos
veríais
verían
verías
Tiempos compuestos comunes
pretérito perfecto
yo\the visto
tú\thas visto
él, ella, Ud.\tha visto
nosotros\themos visto
vosotros\thabéis visto
ellos, ellas, Uds.\than visto
vos\thas visto
pluscuamperfecto
había visto
habías visto
había visto
habíamos visto
habíais visto
habían visto
habías visto
futuro perfecto
habré visto
habrás visto
habrá visto
habremos visto
habréis visto
habrán visto
habrás visto
condicional perfecto
habría visto
habrías visto
habría visto
habríamos visto
habríais visto
habrían visto
habrías visto
Subjuntivo
presente
yo\tvea
tú\tveas
él, ella, Ud.\tvea
nosotros\tveamos
vosotros\tveáis
ellos, ellas, Uds.\tvean
vos\tveas
imperfecto
viera o viese
vieras o vieses
viera o viese
viéramos o viésemos
vierais o vieseis
vieran o viesen
vieras o vieses
futuro
viere
vieres
viere
viéremos
viereis
vieren
vieres
Tiempos compuestos del subjuntivo
pretérito perfecto
yo\thaya visto
tú\thayas visto
él, ella, Ud.\thaya visto
nosotros\thayamos visto
vosotros\thayáis visto
ellos, ellas, Uds.\thayan visto
vos\thayas visto
pluscuamperfecto
hubiera o hubiese visto
hubieras o hubieses visto
hubiera o hubiese visto
hubiéramos o hubiésemos visto
hubierais o hubieseis visto
hubieran o hubiesen visto
hubieras o hubieses visto
futuro perfecto
hubiere visto
hubieres visto
hubiere visto
hubiéremos visto
hubiereis visto
hubieren visto
hubieres visto
Imperativo
afirmativo
(yo)\t-
(tú)\tve
(usted)\tvea
(nosotros)\tveamos
(vosotros)\tved
(ustedes)\tvean
(vos)\tve
negativo
-
no veas
no vea
no veamos
no veáis
no vean
no veas
Indicativo
pretérito anterior
(yo)\thube visto
(tú)\thubiste visto
(usted)\thubo visto
(nosotros)\thubimos visto
(vosotros)\thubisteis visto
(ustedes)\thubieron visto
(vos)\thubiste visto
        """

        def moods = ['Indicativo', 'Subjuntivo', 'Imperativo', 'Conditional']
        def complexTenses = ['Tiempos compuestos comunes', 'Tiempos compuestos del subjuntivo', 'pretérito perfecto', 'pluscuamperfecto', 'pretérito anterior']
        def pronouns = ['yo', 'tú', 'él, ella, Ud.', 'nosotros', 'vosotros', 'ellos, ellas, Uds.', 'vos']

        String trimmed = input.trim()
        def lines = trimmed.readLines()
        def filteredLines = []

        def mood = null
        int pronounNum = 0
        int clozeNum = 1
        boolean setMood = false
        boolean complexTense = false

        for (int i = 2; i < lines.size(); i++) {
            def line = lines.get(i)
            line = line.trim()


            if (i == 2) {
                def split = line.split('\t')
                def lineWithCloze = "Infinitivo: " + split[1]

                filteredLines += lineWithCloze
            } else if (i == 3) {
                def lineWithCloze = "Gerundio: " + clozeDeleteLine(line, clozeNum)

                filteredLines += lineWithCloze
            } else if (i == 4) {
                def split = line.split('\t')
                def lineWithCloze = 'Participio: ' + clozeDeleteLine(split[0], clozeNum)

                filteredLines += lineWithCloze
            } else if (i < 7 || line == '-') {
            } else if (complexTenses.contains(line)) {
                complexTense = true
            } else if (moods.contains(line)) {
                mood = line
                setMood = true
                complexTense = false
            } else if (line.contains('\t')) { // First conjugation for row

                if (complexTense) {
                    continue
                }

                def split = line.split('\t')
                def pronoun = pronouns[pronounNum++]
                def lineWithCloze = pronoun + ' ' + clozeDeleteLine(split[split.length - 1], clozeNum)

                filteredLines += lineWithCloze

//                if (pronounNum == pronouns.size() - 1) {
//                    pronounNum = 0
//                    clozeNum++
//                }
            } else if (setMood || (pronounNum == pronouns.size())) { // Tense name
                if (complexTense) {
                    continue
                }
                filteredLines += ''
                filteredLines += (mood + " " + line)
                clozeNum++

                pronounNum = 0
                setMood = false
            } else { // remaining conjugations for row

                if (complexTense) {
                    pronounNum++
                    continue
                }

                def pronoun = pronouns[pronounNum++]
                def lineWithCloze
                if (line.startsWith('no ')) {
                    lineWithCloze = pronoun + ' no ' + clozeDeleteLine(line.substring(3), clozeNum)
                } else {
                    lineWithCloze = pronoun + ' ' + clozeDeleteLine(line, clozeNum)
                }

                filteredLines += lineWithCloze
//
//                if (pronounNum == pronouns.size() - 1) {
//                    pronounNum = 0
//                    clozeNum++
//                }
            }
        }

        for (int i = 0; i < filteredLines.size(); i++) {
            println "${filteredLines.get(i)}"
        }
    }

    private static String clozeDeleteLine(String line, int clozeNum) {
        return '{{c' + clozeNum + '::' + line + '}}'
    }

}
