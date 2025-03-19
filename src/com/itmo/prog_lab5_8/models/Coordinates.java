package com.itmo.prog_lab5_8.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Coordinates{
    public Float x;
    public Float y;

    @Override
    public String toString() {
        return "coordinates: (" + x + ", " + y + ")";
    }

    public String toXMLObject() {
        return "<coordinates>\n\t<x>" + x + "</x>\n\t<y>" + y + "</y>\n</coordinates>";
    }

    private static final Pattern pattern = Pattern.compile("^\\s*<\\s*coordinates\\s*>\\s*<\\s*x\\s*>(.*)<\\s*/x\\s*>\\s*<\\s*y\\s*>(.*)<\\s*/y\\s*>\\s*<\\s*/coordinates\\s*>");
    static public Coordinates fromXML(String text) throws XMLObjectFormatException {
        Matcher matcher = pattern.matcher(text);
        Coordinates newCoord = new Coordinates();
        if (matcher.find()) {
            newCoord.x = Float.valueOf(matcher.group(1));
            newCoord.y = Float.valueOf(matcher.group(2));
        } else {
            throw new XMLObjectFormatException("Coordinates not found in input: " + text);
        }
        return newCoord;
    }
}
