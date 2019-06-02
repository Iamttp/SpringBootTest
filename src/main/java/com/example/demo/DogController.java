package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DogController {
    //因为业务太简单，所有我们省略service层，直接调用数据访问层的代码
    @Autowired
    private DogRepository dogRepository;

    /**
     * 查询所有列表
     * /@GetMapping是一个组合注解 是@RequestMapping(method = RequestMethod.GET)的缩写
     * /@PostMapping是一个组合注解 是@RequestMapping(method = RequestMethod.POST)的缩写
     */
    @GetMapping(value = "/dogs")
    public List<Dog> dogList() {
        return dogRepository.findAll();//findAll()方法是底层帮我没实现好的
    }
}
