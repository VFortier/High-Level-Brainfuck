package com.github.high_level_brainfuck.compiler.instructions;

import org.apache.commons.lang.StringUtils;

import com.github.high_level_brainfuck.compiler.generator.BfDataPointer;
import com.github.high_level_brainfuck.compiler.generator.BfProgram;

public class VarInstruction extends Instruction {

	private String varName;
	private int initValue;
	private long cellPos;
	
	/**
	 * True when the variable is used in an if statement in the BFGen code
	 */
	private boolean isIfable;

	public VarInstruction(String varName, int initValue) {
		this.varName = varName;
		this.initValue = initValue;
	}

	public String generateBfCode(BfProgram bfProgram) {
		String bfCode = "";
		BfDataPointer dataPointer = bfProgram.getBfDataPointer();
		
		this.cellPos = dataPointer.getCellPos();

		bfCode += StringUtils.repeat("+", initValue);
		bfCode += dataPointer.moveRight() + LINE_BREAK;
		
		if (isIfable) {
			// Reserve spot for two bf "variable" that will be used to
			// control the enter/exit conditions of if and else
			bfCode += dataPointer.moveRight() + LINE_BREAK;
			bfCode += dataPointer.moveRight() + LINE_BREAK;
		}
		
		return bfCode;
	}

	public String getVarName() {
		return varName;
	}

	public long getCellPos() {
		return cellPos;
	}

	public long getEnterElseCellPos() {
		return cellPos + 1;
	}

	public long getAvoidElseCellPos() {
		return cellPos + 2;
	}

	public void setIfable(boolean isIfable) {
		this.isIfable = isIfable;
	}
	
	@Override
	public String toString() {
		return varName;
	}
}
