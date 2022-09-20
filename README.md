根据数据表自动写出实体类代码、增删查改。  
关于低代码开发-那些为了让技术少写代码而要花精力去学习另一款框架的，都是耍流氓！框架太多，如何跟得上！  

## 快速使用
#### 1. 加入maven依赖

````
<dependency>
	<groupId>com.xnx3</groupId>
	<artifactId>writecode</artifactId>
	<version>1.0</version>
	<scope>provided</scope>
<dependency>
````

#### 2. 增加java文件
你想在哪个包增加实体类，就在哪个包增加一个java文件: WriteCode.java ,其内容为

````
package com.xnx3.demo;

import com.xnx3.autowritecode.WriteCode;
import com.xnx3.autowritecode.interfaces.impl.Mysql;

/**
 * 最简单的入门使用
 * @author 管雷鸣
 */
public class WriteCode {
	public static void main(String[] args) {
		String host = "local.mysql.leimingyun.com";	//主机，可以填写域名或ip
		int port = 3306;			//端口号
		String databaseName = "wangmarket"; //数据表的名字
		String username = "root"; 	//数据库登录用户名
		String password = "111111";	//数据库登录密码
		
		WriteCode code = new WriteCode(new Mysql(host, port, databaseName, username, password));
		code.writeEntityCodeBySelectTableUI();
		
	}
}

````

将 host、port、databaseName、username、password 填上，然后直接运行即可看到下图。

#### 3. 选择数据表，进行生成对应的实体类
选择那几个表要进行生成，选择后点击生成按钮，即可生成。
![image.png](./else/images/selectTableUI.png) 