package com.github.high_level_brainfuck.compiler.instructions;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.github.high_level_brainfuck.compiler.generator.BfProgram;

public abstract class Instruction {
	protected static final String LINE_BREAK = "\n";
	protected Instruction parent = null;
	protected List<Instruction> children = new ArrayList<>();
	
	public abstract String generateBfCode(BfProgram bfProgram);

	public List<Instruction> getChildren() {
		return children;
	}

	public void addChild(Instruction child) {
		child.parent = this;
		children.add(child);
	}

	public Instruction getParent() {
		return parent;
	}

	public int getDepth() {
		return parent.getDepth() + 1;
	}
	
	protected String generateChildrenBfCode(BfProgram bfProgram) {
		return generateBfCode(children, bfProgram);
	}
	
	protected static String generateBfCode(List<Instruction> instructions, BfProgram bfProgram) {
		String bfCode = "";
		
		for (Instruction instruction : instructions) {
			bfCode += instruction.generateBfCode(bfProgram);
		}
		
		return bfCode;
	}
	
	protected static String indent(int indentLevel) {
		return StringUtils.repeat("  ", indentLevel);
	}
}
