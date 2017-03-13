package com.github.high_level_brainfuck.compiler;

public class CompileException extends Exception {

	private static final long serialVersionUID = 6383424327943740871L;

	public CompileException(Throwable e) {
		super(e);
	}

	public CompileException(String message) {
		super(message);
	}

	public CompileException(String string, int rawLineNumber) {
		super("Line " + rawLineNumber + ": " + string);
	}

	public CompileException(int rawLineNumber) {
		super("Line " + rawLineNumber + ": Syntax error.");
	}
	
	public static CompileException newUnknownVar(int rawLineNumber) {
		return new CompileException("Line " + rawLineNumber + ": Syntax error.", rawLineNumber);
	}
}
