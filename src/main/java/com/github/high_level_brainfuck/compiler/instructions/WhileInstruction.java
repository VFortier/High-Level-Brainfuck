package com.github.high_level_brainfuck.compiler.instructions;

import com.github.high_level_brainfuck.compiler.generator.BfProgram;

public class WhileInstruction extends Instruction {

	private VarInstruction variable;

	public WhileInstruction(VarInstruction variable) {
		this.variable = variable;
	}

	@Override
	public String generateBfCode(BfProgram bfProgram) {
		
		String bfCode = "";
		int baseIndent = getDepth() - 1;
		
		bfCode += indent(baseIndent) + bfProgram.getBfDataPointer().goTo(variable) + LINE_BREAK;
		bfCode += indent(baseIndent) + "[" + LINE_BREAK;
		bfCode += generateChildrenBfCode(bfProgram);
		bfCode += indent(baseIndent+1) + bfProgram.getBfDataPointer().goTo(variable) + LINE_BREAK;
		bfCode += indent(baseIndent) + "]" + LINE_BREAK;
		
		return bfCode;
	}
}
