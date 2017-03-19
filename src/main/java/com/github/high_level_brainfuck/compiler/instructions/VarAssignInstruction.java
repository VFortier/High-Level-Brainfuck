package com.github.high_level_brainfuck.compiler.instructions;

import org.apache.commons.lang.StringUtils;

import com.github.high_level_brainfuck.compiler.generator.BfProgram;

public class VarAssignInstruction extends Instruction {

	private VarInstruction variable;
	private int value;
	private Boolean isIncrement;

	public VarAssignInstruction(Instruction parent, VarInstruction var, 
			int value, Boolean isIncrement) {
		super(parent);
		this.variable = var;
		this.value = value;
		this.isIncrement = isIncrement;
	}

	@Override
	public String generateBfCode(BfProgram bfProgram) {
		String bfCode = "";
		
		bfCode += bfProgram.getBfDataPointer().goTo(variable);
		if (isIncrement) {
			bfCode += StringUtils.repeat("+", value);
		} else {
			bfCode += StringUtils.repeat("-", value);
		}
		
		return bfCode;
	}

}
