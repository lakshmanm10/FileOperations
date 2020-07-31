/**
 * 
 */
package com.image.utility.service;

import java.io.IOException;

import com.drew.imaging.ImageProcessingException;

public interface MetaReaderService {
	
	public void printAll(String filename)  throws ImageProcessingException, IOException ;

}
