适用于 [wm快速开发的](http://wm.zvo.cn) 增加自动写代码的demo示例  

## 第一步，设计数据表
数据表设计注意事项，可参见： [database_table.md](database_table.md)

## 第二步，项目中增加一个 WeiteCode.java
![image.png](https://res.zvo.cn/writecode/wm_demo_java_tree.png)   

````
package com;

import com.xnx3.writecode.template.wm.Code;

/**
 * 代码生成器，自动生成实体类、controller、vo、jsp页面等
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

## 第三步，运行 WriteCode.java

![image.png](https://res.zvo.cn/writecode/wm_demo_writecode_run.gif)   


## 第四步，直接使用
运行起来项目，访问测试：  
localhost:8080/demo/person/list.jsp
