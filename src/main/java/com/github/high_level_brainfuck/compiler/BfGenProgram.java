package com.github.high_level_brainfuck.compiler;

import com.github.high_level_brainfuck.compiler.instructions.InstructionRoot;

public class BfGenProgram {	
	InstructionRoot instructionRoot;

	public BfGenProgram(InstructionRoot instructionRoot) {
		super();
		this.instructionRoot = instructionRoot;
	}

	public InstructionRoot getInstructionRoot() {
		return instructionRoot;
	}
}
