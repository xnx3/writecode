根据数据表自动写出实体类代码、增删查改。  
关于低代码开发-那些为了让技术少写代码而要花精力去学习另一款框架的，都是耍流氓！框架太多，如何跟得上！  

## 快速使用
#### 1. 加入maven依赖

````
<dependency>
	<groupId>com.xnx3</groupId>
	<artifactId>autowritecode</artifactId>
	<version>1.0</version>
<dependency>
````

#### 2. 增加java文件
在你放实体类的包下，增加一个java文件: WriteCode.java ,其内容为

````
package com.xnx3.entity;

import com.xnx3.autowritecode.WriteCode;
import com.xnx3.autowritecode.interfaces.impl.Mysql;

public class WriteCode {
	public static void main(String[] args) {
		
		WriteCode code = new WriteCode(new Mysql("local.mysql.leimingyun.com", 3306, "wangmarket", "root", "111111"));
		code.writeEntityCodeBySelectTableUI();
	}
}

````