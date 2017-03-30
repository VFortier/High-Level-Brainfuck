package com.github.high_level_brainfuck.compiler.parser;

import com.github.high_level_brainfuck.compiler.CompileException;
import com.github.high_level_brainfuck.compiler.instructions.IfInstruction;
import com.github.high_level_brainfuck.compiler.instructions.Instruction;
import com.github.high_level_brainfuck.compiler.instructions.InstructionRoot;
import com.github.high_level_brainfuck.compiler.instructions.VarInstruction;

public class IfParser {
	public boolean isIf(BfGenLine bfGenLine) {
		return bfGenLine.getCode().startsWith(BfGenKeywords.IF);
	}

	public boolean isElse(BfGenLine bfGenLine) {
		return bfGenLine.getCode().equals(BfGenKeywords.ELSE);
	}
	
	public Instruction parse(BfGenLine bfGenLine, InstructionRoot instructionRoot) 
			throws CompileException {
		
		String code = bfGenLine.getCode();
		String[] codeSplit = code.split(" ");
		VarInstruction var = null;
		
		if (codeSplit.length == 2) {
			String varName = codeSplit[1];
			var = instructionRoot.getVarByName(varName);
			
			if (var == null) {
				throw new CompileException("[If] Unknown var", bfGenLine.getLineNum());
			}
			
			var.setIfable(true);
		} else {
			throw CompileException.newSyntaxError(bfGenLine.getLineNum());
		}
		
		return new IfInstruction(var);
	}
}
