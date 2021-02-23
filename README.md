![shanghao-idea](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/shanghao-idea.jpg)

#                                                   乐璟商城

> **微服务架构图**

![](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210222191025177.png)

# 1 技术架构选型

第二代微服务架构，`kubernetes`高可用集群，全自动`DevOps`，

[springcloud与springboot对应版本](https://start.spring.io/actuator/info)

![image-20210126234300478](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210126234300478.png)

[springboot的历史版本](https://docs.spring.io/spring-boot/docs/)

![image-20210126234531221](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210126234531221.png)

技术架构：

- `JDK11`
- `MySQL-8.0.23`
- `apache-maven-3.6.3`
- `spring-boot 2.3.9.RELEASE`
- `spring-cloud-Hoxton.SR10`
- `spring-cloud-alibaba 2.2.5.RELEASE`

**架构图**



# 2 IDEA环境JVM参数配置

> tips: 主要目的减少web服务的的内存占用

JVM参数: `-Xms512m -Xmx512m -Xmn200m -Xlog:gc*`

![image-20210207215344410](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210207215344410.png)

![image-20210207215556800](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210207215556800.png)



# 3 已完成清单

- [x] 分装好BaseController，用于Controller数据返回

![image-20210204005654609](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210204005654609.png)

- [x] 分装好BaseResult，返回结果封装 ，让接口的输入和输出更加明确

![image-20210204010657082](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210204010657082.png)

![image-20210204010822582](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210204010822582.png)



![image-20210204010952249](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210204010952249.png)

- [x] 重新改造代码生成, 所有接口都可以生成测试文档, 支持在线调试接口, 执行`mvn package`自动出api文档,访问地址改成自己的端口号:http://127.0.0.1:10000/debug-all.html,

  ![image-20210205025359610](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210205025359610.png)

![image-20210205024651157](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210205024651157.png)

> 基于Mybatis-Plus自动生成,支持复杂分页查询, 数据库变动只需要修改实体类即可

![image-20210205025122573](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210205025122573.png)

![image-20210205025022332](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210205025022332.png)

**![image-20210205024941585](https://alphahub-test-bucket.oss-cn-shanghai.aliyuncs.com/image/image-20210205024941585.png)**

- [x] 项目改造完成, 集成: 业务代码自动生成 -> 执行: `mvn package`, 自动输出可调式的高可读性api文档 -> 分层架构 -> 以最小的代价迎合业务的重大变更





# 4 待完成清单

- [x] 整合人人框架，提供：domain--> dao --> service --> controller --> 全自动api文档在线输出&在线调试，

  目的：让今后因业务变动影响数据库的变动只需要码农修改数据库对应的领域模型即可，节省更多的时间

- [ ] 微服务计划

- [x] 移除`spring-cloud-alibaba 2.2.5.RELEASE` 版本中微服务中通过`feign`远程调用时使用`ribbon`负载均衡的支持, 采用`spring-cloud 2020.0.1`的`spring cloud loadbalancer`代替



# 5 TIPS

> 本项目的`api`文档输出基于smart-doc和common工程里面我写的一些类完成的，不建议把common工程上传到maven私服上面，这会导致项目执行：
>
> `mvn package`的时候不能读取common里面的一些相关类的注释，因为`java`最终编译成字节码文件后会把所有注释都清理掉，也就是项目最终的class文件不包含任何注释

