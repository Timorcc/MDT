package com.example.demo.background.service.impl;


import com.example.demo.background.entity.SmallSecretary;
import com.example.demo.background.mapper.SmallSecretaryMapper;
import com.example.demo.background.service.SmallSecretaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SmallSecretaryServiceImpl implements SmallSecretaryService {
    @Autowired
    SmallSecretaryMapper smallSecretaryMapper;

    @Override
    public List<SmallSecretary> getSmallSecretaryList() {
        try {
            return smallSecretaryMapper.findAll();
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    @Override
    public Boolean AddSmallSecretary(String username, String telNum, String wxNum) {
        try {
            return smallSecretaryMapper.AddSmallSecretary(username, telNum, wxNum);
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }

    @Override
    public SmallSecretary findById(Long id) {
        try {
            return smallSecretaryMapper.findById(id);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    @Override
    public Boolean updateById(Long id, String username, String telNum, String wxNum) {
        try {
            return smallSecretaryMapper.updateById(id, username, telNum, wxNum);
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }

    @Override
    public List<SmallSecretary> fuzzyFind(String name) {
        try {
            return smallSecretaryMapper.fuzzyFind(name);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }

    }
}
