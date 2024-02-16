package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScoreMapper {

    @Select("SELECT tscore FROM TESTER ORDER BY tscore DESC FETCH FIRST 3 ROWS ONLY")
    List<Integer> getTop3ScoresFromDB();

    @Select("SELECT tscore FROM TESTER ORDER BY tscore DESC FETCH FIRST 10 ROWS ONLY")
    List<Integer> getTop10ScoresFromDB();

    @Select("SELECT tscore FROM TESTER ORDER BY tno DESC FETCH FIRST 1 ROWS ONLY")
    int getLatestScore();
}