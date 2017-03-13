package com.github.high_level_brainfuck.compiler;

public class BfProgram {
	
	private String bfCode;
	
	public BfProgram() {
		
	}
	
	public BfProgram(String bfCode) {
		this.bfCode = bfCode;
	}

	@Override
	public String toString() {
		return bfCode;
	}

	public String getCode() {
		return bfCode;
	}
}
