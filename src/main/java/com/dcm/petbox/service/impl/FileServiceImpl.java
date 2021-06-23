package com.dcm.petbox.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcm.petbox.mapper.FileMapper;
import com.dcm.petbox.model.FileDTO;
import com.dcm.petbox.service.FileService;

@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileMapper fileMapper;

	@Override
	public void saveFileInfo(FileDTO dto) {
		fileMapper.saveFileInfo(dto);
	}
	

}
