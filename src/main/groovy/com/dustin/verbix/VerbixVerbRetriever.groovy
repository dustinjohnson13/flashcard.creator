package com.dustin.verbix

import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import org.cyberneko.html.parsers.SAXParser

/**
 * Created by djohnson on 5/3/16.
 */
class VerbixVerbRetriever {

    final String verb

    public VerbixVerbRetriever(final String verb) {
        this.verb = verb
    }
    public static void main(String[] args) {
        VerbixVerbRetriever r = new VerbixVerbRetriever('ser')
        println r.getHtml()
    }

    String getHtml() {


        HTTPBuilder http = new HTTPBuilder()
//        http.getHeaders().put("User-Agent", "Mozilla/5.0")

        def data

        def html = http.get(uri: "http://api.verbix.com/conjugator/html?language=spa&tableurl=http://tools.verbix.com/webverbix/personal/template.htm&verb=$verb", contentType: ContentType.TEXT) { resp, reader ->
            def s = reader.text
            data = s
            new XmlSlurper(new SAXParser()).parseText(s)
        }

        return data
    }
}
