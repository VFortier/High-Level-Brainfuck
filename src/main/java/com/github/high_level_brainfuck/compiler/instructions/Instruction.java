package com.github.high_level_brainfuck.compiler.instructions;

import java.util.ArrayList;
import java.util.List;

public abstract class Instruction {
	protected Instruction parent;
	protected List<Instruction> children = new ArrayList<>();
	
	public abstract String generateBfCode();
	
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
