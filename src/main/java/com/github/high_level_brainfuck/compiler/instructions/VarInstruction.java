package com.github.high_level_brainfuck.compiler.instructions;

public class VarInstruction extends Instruction {

	private String varName;
	private String value;

	public VarInstruction(Instruction parent, String varName, String value) {
		super(parent);
		this.varName = varName;
		this.value = value;
	}

}
