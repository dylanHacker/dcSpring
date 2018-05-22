package com.dylan.demo.action;

import com.dylan.annotation.Autowired;
import com.dylan.annotation.Controller;
import com.dylan.annotation.RequestMapping;
import com.dylan.annotation.RequestParam;
import com.dylan.demo.service.api.DemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Dylan.c
 * @Date: 2018/5/22 下午10:45
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/query.json")
    public void query(HttpServletRequest req, HttpServletResponse res, @RequestParam String name){
        String result = demoService.get(name);
        try {
            res.getWriter().write(result);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
