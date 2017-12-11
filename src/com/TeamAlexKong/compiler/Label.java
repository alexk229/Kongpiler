package com.TeamAlexKong.compiler;

public enum Label {
	LABEL("Label"),
	WHEN("WhenLabel"),
	WHEN_END("WhenEnd"),
	WHILE_LOOP("WhileLoop"),
	WHILE_COMPARE("WhileCompare"),
	IF("IfLabel"),
	ELSE("ElseLabel"),
	FOR_LOOP("ForLoopLabel")
    ;

    private final String text;

    private Label(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
