## 自动写代码
根据数据表，自动写出代码（实体类、controller、前端页面、接口文档……），直接运行使用。而你只需右键运行，鼠标点几下选择，就可完成整个操作。  
曾思考过，为了少写代码、提高效率，而花时间去学习某款框架，这一来一回，是否真的值得？而且框架更新换代这么快，学了一个又一个... **而我们最初的想法是将写代码的时间减少，效率更高**，无需投入精力学习它，拿来直接使用，才是我们需要的。  


## 快速跑通Demo
1. 打开 ````demo/src/main/java/com/xnx3/writecode/demo/simple/Demo.java```` 
2. 配置好mysql连接信息
3. 运行。即可看到如下图：![image.png](https://res.zvo.cn/writecode/simple_demo.gif) 

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
[您可参考此方式为您的框架增加自动写代码功能](doc/framework_extend_join.md)  
如果您是某个开源框架作者，star数 > 100 可联系我们免费帮你定制符合您框架的自动写代码模板！  
无论你是Java、PHP、还是Net语言，都可使用此来做自动化写代码模板适配。  

## 更多文档
* [支持的调用标签](doc/tag.md)
* [数据表的设计注意事项](doc/database_table.md)
* [在wm及网市场云建站系统中的深度集成使用](template-wm/README.md)
* [其他数据库的扩展，如 SqlServer、Oracle 等](doc/datasource.md)
