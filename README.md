# Spring Boot

* 参考文章：

`https://www.cnblogs.com/zmfx/p/9020514.html`

* 完成功能：

1. `@Controller` `@RequestMapping` 路由

2. `@Autowired` `@ConfigurationProperties` 对application.yml定义值进行自动装配
   
   需要`spring-boot-configuration-processor` configuration的依赖
    
3. `spring-boot-starter-thymeleaf` 模板依赖,返回html

4. `@Autowired` 对数据库进行装配

    `spring-boot-starter-data-jpa` `mysql-connector-java` JPA（Java Persistence API）定义了一系列对象持久化的标准，目前实现这一规范的产品有Hibernate、TopLink等。
    
    继承一个接口JpaRepository