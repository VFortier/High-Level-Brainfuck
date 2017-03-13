package com.github.high_level_brainfuck.compiler.instructions;

public class VarInstruction extends Instruction {

	private String varName;
	private int value;

	public VarInstruction(Instruction parent, String varName, int value) {
		super(parent);
		this.varName = varName;
		this.value = value;
	}

	@Override
	public String generateBfCode() {
		String bfCode = "";
		
		for (int i = 0; i < value; i++) {
			bfCode += "+";
		}
		return bfCode + ">";
	}

}
