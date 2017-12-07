package com.TeamAlexKong.compiler;

public enum Literal {
    BOOL("Bool"),
    CHAR("Char"),
    BYTE("Byte"),
    SHORT("Short"),
    INT("Int"),
    LONG("Long"),
    FLOAT("Float"),
    DOUBLE("Double"),
    STRING("String")
    ;

    private final String text;

    private Literal(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}