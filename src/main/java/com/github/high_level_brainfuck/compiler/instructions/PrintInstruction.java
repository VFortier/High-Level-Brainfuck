package com.github.high_level_brainfuck.compiler.instructions;

import com.github.high_level_brainfuck.compiler.generator.BfProgram;

public class PrintInstruction extends Instruction {

	private VarInstruction variable;

	public PrintInstruction(Instruction parent, VarInstruction variable) {
		super(parent);
		this.variable = variable;
	}

	@Override
	public String generateBfCode(BfProgram bfProgram) {
		// TODO Auto-generated method stub
		return null;
	}

}
