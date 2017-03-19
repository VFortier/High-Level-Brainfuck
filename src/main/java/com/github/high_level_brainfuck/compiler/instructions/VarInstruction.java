package com.github.high_level_brainfuck.compiler.instructions;

import org.apache.commons.lang.StringUtils;

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

		bfCode += StringUtils.repeat("+", value);
		bfCode += dataPointer.moveRight();
		
		return bfCode;
	}

	public String getVarName() {
		return varName;
	}

	public long getCellPos() {
		return cellPos;
	}
}
