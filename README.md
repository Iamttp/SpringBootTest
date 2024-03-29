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
   


## SpringBoot 原理分析与知识点详解 （IDEA版）

`https://www.bilibili.com/video/av52867311`

spring：
    组件轻量级 配置重量级

spring boot：
    约定优于配置 
    内置Tomcat服务器 
    使用spring-boot-admin对spring-boot服务进行监控

spring boot 核心功能：

* 起步依赖：

> 起步依赖本质是一个Mevan炫目对象模型POM (project object model)。起步依赖这里看到的spring-boot-starter-xxx就是SpringBoot的起步依赖。SpringBoot通过提供众多起步依赖降低项目依赖的复杂度。起步依赖本质上是一个Maven项目对象模型，定义了对其他库的传递依赖，这些东西加在一起即支持某项功能。很多起步依赖的命名都暗示了他们提供的某种或某类功能。

* 自动配置：

> ...
    
@ResponseBody 不走视图解析器 (可以直接return字符串) 

1. 添加依赖

```xml
 <!-- 热部署-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
</dependency>
```

2. idea 热部署：

1) setting -> compiler -> ...

2) Ctrl + alt + A 输入 Registry 勾选comple...running

### spring boot 原理分析

**生成依赖**：

ctrl + 点击<artifactId>spring-boot-starter-parent</artifactId>

资源引入：

```xml
<resources>
    <resource>
        <filtering>true</filtering>
        <directory>${basedir}/src/main/resources</directory>
        <includes>
            <include>**/application*.yml</include>
            <include>**/application*.yaml</include>
            <include>**/application*.properties</include>
        </includes>
    </resource>
    <resource>
        <directory>${basedir}/src/main/resources</directory>
        <excludes>
            <exclude>**/application*.yml</exclude>
            <exclude>**/application*.yaml</exclude>
            <exclude>**/application*.properties</exclude>
        </excludes>
    </resource>
</resources>
```
...

继续点击 spring-boot-dependencies

...

同理点击 spring-boot-starter-web 可以看到spring等

**自动配置**：

ctrl + 点击`@SpringBootApplication`进入注解
```java
// 包扫描 引导类所在包下面扫描
@ComponentScan(excludeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
```

JDK1.5之后内部提供的三个注解(和反射有关)

```
@Deprecated 意思是“废弃的，过时的”
@Override 意思是“重写、覆盖”
@SuppressWarnings 意思是“压缩警告”
```

> 总结：注解(Annotation)相当于一种标记，在程序中加入注解就等于为程序打上某种标记，没有加，则等于没有任何标记，以后，javac编译器、开发工具和其他程序可以通过反射来了解你的类及各种元素上有无何种标记，看你的程序有什么标记，就去干相应的事，标记可以加在包、类，属性、方法，方法的参数以及局部变量上。


默认配置：

![avatar](1.png)

**@Configuration     @Bean**

Spring中为了减少xml中配置，可以声明一个配置类（例如SpringConfig）来对bean进行配置。

@Bean是一个方法级别上的注解，主要用在@Configuration注解的类里，也可以用在@Component注解的类里。添加的bean的id为方法名

下面是@Configuration里的一个例子

```java
@Configuration
public class AppConfig {
    @Bean
    public TransferService transferService() {
        return new TransferServiceImpl();
    }
}
```

这个配置就等同于之前在xml里的配置

```xml
<beans>
    <bean id="transferService" class="com.acme.TransferServiceImpl"/>
</beans>
```

YML 以数据为核心 比XML更加简洁

文件扩展名为 .yml .yaml

### 整合Mybatis

1. 添加Mybatis的起步依赖

    ```xml
    <!-- mybatis依赖， mybatis提供的起步依赖-->
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>1.1.1</version>
    </dependency>
    ```
    
2. 添加数据库驱动坐标

    ```xml
    <!-- 数据库连接驱动-->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
    ```
    
3. 添加数据库连接信息

    ```yml
    spring:
        datasource:
            driver-class-name: com.mysql.jdbc.Driver
            url: jdbc:mysql://127.0.0.1:3306/myDataBase
            username: root
            password: root
    ```
    
4. 创建user表

    ```mysql
    # TODO 数据库创建user表
    ```

5. 创建实体Bean

    ```java
    //TODO 定义java对象类
    ```
    
6, 编写Mapper

   > resource: 
   >
   > mapper: 
   >
   > UserMapper.xml
   >
   
            
    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
    <mapper namespace="com.example.demo">
        <select id="queryUserList" resultMap="user">
            select *from user
        </select>
    </mapper>
    ```
    
7. 配置mapper配置文件


8. 在application.yml添加mybatis的信息
9. 编写测试controller
10. 测试


## 比较总结:

Hibernate 对数据库提供了较为完整的封装,封装了基本的DAO层操作,有较好的数据库移植性

Mybatis 可以进行更细致的SQL优化,查询必要的字段,但是需要维护SQL和查询结果集的映射,而且数据库的移植性较差,针对不同的数据库编写不同的SQL,

Spring Data JPA 极大的简化了数据库访问,可以通过命名规范、注解的方式较快的编写SQL。