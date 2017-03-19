package com.github.high_level_brainfuck;

import java.io.IOException;
import java.util.logging.Logger;

import com.github.high_level_brainfuck.compiler.Compiler;
import com.github.high_level_brainfuck.compiler.file.BfFileWriter;
import com.github.high_level_brainfuck.compiler.generator.BfProgram;
import com.github.high_level_brainfuck.compiler.CompileException;

public class Main {
	
	private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) throws CompileException, IOException {
		MainArgs mainArgs = new MainArgs(args);
		Compiler compiler = new Compiler();
		BfFileWriter fileWriter = new BfFileWriter();
	
		BfProgram bfProgram = compiler.compileFile(mainArgs.getBfCodeFilePath());
		fileWriter.writeFile(bfProgram.getCode(), "bfcode.b");
		LOGGER.info("Brainfuck successfully codes generated in bfcode.b");
	}

	private static class MainArgs {
		
		private String bfCodeFilePath;

		public MainArgs(String[] args) {
			if (args.length == 1) {
				bfCodeFilePath = args[0];
			} else {
				// TODO - Print args usage
				LOGGER.severe("Invalid args.\n");
			}
		}
		
		public String getBfCodeFilePath() {
			return bfCodeFilePath;
		}
	}
}
