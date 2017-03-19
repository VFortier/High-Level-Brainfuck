package com.github.high_level_brainfuck.compiler.instructions;

import com.github.high_level_brainfuck.compiler.generator.BfProgram;

public class WhileInstruction extends Instruction {

	private VarInstruction variable;

	public WhileInstruction(Instruction parent, VarInstruction variable) {
		super(parent);
		this.variable = variable;
	}

	@Override
	public String generateBfCode(BfProgram bfProgram) {
		
		String bfCode = "";
		
		bfCode += bfProgram.getBfDataPointer().goTo(variable);
		bfCode += "[";
		bfCode += generateChildrenBfCode(bfProgram);
		bfCode += bfProgram.getBfDataPointer().goTo(variable);
		bfCode += "]";
		
		return bfCode;
	}
}
