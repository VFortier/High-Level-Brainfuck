package com.github.high_level_brainfuck.compiler.parser;

import com.github.high_level_brainfuck.compiler.CompileException;
import com.github.high_level_brainfuck.compiler.instructions.Instruction;
import com.github.high_level_brainfuck.compiler.instructions.InstructionRoot;
import com.github.high_level_brainfuck.compiler.instructions.PrintInstruction;
import com.github.high_level_brainfuck.compiler.instructions.VarAssignInstruction;
import com.github.high_level_brainfuck.compiler.instructions.VarInstruction;

public class VarAssignParser {
	public boolean isVarAssign(BfGenLine bfGenLine) {
		return bfGenLine.getCode().contains(BfGenKeywords.PLUS_EQ) ||
				bfGenLine.getCode().contains(BfGenKeywords.MINUS_EQ);
	}
	
	public Instruction parse(BfGenLine bfGenLine, Instruction parent, InstructionRoot instructionRoot) 
			throws CompileException {
		
		String code = bfGenLine.getCode();
		String[] codeSplit = null;
		Boolean isIncrement = null;
		
		if (bfGenLine.getCode().contains(BfGenKeywords.PLUS_EQ)) {
			codeSplit = code.split("\\" + BfGenKeywords.PLUS_EQ);
			isIncrement = true;
		} else if (bfGenLine.getCode().contains(BfGenKeywords.MINUS_EQ)) {
			codeSplit = code.split(BfGenKeywords.MINUS_EQ);
			isIncrement = false;
		}

		VarInstruction var = null;
		int value;
		
		if (codeSplit.length == 2) {
			String varName = codeSplit[0].trim();
			String valueStr = codeSplit[1].trim();
			
			// Parse the variable name
			var = instructionRoot.getVarByName(varName);
			
			if (var == null) {
				throw new CompileException("[Variable assignment] Unknown var", 
						bfGenLine.getLineNum());
			}
			
			// Parse the value
			try {
				value = Integer.parseInt(valueStr);
			} catch (NumberFormatException e) {
				throw new CompileException("Invalid variable value \"" + valueStr 
						+ "\"", bfGenLine.getLineNum());
			}
		} else {
			throw CompileException.newSyntaxError(bfGenLine.getLineNum());
		}
		
		return new VarAssignInstruction(parent, var, value, isIncrement);
	}
}
