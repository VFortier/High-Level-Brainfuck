package com.github.high_level_brainfuck.compiler.instructions;

public class InstructionRoot extends Instruction {

	public InstructionRoot() {
		super(null);
	}

	@Override
	public String generateBfCode() {
		String bfCode = "";
		
		for (Instruction child : children) {
			bfCode += child.generateBfCode();
		}
		
		return bfCode;
	}
}
