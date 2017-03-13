package com.github.high_level_brainfuck.compiler.generator;

import org.apache.commons.lang.StringUtils;

/**
 * Reprents the data pointer in a brainfuck program (AKA the current cell AKA the head)
 * Used to generate the data pointer's brainfuck code
 */
public class BfDataPointer {
	long currentCell = 0;
	
	public String moveRight() {
		currentCell++;
		return ">";
	}
	
	public String moveRight(int xTimes) {
		currentCell += xTimes;
		return StringUtils.repeat(">", xTimes);
	}
	
	public String moveLeft() {
		currentCell--;
		return "<";
	}
	
	public String moveLeft(int xTimes) {
		currentCell -= xTimes;
		return StringUtils.repeat("<", xTimes);
	}
}
