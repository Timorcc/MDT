package com.example.demo.background.service;

import com.example.demo.background.entity.BigSecretary;
import com.example.demo.background.entity.SmallSecretary;

import java.util.List;

public interface SmallSecretaryService {
    List<SmallSecretary> getSmallSecretaryList();
    Boolean AddSmallSecretary(String username, String telNum, String wxNum);
    SmallSecretary findById(Long id);
    Boolean updateById(Long id, String username, String telNum, String wxNum);
    List<SmallSecretary> fuzzyFind(String name);
}
