package com.github.high_level_brainfuck.compiler.instructions;

import java.util.ArrayList;
import java.util.List;

import com.github.high_level_brainfuck.compiler.generator.BfProgram;

public abstract class Instruction {
	protected Instruction parent;
	protected List<Instruction> children = new ArrayList<>();
	
	public abstract String generateBfCode(BfProgram bfProgram);
	
	public Instruction(Instruction parent) {
		this.parent = parent;
	}

	public void addChild(Instruction child) {
		children.add(child);
	}

	public Instruction getParent() {
		return parent;
	}
}
