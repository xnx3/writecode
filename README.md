## 自动写代码
根据数据表，自动写出代码（实体类、controller、前端页面、接口文档……），你只需右键运行，鼠标点几下选择，就可完成整个操作。  
**不必花精力学它，让它写出你想要的代码，减少你工作量，那就够了。**

## 快速跑通Demo
1. 打开 ````demo/src/main/java/com/xnx3/writecode/demo/simple/Demo.java```` 
2. 配置好mysql连接信息
3. 运行。即可看到如下图：![image.png](https://res.zvo.cn/writecode/simple_demo.gif)   
其中 demo.template 便是模板文件

## 数据表的设计注意事项
注意，你完全按照自己意愿去设计数据表，也能正常使用，只不过按照这个方式来设计，生成的代码会更美观，避免再去做一些修改。  
另外你如果设计的有不合适的地方，运行本系统后，系统会进行自检，将不合适的地方会给你进行逐条进行提示（不影响向后执行），以便你更好的来使用它。  
![image.png](http://res.zvo.cn/writecode/database_table_info.png)   
[详细说明请查阅文档：doc/database_table.md](doc/database_table.md)

## 模板支持的标签
* 跟当前项目相关的，以 {project.xxx} 开头
* 数据表本身相关的，以 {database.table.xxx} 开头
* 数据表遍历字段相关的，以 {foreach.field...} 开头
* 更多扩展及逻辑判断处理等，直接用JavaScript脚本！使用标签 {javasciprt}
  
[详细说明请查阅文档： doc/tag.md](doc/tag.md)

## 数据源的适配
当前只适配了 [mysql数据源](/datasource-mysql/)  
如果您使用的时 Oracle、SqlServer 等数据源，可以进行相关数据源的扩展。当然，扩展也非常简单，您完全可以参考 [datasource-mysql](/datasource-mysql) 来进行创建即可。  
[详细说明请查阅文档： doc/datasource.md](doc/datasource.md)

## 当前已适配的开发框架

1. [wm快速开发框架 wm.zvo.cn ，使用说明：template-wm/README.md](template-wm/README.md)  
  
如果您是某个开源框架作者，欢迎联系我们，帮你免费定制符合您框架的自动写代码模板！   
无论你是Java、PHP、还是Net语言，都可使用此来做自动化写代码模板适配。   
[详细说明请查阅文档： doc/framework_extend_join.md](doc/framework_extend_join.md)  

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


## 交流联系
作者：管雷鸣  
QQ群：734884615
作者微信：xnx3com
微信公众号：wangmarket

