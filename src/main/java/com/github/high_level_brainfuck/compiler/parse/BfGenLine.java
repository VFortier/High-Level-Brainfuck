package com.github.high_level_brainfuck.compiler.parse;

public class BfGenLine {
	
	int rawLineNumber;
	String code;
	int depth;
	
	public BfGenLine(String rawCode, int rawLineNumber) {
		this.code = removeSpaces(rawCode);
		this.depth = calcDepth(rawCode);
		this.rawLineNumber = rawLineNumber;
	}

	private String removeSpaces(String code) {
		return code.replace("\t", "").replace(" ", "");
	}

	private int calcDepth(String rawCode) {
		int i = 0;
		int depth = 0;
		
		while (rawCode.charAt(i) == '\t') {
			i++;
			depth++;
		}
		
		return depth;
	}
	
	public String getCode() {
		return code;
	}

	public int getDepth() {
		return depth;
	}

	public int getRawLineNumber() {
		return rawLineNumber;
	}
}
