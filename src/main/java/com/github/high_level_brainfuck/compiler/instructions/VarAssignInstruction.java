package com.github.high_level_brainfuck.compiler.instructions;

import org.apache.commons.lang.StringUtils;

import com.github.high_level_brainfuck.compiler.generator.BfProgram;

public class VarAssignInstruction extends Instruction {

	private VarInstruction variable;
	private int value;
	private Boolean isIncrement;

	public VarAssignInstruction(VarInstruction var, 
			int value, Boolean isIncrement) {
		this.variable = var;
		this.value = value;
		this.isIncrement = isIncrement;
	}

	@Override
	public String generateBfCode(BfProgram bfProgram) {
		String bfCode = "";
		int baseIndent = getDepth() - 1;
		
		bfCode += indent(baseIndent) + bfProgram.getBfDataPointer().goTo(variable);
		if (isIncrement) {
			bfCode += StringUtils.repeat("+", value);
		} else {
			bfCode += StringUtils.repeat("-", value);
		}
		
		return bfCode + LINE_BREAK;
	}

}
