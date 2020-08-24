package com.example.demo.background.service.impl;

import com.example.demo.background.entity.BigSecretary;
import com.example.demo.background.mapper.BigSecretaryMapper;
import com.example.demo.background.service.BigSecretaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BigSecretaryServiceImpl implements BigSecretaryService {
    @Autowired
    BigSecretaryMapper bigSecretaryMapper;

    @Override
    public List<BigSecretary> getBigSecretaryList() {
        try {
            return bigSecretaryMapper.findAll();
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    @Override
    public Boolean AddBigSecretary(String username, String telNum, String wxNum) {
        try {
            return bigSecretaryMapper.AddBigSecretary(username, telNum, wxNum);
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }

    @Override
    public BigSecretary findById(Long id) {
        try {
            return bigSecretaryMapper.findById(id);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    @Override
    public Boolean updateById(Long id, String username, String telNum, String wxNum) {
        try {
            return bigSecretaryMapper.updateById(id, username, telNum, wxNum);
        } catch (Exception e) {
            log.error(e.toString());
            return false;
        }
    }

    @Override
    public List<BigSecretary> fuzzyFind(String name) {
        try {
            return bigSecretaryMapper.fuzzyFind(name);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }

    }
}
