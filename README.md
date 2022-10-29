## 自动写代码
根据数据表，自动写出代码（实体类、controller、前端页面、接口文档……），直接运行使用。而你只需右键运行，鼠标点几下选择，就可完成整个操作。  
曾思考过，为了少写代码、提高效率，而花时间去学习某款框架，这一来一回，是否真的值得？而且框架更新换代这么快，学了一个又一个... **而我们最初的想法是将写代码的时间减少，效率更高**，无需投入精力学习它，拿来直接使用，才是我们需要的。  


## 快速使用
#### 1. 加入maven依赖

````
<dependency>
	<groupId>com.xnx3.writecode</groupId>
	<artifactId>core</artifactId>
	<version>1.0</version>
	<scope>provided</scope>
</dependency>
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

#### 3. 选择数据表，自动写出代码
选择那几个表要进行生成，选择后点击生成按钮，即可生成。
![image.png](https://res.zvo.cn/writecode/wm_demo_writecode_run.gif) 

#### 4. 运行，使用
![image.png](https://res.zvo.cn/writecode/write_page_runing.gif) 

## 目录结构

```
writecode                           项目
├─client                            子项目-桌面客户端运行软件，可打包成exe、dmg，在windows、mac系统中直接运行
├─core                              writecode的核心支持
├─datasource-mysql                  子项目-Mysql数据源的扩展实现
├─demo                              子项目-demo运行示例
├─doc                               文档相关
│  ├─images                         文档中的一些图片便是存在于此
│  ├─video                          文档中的一些视频文件存在于此
│  ├─datasource.md                  数据源的扩展，比如增加 SqlServer 数据源的对接
│  ├─database_table.md              数据表设计相关说明及注意事项
│  └─tag.md                         变量标签的文档
├─template-wm                       子项目-针对wm的模板文件，可自动生成wm框架的实体类、controller类、vo类、前端的列表页面、编辑页面等
├─pom.xml                           项目源代码 pom ( Maven )
└─README.md                         说明
```

## 框架适配
如果您是某个开源框架作者，star数 > 100 可联系我们免费帮你定制符合您框架的自动写代码模板！  
无论你是Java、PHP、还是Net语言，都可使用此来做自动化写代码模板适配。  

## 更多文档
* [变量标签](doc/tag.md)
* [数据表的设计注意事项](doc/database_table.md)
* [在wm及网市场云建站系统中的深度集成使用](template-wm/README.md)
* [其他数据库的扩展，如 SqlServer、Oracle 等](doc/datasource.md)
