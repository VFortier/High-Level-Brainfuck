package com.github.high_level_brainfuck.compiler.parse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.high_level_brainfuck.compiler.BfGenProgram;
import com.github.high_level_brainfuck.compiler.CompileException;
import com.github.high_level_brainfuck.compiler.instructions.Instruction;
import com.github.high_level_brainfuck.compiler.instructions.InstructionRoot;
import com.github.high_level_brainfuck.compiler.instructions.InstructionsTree;

public class BfGenParser {
	
	private boolean isParsingVarsDone = false;
	
	private static final String COMMENT_DELIMITER = "#";
	
	public BfGenProgram parse(String bfGenCode) throws CompileException {
		
		String[] linesArray = bfGenCode.split("\n");
		List<String> lines = Arrays.asList(linesArray);
		
		List<BfGenLine> bfGenLines = parseRawLines(lines);
		InstructionsTree instructionsTree = parseBfGenLines(bfGenLines);
		
		return new BfGenProgram(instructionsTree);
	}
	
	private List<BfGenLine> parseRawLines(List<String> lines) {
		List<BfGenLine> allBfGenLines = new ArrayList<>();
		int rawLineNumber = 1;
		
		for (String line : lines) {
			String trimmedLine = line.trim();
			
			if (!trimmedLine.isEmpty() && !trimmedLine.startsWith(COMMENT_DELIMITER)) {
				BfGenLine bfGenLine = new BfGenLine(line, rawLineNumber);
				allBfGenLines.add(bfGenLine);
				rawLineNumber++;
			}
		}
		
		return allBfGenLines;
	}

	private InstructionsTree parseBfGenLines(List<BfGenLine> lines) throws CompileException {
		InstructionRoot root = new InstructionRoot();
		Instruction currentParent = root;
		Instruction lastInstruction = null;
		int currentDepth = 0;
		
		for (BfGenLine bfGenLine : lines) {
			Instruction instruction = parseSingleLine(bfGenLine, currentParent);
			
			if (instruction != null) {
				if (bfGenLine.getDepth() == currentDepth) {
					// currentParent stays the same
				} else if (bfGenLine.getDepth() == currentDepth + 1) {
					currentDepth = bfGenLine.getDepth();
					currentParent = lastInstruction;
				} else if (bfGenLine.getDepth() < currentDepth) {
					currentDepth = bfGenLine.getDepth();
					currentParent = currentParent.getParent();
					// TODO - Do getParent many times, possibly
				} else {
					throw new CompileException("Indentation error", bfGenLine.getLineNum());
				}
				
				currentParent.addChild(instruction);
				lastInstruction = instruction;
			}
		}
		
		return new InstructionsTree(root);
	}

	private Instruction parseSingleLine(BfGenLine bfGenLine, Instruction parent) throws CompileException {
		
		VariableParser variableParser = new VariableParser();
		
		boolean isVar = variableParser.isVar(bfGenLine);
		Instruction instruction = null;
		
		if (isVar && !isParsingVarsDone) {
			instruction = variableParser.parse(bfGenLine, parent);
		} else if (!isVar) {
			if (!isParsingVarsDone) {
				isParsingVarsDone = true;
			}
		} else if (isVar && !isParsingVarsDone) {
			throw new CompileException("Cannot declare a variable here. " + 
							"Group variable declaration at the beginning.", bfGenLine.getLineNum());
		}
		
		return instruction;
	}
}
