数据源

## 自带Mysql数据源
当前内置了 Mysql 数据源，可通过如下代码进行创建

````
String host = "local.mysql.leimingyun.com";	//主机，可以填写域名或ip
int port = 3306;			//端口号
String databaseName = "wangmarket"; //数据表的名字
String username = "root"; 	//数据库登录用户名
String password = "111111";	//数据库登录密码

//创建 Mysql 数据源
DataSourceInterface dataSource = new Mysql(host, port, databaseName, username, password);
````

## 其他数据源的扩展
如果您使用的时 Oracle、SqlServer 等数据源，可以进行相关数据源的扩展。当然，扩展也非常简单，您完全可以参考 [datasource-mysql](/datasource-mysql) 来进行创建即可。  
其实就是一个实现了 [com.xnx3.writecode.interfaces.DataSourceInterface](/core/src/main/java/com/xnx3/writecode/interfaces/DataSourceInterface.java) 接口的Java类而已。 [可以完全参考mysql的这个实现来](/datasource-mysql/src/main/java/com/xnx3/writecode/datasource/Mysql.java)  
如果您有扩展其他数据源，欢迎提交pull合并过来，一起丰富本项目。