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
    
    注： ubuntu18.04的MySQL默认需要sudo才能连接上
    在下面的文件后面加上
    
    ```shell
    sudo gedit /etc/mysql/my.cnf
    ```
    
    >    [mysqld]
    >
    >    skip-grant-tables
    
    之后重启mysql
    
    ```shell
    sudo service mysql stop
    sudo service mysql start
    ```
    
    实体定义时import应为：
    
    `import javax.persistence.*;`

5. @RequestParam("age") 获取POST参数
   完成数据库的增删改查
   
   Ubuntu下 Postman（模拟POST\GET） 的安装
   
   > `sudo apt-get install libgconf-2-4`
   > 
   > `sudo axel -o Downloads/ -n 4 https://dl.pstmn.io/download/latest/linux64`
   > 
   > 测试运行
   > `sudo ./Postman `
   > 
   > 设置直接Postman运行
   > `sudo ln -s  ~/Downloads/Postman/Postman   /usr/bin/`
   
