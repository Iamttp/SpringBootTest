package com.example.demo;

import javax.persistence.*;

@Entity
@Table(name = "dog")
public class Dog {
    /*
       如果这些注解所在的包，javax.persistence不能导入。可能是缺少依赖
       可以在pom中加入Javax Persistence API 的依赖
    */
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private Integer age;
    @Column
    private String name;

    public Dog() {
    }

    public Dog(Integer id, Integer age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
