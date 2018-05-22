package com.dylan.demo.service.impl;

import com.dylan.annotation.Service;
import com.dylan.demo.service.api.DemoService;

/**
 * @Author: Dylan.c
 * @Date: 2018/5/22 下午10:33
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String get(String name) {
        return "this is " + name;
    }
}
