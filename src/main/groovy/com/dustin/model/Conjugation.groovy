package com.dustin.model

import groovy.transform.EqualsAndHashCode

/**
 * Created by djohnson on 5/6/16.
 */
@EqualsAndHashCode
class Conjugation {
    final String person
    final String conjugated

    Conjugation(final String person, final String conjugated) {
        this.person = person
        this.conjugated = conjugated
    }

    @Override
    String toString() {
        return "$person $conjugated"
    }
}
