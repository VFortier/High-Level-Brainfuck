package com.github.high_level_brainfuck.compiler.parser;

public class BfGenLine {
	
	int rawLineNumber;
	String code;
	int depth;
	
	public BfGenLine(String rawCode, int rawLineNumber) {
		this.code = cleanLine(rawCode);
		this.depth = calcDepth(rawCode);
		this.rawLineNumber = rawLineNumber;
	}

	private String cleanLine(String code) {
		code = code.replace("\t", "");
		code = code.replace("\n", "");
		code = code.replace("\r", "");
		code = code.toLowerCase();
		
		return code;
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
	
	@Override
	public String toString() {
		return code;
	}

	public String getCode() {
		return code;
	}

	public int getDepth() {
		return depth;
	}

	public int getLineNum() {
		return rawLineNumber;
	}
}
