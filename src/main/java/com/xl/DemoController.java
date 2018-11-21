package com.xl;

import com.xl.dao.AccountMapper;
import com.xl.vo.Acoount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {
    @Autowired
    private AccountMapper accountMapper;

    @GetMapping("list")
    public List<Acoount> list(){
        return accountMapper.list(null);
    }
}
