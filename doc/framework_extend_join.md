# 其他开发框架接入
如您公司有自己的一套开发框架及规范，又或者在用某套开源开发框架，可以参考此文档，来给这款开发框架进行对接，让这款开发框架快速具有自动写代码的能力。

# 对接步骤

### 第一步，修改pom.xml

加入 writecode 的引入 （这里以操作mysql为例）

````
<!-- 加入 mysql数据源的writecode  -->
<dependency>
	<groupId>com.xnx3.writecode</groupId>
	<artifactId>core</artifactId>
	<version>1.1</version>
</dependency>
<dependency>
	<groupId>com.xnx3.writecode</groupId>
	<artifactId>datasource.mysql</artifactId>
	<version>1.0</version>
</dependency>
````
