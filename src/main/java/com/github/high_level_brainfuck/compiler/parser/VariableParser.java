package com.github.high_level_brainfuck.compiler.parser;

import com.github.high_level_brainfuck.compiler.CompileException;
import com.github.high_level_brainfuck.compiler.instructions.Instruction;
import com.github.high_level_brainfuck.compiler.instructions.VarInstruction;

public class VariableParser {
	
	public boolean isVar(BfGenLine bfGenLine) {
		return bfGenLine.getCode().startsWith(BfGenKeywords.INT8);
	}
	
	public Instruction parse(BfGenLine bfGenLine, Instruction parent) throws CompileException {
		String varName;
		String valueStr;
		int value;
		
		String code = bfGenLine.getCode();
		String[] eqSplit = code.split("=");
		
		if (eqSplit.length == 2) {
			
			// Get the var name
			
			String varVarName = eqSplit[0].trim();
			valueStr = eqSplit[1].trim();
			
			String[] varVarNameSplit = varVarName.split(" ");
			
			if (varVarNameSplit.length == 2) {
				varName = varVarNameSplit[1];
			} else {
				throw CompileException.newSyntaxError(bfGenLine.getLineNum());
			}
			
			// Get the var value
			try {
				if (valueIsAChar(valueStr)) {
					// Get ascii value of provided char
					value = (int) valueStr.charAt(1);
				} else {
					value = Integer.parseInt(valueStr);
				}
			} catch (NumberFormatException e) {
				throw new CompileException("Invalid variable value \"" + valueStr 
						+ "\"", bfGenLine.getLineNum());
			}
			
		} else {
			throw new CompileException("A variable declaration line " + 
							"requires exactly one \"=\" operator.", bfGenLine.getLineNum());
		}
		
		return new VarInstruction(varName, value);
	}
	
	private boolean valueIsAChar(String valueStr) {
		return valueStr.charAt(0) == '\'' && valueStr.charAt(valueStr.length()-1) == '\'' &&
				valueStr.length() == 3;
	}
}
