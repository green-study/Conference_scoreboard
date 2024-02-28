package com.example.demo.controller;
import com.example.demo.dao.TesterDao;
import com.example.demo.model.Tester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Controller;

@Controller
public class TesterController {

    private final TesterDao testerDao;

    public TesterController(TesterDao testerDao) {
        this.testerDao = testerDao;
    }

    @GetMapping("/main")
    public String main(Model model) {
        List<Tester> testers = testerDao.selectList();
        testers.sort(Comparator.comparing(Tester::getTno).reversed());
        model.addAttribute("testers", testers);
        return "main";
    }

    @GetMapping("/sub")
    public String sub(Model model) {
        List<Tester> testers = testerDao.selectList();
        testers.sort(Comparator.comparing(Tester::getTscore)
                .reversed()
                .thenComparing(Tester::getTno, Comparator.reverseOrder()));
        model.addAttribute("testers", testers);
        if(testers.size() > 10){
            int size = testers.size();
            for(int i = 0; i<size-10; i++){
                testers.remove(10);
            }
        }

        return "sub";
    }

    @GetMapping("/mix")
    public String mix(Model model) {
        List<Tester> testers2 = testerDao.selectList();
        testers2.sort(Comparator.comparing(Tester::getTno).reversed());
        model.addAttribute("testers2", testers2);
        List<Tester> testers1 = testerDao.selectList();
        testers1.sort(Comparator.comparing(Tester::getTscore)
                .reversed()
                .thenComparing(Tester::getTno, Comparator.reverseOrder()));
        model.addAttribute("testers1", testers1);
        if(testers1.size() > 10){
            int size = testers1.size();
            for(int i = 0; i<size-10; i++){
                testers1.remove(10);
            }
        }

        return "mix";
    }

    @GetMapping("/admin")
    public String showAdminPage(Model model) {

        return "admin";
    }

}
