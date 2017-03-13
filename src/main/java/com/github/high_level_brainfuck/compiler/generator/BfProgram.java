package com.github.high_level_brainfuck.compiler.generator;

public class BfProgram {
	
	private String bfCode;
	private BfDataPointer bfDataPointer = new BfDataPointer();
	
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

	public void setBfCode(String bfCode) {
		this.bfCode = bfCode;
	}

	public BfDataPointer getBfDataPointer() {
		return bfDataPointer;
	}
}
