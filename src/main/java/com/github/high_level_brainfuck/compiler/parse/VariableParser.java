package com.github.high_level_brainfuck.compiler.parse;

import com.github.high_level_brainfuck.compiler.CompileException;
import com.github.high_level_brainfuck.compiler.instructions.Instruction;
import com.github.high_level_brainfuck.compiler.instructions.VarInstruction;

public class VariableParser {
	
	public boolean isVar(BfGenLine bfGenLine) {
		return bfGenLine.getCode().startsWith(BfGenKeywords.INT8);
	}
	
	public Instruction parse(BfGenLine bfGenLine, Instruction parent) throws CompileException {
		String varName;
		String value;
		
		String code = bfGenLine.getCode();
		String[] eqSplit = code.split("=");
		
		if (eqSplit.length == 2) {
			String varVarName = eqSplit[0];
			value = eqSplit[1];
			
			String[] varVarNameSplit = varVarName.trim().split(" ");
			
			if (varVarNameSplit.length == 2) {
				varName = varVarNameSplit[1];
			} else {
				throw new CompileException(bfGenLine.getLineNum());
			}
			
		} else {
			throw new CompileException("A variable declaration line " + 
							"requires exactly one \"=\" operator.", bfGenLine.getLineNum());
		}
		
		return new VarInstruction(parent, varName, value);
	}
}
