package com.image.utility.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.image.utility.CommonUtilityApplication;

import sun.misc.BASE64Decoder;

public class FilesUtil {

	private static final Logger logger = LogManager.getLogger(CommonUtilityApplication.class);
	
	public void uploadFileToServersNew(String filename) throws Exception {

		Path sourcepath = Paths.get(filename);
		logger.info(sourcepath.toAbsolutePath());
		Path path = Paths.get("C:/download/E");
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				throw e;
			}
		}
		
			//File inputFile = new File(filename);
			Path copyfilepath = path.resolve(filename);
			Path movefilepath = path.resolve("movedFile");
			try {
				if (Files.exists(copyfilepath)) {
					Files.deleteIfExists(copyfilepath);
				}
				logger.info(copyfilepath.toAbsolutePath());
				
			//	Path newFile = Files.createFile(newFilePath);
				Files.copy(sourcepath, copyfilepath);
				Files.move(sourcepath, movefilepath);
			} catch (IOException e) {
				throw e;
			}
		}
}
