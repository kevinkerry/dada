package com.youyisi.fastdfs.application.impl;

import org.springframework.stereotype.Service;

import com.youyisi.fastdfs.application.FileService;
import com.youyisi.fastdfs.domain.FastDFSFile;
import com.youyisi.fastdfs.infrastructure.helper.FileManager;
@Service
public class FileServiceImpl implements FileService {

	@Override
	public String upload(FastDFSFile file) {
		return new FileManager().upload(file);
	}
}
