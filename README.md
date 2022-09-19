# AutoWriteCode

## 模板
* **{java.xxxx}** java. 开头的，则是跟java本身相关的
* **{database.xxxx}** database. 开头的，则是跟数据库相关的
* **{if.xxxx}** if. 开头的，则是如果有，就输出，如果没有，就不输出  

#### entity.template  
* **{java.package}** 生成的实体类是在哪个包，格式如 com.xnx3.j2ee.entity  
* **{java.datatype}** 数据类型。会自动根据当前数据表中字段的类型来赋予。如 String、Integer、Boolean、Float等
* **{java.tostring}** toString 的字符串，如 User{id:1,username:管雷鸣,roleId:1}

* **{database.table.comment}** 数据表的备注，这个表是干什么的，如 用户表  
* **{database.table.name}** 数据表的名字，如 user  
* **{database.table.name.hump.upper}** 数据表的名字的大驼峰式命名，如 User  
* **{database.table.field.comment}** 数据表中，字段的注释
* **{database.table.field.name}** 数据表中，字段名。如 role_id
* **{database.table.field.name.hump.lower}** 数据表中，字段名的小驼峰式命名。如本来是role_id，会输出为 roleId
* **{database.table.field.name.hump.upper}** 数据表中，字段名的大驼峰式命名。如本来是role_id，会输出为 RoleId
* **{database.table.field.default}** 数据表中，字段的默认值。一般这个标签不怎么用，二是用 {if.database.table.field.default}
* **{database.table.field.datatype}** 数据表中，字段的数据类型，如 int、char、varchar 等
* **{database.table.field.length}** 数据表中，字段数据的长度，比如类型是int、chat等类型，这里输出格式为 11 ,比如类型是float型，这里输出格式为 3,6
* **{database.table.field.collate}** 数据表中，字段的编码格式，针对字符串类型，防止乱码。如果数据表中本身是int型，并没有设置编码格式，那这个则没有任何输出。如果数据字段是char，设置了字符编码，那会输出如 COLLATE utf8mb4_unicode_ci

* **{if.java.annotation.id}** 如果当前字段是主键，则输出Java注解 @Id
* **{if.java.annotation.generatedvalue}** 如果当前字段比如是自增属性，则输出Java注解 @GeneratedValue(strategy = IDENTITY)
* **{if.database.table.field.default}** 如果当前数据表的字段有设置默认值，那么输出 default 'xxxx'

##### 代码块 : field-start、field-start

````//field-start```` 跟 ````//field-end```` 中间的为循环输出数据表的所有字段属性。如： 

````
//field-start
private Integer id; 
//field-end
````

##### 代码块 : get-set-method-start 、get-set-method-end

````//get-set-method-start```` 跟 ````//get-set-method-start```` 中间的为循环输出数据表的所有字段的get、set方法