package com.example.demo.background.service;

import com.example.demo.background.entity.BigSecretary;

import java.util.List;

public interface BigSecretaryService {
    List<BigSecretary> getBigSecretaryList();
    Boolean AddBigSecretary(String username,String telNum,String wxNum);
    BigSecretary findById(Long id);
    Boolean updateById(Long id, String username, String telNum, String wxNum);
    List<BigSecretary> fuzzyFind(String name);
}
