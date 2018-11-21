package com.xl.service;

import com.xl.dao.AccountMapper;
import com.xl.vo.Acoount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemoService {
    @Autowired
    private AccountMapper accountMapper;

    @Transactional
    public void update(){
        Acoount acoount = new Acoount();
        acoount.setName("张三");
        acoount.setId(2);
        accountMapper.update(acoount);
        Acoount acoount2 = new Acoount();
        acoount2.setName("李四");
        acoount2.setId(3);
        accountMapper.update(acoount2);
    }
}
