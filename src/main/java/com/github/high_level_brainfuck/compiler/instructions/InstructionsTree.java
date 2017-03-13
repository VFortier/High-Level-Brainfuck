package com.github.high_level_brainfuck.compiler.instructions;

public class InstructionsTree {
	
	InstructionRoot root;
	
	public InstructionsTree(InstructionRoot root) {
		this.root = root;
	}

	public InstructionRoot getRoot() {
		return root;
	}
}