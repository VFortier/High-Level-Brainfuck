package com.github.high_level_brainfuck.compiler;

import java.io.IOException;
import java.net.URISyntaxException;

import com.github.high_level_brainfuck.compiler.file.BfGenFileReader;
import com.github.high_level_brainfuck.compiler.generator.BfProgram;
import com.github.high_level_brainfuck.compiler.generator.BfProgramGenerator;
import com.github.high_level_brainfuck.compiler.parser.BfGenParser;

public class Compiler {
	
	public Compiler() {}
	
	public BfProgram compileFile(String bfGenCodeFilePath) throws CompileException {
		BfGenFileReader fileReader = new BfGenFileReader();
		String bfGenCode;
		try {
			bfGenCode = fileReader.readBfGenFile(bfGenCodeFilePath);
		} catch (URISyntaxException | IOException e) {
			throw new CompileException(e);
		}
		
		return compile(bfGenCode);
	}
	
	public BfProgram compile(String bfGenCode) throws CompileException {
		BfGenParser bfGenParser = new BfGenParser();
		BfProgramGenerator bfProgramGenerator = new BfProgramGenerator();
		
		BfGenProgram bfGenProgram = bfGenParser.parse(bfGenCode);
		BfProgram BfProgram = bfProgramGenerator.generate(bfGenProgram);
		
		return BfProgram;
	}
}
