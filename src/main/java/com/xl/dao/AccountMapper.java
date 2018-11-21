package com.xl.dao;

import com.xl.vo.Acoount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    List<Acoount> list(Acoount acoount);
    void update(Acoount acoount);
    void insert(Acoount acoount);
}
