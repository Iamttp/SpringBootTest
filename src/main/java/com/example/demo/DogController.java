package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DogController {
    /**
     * 因为业务太简单，所有我们省略service层，直接调用数据访问层的代码
     */
    @Autowired
    private DogRepository dogRepository;

    /**
     * 查询所有列表
     * /@GetMapping是一个组合注解 是@RequestMapping(method = RequestMethod.GET)的缩写
     * /@PostMapping是一个组合注解 是@RequestMapping(method = RequestMethod.POST)的缩写
     */
    @GetMapping(value = "/dogs")
    public List<Dog> dogList() {
        //findAll()方法是底层帮我没实现好的
        return dogRepository.findAll();
    }

    @GetMapping(value = "/dogs/{id}")
    public Optional<Dog> findDogById(@PathVariable("id") Integer id) {
        return dogRepository.findById(id);
    }

    @PostMapping(value = "addDogs")
    public Dog addDogs(
            @RequestParam("age") Integer age,
            @RequestParam("name") String name,
            @RequestParam("id") Integer id) {
        Dog dog = new Dog();
        dog.setAge(age);
        dog.setName(name);
        dog.setId(id);
        return dogRepository.save(dog);
    }
}
