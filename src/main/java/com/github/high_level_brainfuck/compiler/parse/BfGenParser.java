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
			if (!line.trim().isEmpty()) {
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
			Instruction instruction = parseSingleLine(bfGenLine);
			
			if (bfGenLine.getDepth() == currentDepth) {
				currentParent.addChild(instruction);
			} else if (bfGenLine.getDepth() == currentDepth + 1) {
				currentParent = lastInstruction;
			} else if (bfGenLine.getDepth() == currentDepth - 1) {
				currentParent = currentParent.getParent();
			} else {
				throw new CompileException("Indentation error", bfGenLine.getRawLineNumber());
			}
			
			currentParent.addChild(instruction);
			lastInstruction = instruction;
		}
		
		return new InstructionsTree(root);
	}

	private Instruction parseSingleLine(BfGenLine bfGenLine) {
		// TODO Auto-generated method stub
		return null;
	}
}
