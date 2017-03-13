package com.github.high_level_brainfuck.compiler.generator;

import com.github.high_level_brainfuck.compiler.BfGenProgram;
import com.github.high_level_brainfuck.compiler.instructions.InstructionRoot;

public class BfProgramGenerator {

	public BfProgram generate(BfGenProgram bfGenProgram) {
		BfProgram bfProgram = new BfProgram();
		InstructionRoot instructionsRoot = bfGenProgram.getInstructionRoot();
		
		String bfCode = instructionsRoot.generateBfCode(bfProgram);
		bfProgram.setBfCode(bfCode);
		
		return bfProgram;
	}

}
