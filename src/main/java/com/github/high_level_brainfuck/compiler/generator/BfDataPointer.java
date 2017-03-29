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
		return goToCell(variable.getCellPos());
	}

	public String goToCell(long enterElseCellPos) {
		String bfCode = "";
		
		if (cellPos > enterElseCellPos) {
			while (cellPos > enterElseCellPos) {
				bfCode += moveLeft();
			}
		} else {
			while (cellPos < enterElseCellPos) {
				bfCode += moveRight();
			}
		}
		
		return bfCode;
	}

	public long getCellPos() {
		return cellPos;
	}

	public String addAtCurrentCell(int i) {
		
		return "";
	}
}
