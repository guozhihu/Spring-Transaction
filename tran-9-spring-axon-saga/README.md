* **分布式事务实现模式-事件溯源模式**  
* **Saga模式**  
事务驱动的业务流程管理模式  
通过开始事件、结束事件、过程中的事件完成整个业务流程  
保证多个事件处理方法执行期间实现事务性  
* **Axon Saga实现**  
StartSaga - SagaEventHandler - EndSaga  
使用associate将不同的事件关联到同一个Saga流程中  
正常的结束业务都通过EndSaga标签触发，超时使用EventScheduler，触发一个EndSaga  
一次业务流程的执行对应一个saga实例  
Saga实例状态和关联的事件会保存在数据库中  

* **Axon简单实例 Saga模式**  
**购票系统**  
**功能：** 创建用户、创建票，实现用户购票流程  
单系统服务  
使用Axon Saga  

