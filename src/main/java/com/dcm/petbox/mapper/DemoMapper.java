package com.dcm.petbox.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.dcm.petbox.model.Film;

@Mapper
public interface DemoMapper {

	List<Film> getFilmList(int i);

}
