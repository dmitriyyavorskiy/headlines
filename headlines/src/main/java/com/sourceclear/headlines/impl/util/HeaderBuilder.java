package com.sourceclear.headlines.impl.util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class HeaderBuilder {

    private HeaderBuilder() {

    }

    public static String formatDirectives(ImmutableMap<String,ImmutableList<String>> directives, String elementSplitter, String directiveSplitter) {

        // In the case of an empty map return the empty string:
        if (directives.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        // Outer loop - loop through each directive
        for (String directive : directives.keySet()) {

            // Don't add a directive if it has zero elements
            if (directives.get(directive).size() == 0) {
                continue;
            }

            StringBuilder elements = new StringBuilder();

            // Inner loop = for each directive build up the element String
            for (String element: directives.get(directive)) {
                elements.append(elementSplitter).append(element).append(" ");
            }

            if (sb.length() > 0) {
                sb.append("; ");
            }

            sb.append(directive).append(directiveSplitter).append(elements.append(" ").toString());
        }

        return sb.toString().trim();
    }

    public static String formatDirectives(ImmutableList<String> directives, String elementSplitter, String directiveSplitter) {

        // In the case of an empty map return the empty string:
        if (directives.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        // Outer loop - loop through each directive
        for (String directive : directives) {

            // Don't add a directive if it has zero elements
            if (directives.size() == 0) {
                continue;
            }

            StringBuilder elements = new StringBuilder();

            // Inner loop = for each directive build up the element String
            for (String element: directives) {
                elements.append(elementSplitter).append(element).append(" ");
            }

            if (sb.length() > 0) {
                sb.append("; ");
            }

            sb.append(directive).append(directiveSplitter).append(elements.append(" ").toString());
        }

        return sb.toString().trim();
    }

}
