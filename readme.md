# 相关说明
## mybatis-generator-maven-plugin插件生成代码
- 需手动配置maven项，command line项配置如下内容
```
mybatis-generator:generate
```
- 设置generatorConfig.xml配置文件里mysql-connector-java的路径
```
C:\Users\Administrator\.m2\repository\mysql\mysql-connector-java\8.0.13\mysql-connector-java-8.0.13.jar
```
- 设置数据库连接
```
jdbc:mysql://121.40.94.96:3306/test?serverTimezone=GMT%2B8&amp;useInformationSchema=true
```
## 开子系统
- 将gpweb.unit改为1，同时在后台里配置对应的用户  
- gp-web项目同样要设置全局变量unit，用户判断线下支付审核是否显示编辑按钮