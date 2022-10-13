## 测试生成
直接执行 com.xnx3.writecode.Demo 即可自动生成。

## 使用方式
在wm项目中的快速使用
#### 1. 引入 maven

````
<dependency>
	<groupId>com.xnx3.writecode</groupId>
	<artifactId>template.wm</artifactId>
	<version>1.0</version>
	<scope>provided</scope>
</dependency>
````

#### 2. 增加一个类,用于生成代码

````
package com.xnx3.writecode;
import com.xnx3.writecode.template.wm.Code;

/**
 * DEMO示例，自动生成实体类、controller、vo、jsp页面等
 * @author 管雷鸣
 */
public class Demo {
	public static void main(String[] args) {
		Code code = new Code();
		code.setPackageName("com.xnx3.test");	//设置生成的entity、controller、vo等java类放到哪个包下
		code.setProjectUrlPath("/admin/{database.table.name.hump.lower}/"); //设置url请求的路径
		code.write();	//创建相关代码文件
	}
}

````
直接运行，即可出现选择要生成哪些数据表的界面

#### 3. 生成代码

...