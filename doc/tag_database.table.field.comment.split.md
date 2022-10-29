对表中字段的注释进行截取。  
注释本身因为要写清楚当前字段的用途，所以可能本身是比较长的。比如，如下数据表中，name 这个字段

### 错误示范

````
CREATE TABLE `person` (
  ......
  `name` char(20) DEFAULT NULL COMMENT '用户的姓名也就是他在身份证上的名字',
  ......
)
````

那么在生成后的页面是这样的：  
![image](https://res.zvo.cn/writecode/database.table.field.comment.split.warning.png)
  
姓名这个因为太长被挤破行了，明显不合适，还需要手动去页面修改显示的文字。  
其实这也不说是一个错误的示范，只是还要手动去修改几个页面的显示，造成了时间的浪费，不可取  

### 正确示范

````
CREATE TABLE `person` (
  ......
  `name` char(20) DEFAULT NULL COMMENT '姓名，用户的姓名也就是他在身份证上的名字',
  ......
)
````

生成后的效果：  

![image](https://res.zvo.cn/writecode/database.table.field.comment.split.normal.png)
  
区别只是注释中，在原本的注释前面，加了一个简短的标题，然后用逗号进行了分割。

### 解释说明
这个其实在列表、编辑页面的模板中，用了 {database.table.field.comment.split} 这个标签进行的截取，截取注释中逗号（或句号、分号都行）前面的字符作为标题，来进行显示。