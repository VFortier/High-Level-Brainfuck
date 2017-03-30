package com.github.high_level_brainfuck.compiler.instructions;

import com.github.high_level_brainfuck.compiler.generator.BfProgram;

public class PrintInstruction extends Instruction {

	private VarInstruction variable;

	public PrintInstruction(VarInstruction variable) {
		this.variable = variable;
	}

	@Override
	public String generateBfCode(BfProgram bfProgram) {
		int baseIndent = getDepth() - 1;
		return indent(baseIndent) + bfProgram.getBfDataPointer().goTo(variable) + "." + LINE_BREAK;
	}

}
