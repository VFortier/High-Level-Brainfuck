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

	public IfInstruction(Instruction parent, VarInstruction variable) {
		super(parent);
		this.variable = variable;
	}

	@Override
	public List<Instruction> getChildren() {
		return children;
	}

	@Override
	public void addChild(Instruction child) {
		children.add(child);
		
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
		
		bfCode += bfDataPointer.goTo(variable);
		bfCode += "[";
		bfCode += generateBfCode(ifInstructions, bfProgram);
		bfCode += bfDataPointer.goToCell(variable.getAvoidElseCellPos());
		bfCode += "-";
		bfCode += bfDataPointer.goToCell(variable.getEnterElseCellPos());
		bfCode += "]";
		bfCode += bfDataPointer.moveRight();
		bfCode += "+";
		bfCode += "[";
		// The weird part: realign the cursor with the context of the "else"
		bfDataPointer.moveLeft();
		bfCode += generateBfCode(elseInstructions, bfProgram);
		bfCode += bfDataPointer.goToCell(variable.getEnterElseCellPos());
		bfCode += "-";
		bfCode += bfDataPointer.goToCell(variable.getAvoidElseCellPos());
		bfCode += "]";
		
		return bfCode;
	}

	public void addChildToElse() {
		addToElseClause = true;
	}
}
