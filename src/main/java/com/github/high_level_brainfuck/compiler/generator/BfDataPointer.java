package com.github.high_level_brainfuck.compiler.generator;

import org.apache.commons.lang.StringUtils;

import com.github.high_level_brainfuck.compiler.instructions.VarInstruction;

/**
 * Reprents the data pointer in a brainfuck program (AKA the current cell AKA the head)
 * Used to generate the data pointer's brainfuck code
 */
public class BfDataPointer {
	long cellPos = 0;
	
	public String moveRight() {
		cellPos++;
		return ">";
	}
	
	public String moveRight(int xTimes) {
		cellPos += xTimes;
		return StringUtils.repeat(">", xTimes);
	}
	
	public String moveLeft() {
		cellPos--;
		return "<";
	}
	
	public String moveLeft(int xTimes) {
		cellPos -= xTimes;
		return StringUtils.repeat("<", xTimes);
	}

	public String goTo(VarInstruction variable) {
		long varCellPos = variable.getCellPos();
		String bfCode = "";
		
		if (cellPos > varCellPos) {
			while (cellPos > varCellPos) {
				bfCode += moveLeft();
			}
		} else {
			while (cellPos < varCellPos) {
				bfCode += moveRight();
			}
		}
		
		return bfCode;
	}

	public long getCellPos() {
		return cellPos;
	}
}
