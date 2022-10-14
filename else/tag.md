当前支持的变量

## 大纲
* **变量标签** 用于代表某个动态变量
* **变量代码块** 用于做为循环输出的代码块

## 变量标签
用于代表某个动态变量
#### 大致简介
* **{project.xxxx}** project. 开头的，是跟当前项目相关的
* **{java.xxxx}** java. 开头的，则是跟java本身相关的
* **{database.xxxx}** database. 开头的，则是跟数据库相关的
* **{if.xxxx}** if. 开头的，则是如果有，就输出，如果没有，就不输出 (也就是输出空字符串) 

#### 标签列表
* **{project.path.absolute}** 当前项目的路径，在本机磁盘的绝对路径。格式如 E:\eclipseWork\git\writecode
* **{project.url.path}** 请求的URL路径，比如实际生成的列表请求应该是 /admin/user/list.jsp， 那么这里应该设置为 /admin/user/ 注意格式，以/开头，以/结尾 。 比如生成controller时，这个可以在Controller上的@RequestMapping用的，使用方式如： @RequestMapping("{project.url.path}{database.table.name.hump.upper}/")
  
* **{java.package}** 生成的实体类是在哪个包，格式如 com.xnx3.j2ee.entity  
* **{java.tostring}** toString 的字符串，如 User{id:1,username:管雷鸣,roleId:1}
  
* **{database.table.comment}** 数据表的备注，这个表是干什么的，如 用户表  
* **{database.table.name}** 数据表的名字，如 user_role
* **{database.table.name.hump.upper}** 数据表的名字的大驼峰式命名，如 UserRole 
* **{database.table.name.hump.lower}** 数据表的名字的小驼峰式命名，如 userRole
  
* **{database.table.field.comment}** 数据表中，字段的注释
* **{database.table.field.name}** 数据表中，字段名。如 role_id
* **{database.table.field.name.hump.lower}** 数据表中，字段名的小驼峰式命名。如本来是role_id，会输出为 roleId
* **{database.table.field.name.hump.upper}** 数据表中，字段名的大驼峰式命名。如本来是role_id，会输出为 RoleId
* **{database.table.field.default}** 数据表中，字段的默认值。一般这个标签不怎么用，二是用 {if.database.table.field.default}
* **{database.table.field.datatype}** 数据表中，字段的数据类型，如 int、char、varchar 等
* **{database.table.field.datatype.java}** 数据表中，字段的数据类型在Java中的表示，如 String、Integer、Short 等
* **{database.table.field.length}** 数据表中，字段数据的长度，比如类型是int、chat等类型，这里输出格式为 11 ,比如类型是float型，这里输出格式为 3,6
* **{database.table.field.collate}** 数据表中，字段的编码格式，针对字符串类型，防止乱码。如果数据表中本身是int型，并没有设置编码格式，那这个则没有任何输出。如果数据字段是char，设置了字符编码，那会输出如 COLLATE utf8mb4_unicode_ci
* **{if.java.annotation.id}** 如果当前字段是主键，则输出Java注解 @Id
* **{if.java.annotation.generatedvalue}** 如果当前字段比如是自增属性，则输出Java注解 @GeneratedValue(strategy = IDENTITY)
* **{if.database.table.field.default}** 如果当前数据表的字段有设置默认值，那么输出 default 'xxxx'

## 变量代码块
用于做为循环输出的代码块
#### 代码块 : {codeblock.field}

````{codeblock.field}```` 跟 ````{/codeblock.field}```` 中间的为循环输出数据表中的所有字段。如： 

````
{codeblock.field}
	private {java.field.datatype} {database.table.field.name.hump.lower};
{/codeblock.field}
````

#### 代码块 : {codeblock.field.edit}
意思同上 {codeblock.field} ，只不过输出的范围有变化
````{codeblock.field.edit}```` 跟 ````{/codeblock.field.edit}```` 中间的为循环输出数据表-增加及编辑页面中，需要用户自己编辑的字段

