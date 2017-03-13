package com.github.high_level_brainfuck.compiler;

import com.github.high_level_brainfuck.compiler.instructions.InstructionsTree;

public class BfProgramGenerator {

	public BfProgram generate(BfGenProgram bfGenProgram) {
		InstructionsTree instructionsTree = bfGenProgram.getInstructionsTree();
		
		String bfCode = instructionsTree.getRoot().generateBfCode();
		
		return new BfProgram(bfCode);
	}

}
