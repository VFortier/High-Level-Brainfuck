package com.github.high_level_brainfuck.compiler.instructions;

import com.github.high_level_brainfuck.compiler.generator.BfProgram;

public class VarInstruction extends Instruction {

	private String varName;
	private int value;

	public VarInstruction(String varName, int value) {
		super(null);
		this.varName = varName;
		this.value = value;
	}

	public String generateBfCode(BfProgram bfProgram) {
		String bfCode = "";
		
		for (int i = 0; i < value; i++) {
			bfCode += "+";
		}
		return bfCode + ">";
	}

	public String getVarName() {
		return varName;
	}
}
