package com.github.high_level_brainfuck.compiler.instructions;

import java.util.ArrayList;
import java.util.List;

import com.github.high_level_brainfuck.compiler.generator.BfProgram;

public class InstructionRoot extends Instruction {
	private List<VarInstruction> varInstructions = new ArrayList<>();
	private List<Instruction> logicInstructions = new ArrayList<>();

	public InstructionRoot() {
	}

	public InstructionRoot(List<VarInstruction> varInstructions, List<Instruction> logicInstructions) {
		this.varInstructions = varInstructions;
		this.logicInstructions = logicInstructions;
	}
	
	@Override
	public void addChild(Instruction instruction) {
		super.addChild(instruction);
		
		if (instruction.getClass().equals(VarInstruction.class)) {
			varInstructions.add((VarInstruction) instruction);	
		} else {
			logicInstructions.add(instruction);
		}
	}

	public List<VarInstruction> getVarInstructions() {
		return varInstructions;
	}

	public List<Instruction> getLogicInstructions() {
		return logicInstructions;
	}

	@Override
	public String generateBfCode(BfProgram bfProgram) {
		String bfCode = "";
		
		for (VarInstruction varInstruction : varInstructions) {
			bfCode += varInstruction.generateBfCode(bfProgram);
		}
		
		for (Instruction instruction : logicInstructions) {
			bfCode += instruction.generateBfCode(bfProgram);
		}
		
		return bfCode;
	}
	
	public VarInstruction getVarByName(String varName) {
		for (VarInstruction varInstruction : varInstructions) {
			if (varName.equals(varInstruction.getVarName())) {
				return varInstruction;
			}
		}
		
		return null;
	}
	
	@Override
	public int getDepth() {
		return 0;
	}

	/**
	 * Returns the last added instruction at the specified depth.
	 * Returns null if the instruction is impossible to find.
	 * 
	 * @param currentDepth
	 */
	public Instruction getLastInstruction(int depth) {
		int currentDepth = 0;
		Instruction currentInstruction = this;
		
		while (depth != currentDepth) {
			List<Instruction> curChildren = currentInstruction.getChildren();
			
			if (curChildren.isEmpty()) {
				return null;
			}
			
			currentInstruction = curChildren.get(curChildren.size() - 1);
			currentDepth++;
		}
		
		return currentInstruction;
	}
}
