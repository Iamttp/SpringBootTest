package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PeopleController {
    /**
     * /@ResponseBody 不走视图解析器
     */
    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello boot";
    }

    @Autowired
    private People people;

    @ResponseBody
    @RequestMapping(value = "/helloPeople", method = RequestMethod.GET)
    public String helloPeople() {
        return people.toString();
    }

    @RequestMapping(value = {"/t", "/temp", "/template"}, method = RequestMethod.GET)
    public String showIndexHtml() {
        return "index.html";
    }
}


