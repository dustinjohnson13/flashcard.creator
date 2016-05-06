package com.dustin.model

/**
 * Created by djohnson on 5/3/16.
 */
class Verb {

    final String infinitive
    final String participle
    final String gerund
    final List<Mood> moods

    Verb(String infinitive, String participle, String gerund, List<Mood> moods) {
        this.infinitive = infinitive
        this.participle = participle
        this.gerund = gerund
        this.moods = moods
    }

}
