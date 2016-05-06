package com.dustin.model

import groovy.transform.EqualsAndHashCode

/**
 * Created by djohnson on 5/3/16.
 */
@EqualsAndHashCode
class Mood {
    final String name
    final List<Tense> tenses

    Mood(final String name, final List<Tense> tenses) {
        this.name = name
        this.tenses = tenses
    }

    @Override
    String toString() {
        StringBuilder sb = new StringBuilder('Name: ').append(name).append(", Tenses: ").append(tenses)
        return sb.toString()
    }
}
