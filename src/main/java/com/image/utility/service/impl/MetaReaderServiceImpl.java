/**
 * 
 */
package com.image.utility.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.image.utility.service.MetaReaderService;

@Service("metaReaderService")
public class MetaReaderServiceImpl implements MetaReaderService {

	private static final Logger logger = LogManager.getLogger(MetaReaderServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ust.metainfo.service.MetaReaderService#printAll()
	 */
	@Override
	public void printAll(String filename) throws ImageProcessingException, IOException {
		File file = new File(filename);
		Metadata metadata = ImageMetadataReader.readMetadata(file);

		for (Directory directory : metadata.getDirectories()) {
			for (Tag tag : directory.getTags()) {
				// logger.info("[%s] - %s = %s", directory.getName(), tag.getTagName(),
				// tag.getDescription());
				logger.info("[" + directory.getName() + "]" + " - " + tag.getTagName() + " = " + tag.getDescription());
			}
			if (directory.hasErrors()) {
				for (String error : directory.getErrors()) {
					System.err.format("ERROR: %s", error);
				}
			}
		}

	}

}
