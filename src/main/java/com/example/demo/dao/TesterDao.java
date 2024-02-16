package com.example.demo.dao;

import com.example.demo.model.Tester;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TesterDao {
    public List<Tester> selectList();
}
