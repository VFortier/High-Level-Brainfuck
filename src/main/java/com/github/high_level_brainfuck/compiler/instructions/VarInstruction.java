package com.github.high_level_brainfuck.compiler.instructions;

import com.github.high_level_brainfuck.compiler.generator.BfDataPointer;
import com.github.high_level_brainfuck.compiler.generator.BfProgram;

public class VarInstruction extends Instruction {

	private String varName;
	private int value;
	private long cellPos;

	public VarInstruction(String varName, int value) {
		super(null);
		this.varName = varName;
		this.value = value;
	}

	public String generateBfCode(BfProgram bfProgram) {
		String bfCode = "";
		BfDataPointer dataPointer = bfProgram.getBfDataPointer();
		
		this.cellPos = dataPointer.getCellPos();
		
		for (int i = 0; i < value; i++) {
			bfCode += "+";
		}
		return bfCode + dataPointer.moveRight();
	}

	public String getVarName() {
		return varName;
	}

	public long getCellPos() {
		return cellPos;
	}
}
