适用于 [wm快速开发的](http://wm.zvo.cn) 增加自动写代码的demo示例  

## 前提条件
#### wm v2.24 及更高版本
可直接使用，无须什么准备，因为在你使用的这个父类中就已经加入了

````
<parent>
    <groupId>com.xnx3.wangmarket.wm</groupId>
    <artifactId>parent</artifactId>
    <version>2.24</version>
</parent>
````

#### wm v2.24 以前的版本
需要先将wm升级到 wm v2.24版本。具体pom.xml 更改如下，增加这两个引入

````
<!-- wm v2.24版本 http://wm.zvo.cn -->
<dependency>
	<groupId>com.xnx3.wangmarket.wm</groupId>
	<artifactId>wm</artifactId>
	<version>2.24</version>
</dependency>
<!-- writecode 自动写代码 http://gitee.com/mail_osc/writecode -->
<dependency>
  <groupId>com.xnx3.writecode</groupId>
  <artifactId>template.wm</artifactId>
  <version>1.1</version>
  <scope>provided</scope>
</dependency>
````

## 第一步，设计数据表
数据表设计注意事项，可参见： [database_table.md](../doc/database_table.md)

## 第二步，运行 WriteCode.java 写代码
WriteCode.java 可以自己在com包下建一个，代码：

````
package com;
import com.xnx3.writecode.template.wm.Code;
/**
 * 自动写代码，根据数据表写出实体类、controller、vo、jsp页面等
 * @author 管雷鸣
 */
public class WriteCode {
	public static void main(String[] args) {
		Code code = new Code();
		code.setPackageName("com.xnx3.demo");	//设置生成的entity、controller、vo等java类放到哪个包下
		code.setProjectUrlPath("/demo/"); //设置url请求的路径
		code.write();	//执行
	}
}
````

![image.png](https://res.zvo.cn/writecode/wm_demo_writecode_run.gif) 


## 第三步，使用
运行起来项目，访问测试：  
localhost:8080/demo/person/list.jsp  

![image.png](https://res.zvo.cn/writecode/write_page_runing.gif) 

