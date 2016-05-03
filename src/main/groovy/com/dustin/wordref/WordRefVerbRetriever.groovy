package com.dustin.wordref
/**
 * Created by djohnson on 5/3/16.
 */
class WordRefVerbRetriever {

    final String verb

    public WordRefVerbRetriever(final String verb) {
        this.verb = verb
    }

    String getHtml() {
        def data = new URL("http://www.wordreference.com/conj/ESverbs.aspx?v=$verb").text
        return data
    }
}
