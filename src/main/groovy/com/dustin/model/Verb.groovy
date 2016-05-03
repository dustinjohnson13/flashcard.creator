package com.dustin.model

/**
 * Created by djohnson on 5/3/16.
 */
class Verb {

    private String infinitive
    private List<Tense> tenses

    Verb(String infinitive, List<Tense> tenses) {
        this.infinitive = infinitive
        this.tenses = tenses
    }
}
