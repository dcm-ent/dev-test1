package com.dcm.petbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcm.petbox.mapper.DemoMapper;
import com.dcm.petbox.model.Film;
import com.dcm.petbox.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
	@Autowired
	private DemoMapper demoMapper;
	
	@Override
	public List<Film> getFilmList() {
		return demoMapper.getFilmList(10);
	}

}
