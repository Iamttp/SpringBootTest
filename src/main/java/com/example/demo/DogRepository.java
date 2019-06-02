package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 这个接口需要继承一个接口JpaRepository
 * 这样就可以使用底层为我们编写好的一些通用方法
 */
public interface DogRepository extends JpaRepository<Dog, Integer> {
}