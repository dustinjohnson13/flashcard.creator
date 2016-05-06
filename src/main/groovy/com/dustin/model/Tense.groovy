package com.dustin.model

import groovy.transform.EqualsAndHashCode

/**
 * Created by djohnson on 5/3/16.
 */
@EqualsAndHashCode
class Tense {
    final String name
    final List<Conjugation> conjugations

    Tense(final String name, final List<Conjugation> conjugations) {
        this.name = name
        this.conjugations = conjugations
    }

    @Override
    String toString() {
        StringBuilder sb = new StringBuilder('Name: ').append(name).append(", Conjugations: ").append(conjugations)
        return sb.toString()
    }
}
