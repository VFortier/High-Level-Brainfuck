package com.github.high_level_brainfuck.compiler.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.high_level_brainfuck.compiler.BfGenProgram;
import com.github.high_level_brainfuck.compiler.CompileException;
import com.github.high_level_brainfuck.compiler.instructions.Instruction;
import com.github.high_level_brainfuck.compiler.instructions.InstructionRoot;

public class BfGenParser {
	
	private static final String COMMENT_DELIMITER = "#";
	
	public BfGenProgram parse(String bfGenCode) throws CompileException {
		
		String[] linesArray = bfGenCode.split("\n");
		List<String> lines = Arrays.asList(linesArray);
		
		List<BfGenLine> bfGenLines = parseRawLines(lines);
		
		return parseBfGenLines(bfGenLines);
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

	private BfGenProgram parseBfGenLines(List<BfGenLine> lines) throws CompileException {
		InstructionRoot instructionRoot = new InstructionRoot();
		Instruction currentParent = instructionRoot;
		Instruction lastInstruction = null;
		int currentDepth = 0;
		
		for (BfGenLine bfGenLine : lines) {
			Instruction instruction = parseSingleLine(bfGenLine, currentParent, instructionRoot);
			
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
		
		return new BfGenProgram(instructionRoot);
	}

	private Instruction parseSingleLine(BfGenLine bfGenLine, Instruction parent, 
			InstructionRoot instructionRoot) 
		throws CompileException {
		
		VariableParser variableParser = new VariableParser();
		PrintParser printParser = new PrintParser();
		
		Instruction instruction = null;
		
		if (variableParser.isVar(bfGenLine)) {
			instruction = variableParser.parse(bfGenLine, parent);
		} else if (printParser.isPrint(bfGenLine)) {
			instruction = printParser.parse(bfGenLine, parent, instructionRoot);
		}
		
		return instruction;
	}
}
