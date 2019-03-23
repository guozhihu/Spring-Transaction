# JTA事务实例---单数据源
使用Spring JTA事务管理  
Atomikos外部事务管理器提供JTA事务管理  
使用一个数据库-单数据资源

## 使用方式
在tran-1-spring-trans-jpa工程的pom.xml引入如下依赖即可<br>
\<dependency><br>
&ensp;&ensp;&ensp;&ensp;&ensp;\<groupId>org.springframework.boot</groupId><br>
&ensp;&ensp;&ensp;&ensp;&ensp;\<artifactId>spring-boot-starter-jta-atomikos</artifactId><br>
\</dependency><br>
