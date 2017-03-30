package com.github.high_level_brainfuck.compiler.instructions;

import java.util.ArrayList;
import java.util.List;

import com.github.high_level_brainfuck.compiler.generator.BfDataPointer;
import com.github.high_level_brainfuck.compiler.generator.BfProgram;

public class IfInstruction extends Instruction {

	private VarInstruction variable;
	
	private List<Instruction> ifInstructions = new ArrayList<>();
	private List<Instruction> elseInstructions = new ArrayList<>();
	
	/**
	 * Flag that determine if the added children should go inside the "else" part
	 */
	private boolean addToElseClause = false;

	public IfInstruction(VarInstruction variable) {
		this.variable = variable;
	}

	@Override
	public List<Instruction> getChildren() {
		return children;
	}

	@Override
	public void addChild(Instruction child) {
		super.addChild(child);
		
		if (!addToElseClause) {
			ifInstructions.add(child);
		} else {
			elseInstructions.add(child);
		}
	}
	
	@Override
	public String generateBfCode(BfProgram bfProgram) {
		
		String bfCode = "";
		BfDataPointer bfDataPointer = bfProgram.getBfDataPointer();
		int baseIndent = getDepth() - 1;
		
		bfCode += indent(baseIndent) + bfDataPointer.goTo(variable) + LINE_BREAK;
		bfCode += indent(baseIndent) + "[" + LINE_BREAK;
		bfCode += generateBfCode(ifInstructions, bfProgram);
		bfCode += indent(baseIndent+1) + bfDataPointer.goToCell(variable.getAvoidElseCellPos());
		bfCode +=   "-";
		bfCode +=   bfDataPointer.goToCell(variable.getEnterElseCellPos()) + LINE_BREAK;
		bfCode += indent(baseIndent) + "]" + LINE_BREAK;
		bfCode += indent(baseIndent) + bfDataPointer.moveRight();
		bfCode +=   "+" + LINE_BREAK;
		bfCode += indent(baseIndent) + "[" + LINE_BREAK;
		
		// The weird part: realign the cursor with the context of the "else"
		bfDataPointer.moveLeft();
		
		bfCode += generateBfCode(elseInstructions, bfProgram);
		bfCode += indent(baseIndent+1) + bfDataPointer.goToCell(variable.getEnterElseCellPos());
		bfCode +=   "-";
		bfCode +=    bfDataPointer.goToCell(variable.getAvoidElseCellPos()) + LINE_BREAK;
		bfCode += indent(baseIndent) + "]" + LINE_BREAK;
		
		return bfCode;
	}

	public void addChildToElse() {
		addToElseClause = true;
	}
}
