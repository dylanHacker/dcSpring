package com.dylan.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Dylan.c
 * @Date: 2018/5/20 下午6:41
 */
public class DispatcherServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private Map<String, Object> beanDefinitionMap = new ConcurrentHashMap<String, Object>();

    private List<String> beanNames = new ArrayList<String>();

    private Map<String,Object> handlerMapping = new ConcurrentHashMap<String, Object>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
//        super.init(config);

        // start to init
        // location -- read properties file into map
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        // load -- scan the beans that need to instanced
        doScanner();

        // register -- instance the bean scanned just now
        doRegistry();

        // dependency inject automatically
        doAutowired();

        // init HandlerMapping -- combine a method with a request url pattern in @RequestMapping file
        initHandlerMapping();
    }

    private void initHandlerMapping() {
    }

    private void doAutowired() {
    }

    private void doRegistry() {
    }

    private void doScanner() {
    }

    private void doLoadConfig(String contextConfigLocation) {
        // search and locate file by a Reader Object in Spring

        InputStream ins = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation.replace("classpath:",""));

        try {
            contextConfig.load(ins);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != ins){
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
