package com.image.utility.service.impl;

import org.springframework.stereotype.Service;

import com.image.utility.service.UploadService;
import com.image.utility.utils.FilesUtil;

@Service("uploadService")
public class UploadServiceImpl implements UploadService {
	
	FilesUtil filesUtil;
	
	@Override
	public void uploadService(String filename) throws Exception {
		filesUtil = new FilesUtil();
		this.filesUtil.uploadFileToServersNew(filename);
	}

}
