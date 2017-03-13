package com.github.high_level_brainfuck.compiler.instructions;

import java.util.ArrayList;
import java.util.List;

public abstract class Instruction {
	private Instruction parent;
	private List<Instruction> children = new ArrayList<>();
	
	public Instruction(Instruction parent) {
		this.parent = parent;
	}

	public List<Instruction> getChildren() {
		return children;
	}
	
	public void addChild(Instruction child) {
		children.add(child);
	}

	public Instruction getParent() {
		return parent;
	}
}
