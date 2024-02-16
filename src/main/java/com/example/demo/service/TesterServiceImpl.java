package com.example.demo.service;
import com.example.demo.model.Tester;
import com.example.demo.dao.TesterDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TesterServiceImpl implements TesterService {

    @Autowired
    TesterDao testerDao;

    @Override
    public List<Tester> selectList() {
        return testerDao.selectList();
    }
}
