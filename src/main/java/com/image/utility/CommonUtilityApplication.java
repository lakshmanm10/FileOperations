package com.image.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.drew.imaging.ImageProcessingException;
import com.image.utility.service.MetaReaderService;
import com.image.utility.service.UploadService;
import com.image.utility.utils.FilesUtil;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class CommonUtilityApplication implements CommandLineRunner {

	private static final Logger logger = LogManager.getLogger(CommonUtilityApplication.class);

	@Autowired
	private MetaReaderService metaReaderService;
	
	@Autowired
	private UploadService uploadService;
	
	private FilesUtil filesUtil;

	public static void main(String[] args) {
		SpringApplication.run(CommonUtilityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("Number of arguments: " + args.length);
		if (args.length < 2) {
			printUsage();
			return;
		}

		String option = args[0];

		switch (option) {
		case "1":
			this.printAllMeta(args);
			break;
		case "2":
			this.fileUpload(args);
		default:
			printUsage();
			break;
		}

	}
	
	private void printAllMeta(String... args) throws ImageProcessingException, IOException {
		this.metaReaderService.printAll(args[1]);
		logger.info("Completed.");
	}
	
	private void fileUpload(String... args) throws Exception {
		this.uploadService.uploadService(args[1]);
	}

	private static final void printUsage() {
		logger.info("First argument should be 1 or 2");
		logger.info("1 to print all metadata inside file");
		logger.info(
				"2 to generate tiff file from bunch of Jpg/png. e.g.: java -jar metainfo.0.0.1-SNAPSHOT 2 1.jpg 2.jpg 3.jpg");
		logger.info(
				"3(In Progress) extract images from tiff");
		logger.info(
				"4 to check tiff is corrupted");
		logger.info(
				"5 File Upload");
	}

}
