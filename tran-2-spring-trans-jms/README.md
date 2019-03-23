# JmsTransactionManager
Spring提供了一个JmsTransactionManager用于对JMS ConnectionFactory做事务管理。这将允许JMS应用利用Spring的事务管理特性。JmsTransactionManager在执行本地资源事务管理时将从指定的ConnectionFactory绑定一个ConnectionFactory/Session这样的配对到线程中。JmsTemplate会自动检测这样的事务资源，并对它们进行相应操作。

在Java EE环境中，ConnectionFactory会池化Connection和Session，这样这些资源将会在整个事务中被有效地重复利用。在一个独立的环境中，使用Spring的SingleConnectionFactory时所有的事务将公用一个Connection，但是每个事务将保留自己独立的Session。

JmsTemplate可以利用JtaTransactionManager和能够进行分布式的 JMS ConnectionFactory处理分布式事务。

# Spring JMS事务实例
* Spring Boot中使用JMS
* Spring Boot ActiveMQ Starter
* 内置的可运行的ActiveMQ服务器
* 实现读写ActiveMQ的事务

# Spring JMS Session
* 通过Session进行事务管理
* Session是thread-bound（线程范围内）
* 事务上下文：在一个线程中的一个Session

# Spring JMS事务类型
* Session管理的事务 - 原生事务
* 外部管理的事务：JmsTransactionManager、JTA