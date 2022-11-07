# 其他开发框架接入
如您公司有自己的一套开发框架及规范，又或者在用某套开源开发框架，可以参考此文档，来给这款开发框架进行对接，让这款开发框架快速具有自动写代码的能力。  
使用中欢迎联系我们，让我们来给你提供相关使用帮助。  

# 对接步骤

### 第一步，修改pom.xml

加入 writecode 的引入 （这里以操作mysql为例）

````
<!-- 加入 mysql数据源的writecode  -->
<dependency>
	<groupId>com.xnx3.writecode</groupId>
	<artifactId>core</artifactId>
	<version>1.1.1</version>
</dependency>
<dependency>
	<groupId>com.xnx3.writecode</groupId>
	<artifactId>datasource.mysql</artifactId>
	<version>1.1.1</version>
</dependency>
````

[如果maven拉取不下来，可参考 doc/mven_repository.md](mven_repository.md)

### 第二步，定制生成的模板
可直接参考 ````demo/src/main/java/com/xnx3/writecode/demo/simple/Demo.java````   
一个框架中，需要生成的文件肯定是有多个，比如实体类、控制器、列表页面、编辑页面...等，如此，每个需要生成的页面，也就类似于上面的Demo，需要写多个。这个可以参考[wm框架的生成](https://gitee.com/mail_osc/writecode/blob/master/template-wm/src/main/java/com/xnx3/writecode/template/wm/Code.java)