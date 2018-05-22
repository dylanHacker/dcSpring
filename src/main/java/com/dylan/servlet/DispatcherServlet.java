package com.dylan.servlet;

import com.dylan.annotation.Controller;
import com.dylan.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

    private List<String> classNames = new ArrayList<String>();

    private Map<String, Object> handlerMapping = new ConcurrentHashMap<String, Object>();

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
        doScanner(contextConfig.getProperty("scanPackage"));

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
        if (classNames.isEmpty()){
            return;
        }
        try {
            for (String className : classNames){
                Class<?> clazz = Class.forName(className);

                if (clazz.isAnnotationPresent(Controller.class)){
                    // Spring put a BeanDefinition into map instead a instance
                    String beanName = lowerCaseFirst(clazz.getSimpleName());
                    beanDefinitionMap.put(beanName,clazz.newInstance());
                } else if (clazz.isAnnotationPresent(Service.class)){

                } else {
                    continue;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void doScanner(String pkgName) {
        URL url = this.getClass().getClassLoader().getResource("/" + pkgName.replaceAll("\\.", "/"));

        if (url != null) {
            File classDir = new File(url.getFile());

            for (File file : classDir.listFiles()) {
                if (file.isDirectory()) {
                    doScanner(pkgName + "." + file.getName());
                } else {
                    classNames.add(pkgName + "." + file.getName().replace(".class", ""));
                }
            }
        } else {
            throw new IllegalArgumentException(pkgName + "package name can't be null");
        }

        System.out.println("aaa");

    }

    private void doLoadConfig(String contextConfigLocation) {
        // search and locate file by a Reader Object in Spring

        InputStream ins = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation.replace("classpath:", ""));

        try {
            contextConfig.load(ins);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != ins) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String lowerCaseFirst(String str){
        if (null == str || "".equals(str)){
            return "";
        }
        char[] chars = str.toCharArray();
        if (chars[0]< 'a'){
            chars[0] += 32;
        }
        return String.valueOf(chars);
    }
}
