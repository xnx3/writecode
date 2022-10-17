适用于 [wm快速开发的](http://wm.zvo.cn) 增加自动写代码的demo示例  

## 第一步，mysql库中增加用于demo演示的数据表 person

![image.png](https://res.zvo.cn/writecode/wm_demo_table.png)   
创建表的SQL语句：  

````
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` char(20) DEFAULT NULL COMMENT '姓名，用户的名字',
  `age` int(11) DEFAULT NULL COMMENT '年龄，几岁，如 28 则是28岁',
  `idcard` char(30) DEFAULT NULL COMMENT '身份证号',
  `addtime` int(11) DEFAULT '0' COMMENT '添加时间，这条信息的录入时间，10位时间戳',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='人员表，演示 entity 类';
````

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

## 第三步，运行 WeiteCode.java

<video src="https://res.zvo.cn/writecode/wm_demo_writecode_run.mov">
</video>

## 第四步，使用
