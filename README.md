# DBconnectDemo
  该Demo是有关JDBC、Mybatis连接方式讲解
# Mybatis方式讲解
## Mybatis的基本构成
- Mybatis的核心组件
   - SqlSessionFactoryBuilder（构造器）：它会根据配置信息或者代码来生成SqlSessionFactory（工厂接口）。
   - SqlSessionFactory：依靠工厂来生成SqlSessionFactory（会话）。
   - SqlSessionFactory：是一个既可以发送SQL去执行并返回，也可以获取Mapper的接口。
   - SQL Mapper：它是MyBatis新设计的主见，它是一个Java接口和Xml文件（或注解）构成的，需要给出对应的SQL和映射规则。它负责发送SQL去执行，并返回结果。
- 构建SqlSessionFactory（mybatis-config.xml）
   - 每个Mybatis的应用都是以SqlSessionFactory的实例为中心，SqlSessionFactory是一个工厂接口而不是实现类，它的任务是创建SqlSession，类似于一个JDBC的COnnection对象。
   - SqlSessionFactory的实例可以哦谈过SqlSessionFactoryBuilder获得。
   - Mybatis提供两种模式去创建SqlSessionFactory：
      - 一种是XML配置的方式，推荐使用。
         - 使用 XML 方式构建（mybatis-config.xml）
         - 配置一个简易的 XML
         - 数据库连接实例的数据源（DataSource）
         - 决定事物范围和控制方式的事物管理器（TransactionManager）
         - 映射器（SQL Mapper）：引入了一个XML，它的作用是提供SQl和SQL对POJO的映射规则定义。
     - 另一种是代码的方式：不推荐使用
   - SqlSessionFactory的实现类：一个DefaultSqlSessionFactory（Mybatis只使用） 和 SqlSessionManager（未使用）
- 映射器：是由Java接口和XML文件（或注解）共同组成的
   - 作用如下 ：
1. 定义参数类型 
2. 描述缓存 
3. 描述SQL语句 
4. 定义查询结果和POJO的映射关系 
   - XML 方式配置方式实现Mapper
      - XML文件配置方式是Mybatis实现Mapper的首选方式。它由一个Java接口和一个XML文件构成。
      - 第一步、给出Java接口
         - 定义一个方法，这个方式就是Mapper.xml中的id
      - 第二步、给出一个映射XML文件
         - 这个文件是我们在配置文件mybatis-config.xml中配置的，所以Mybatis会读取这个配置文件，生成映射器。
         - 定义一个命名空间的SQL Mapper，这个命名空间和我们定义的接口的全限定名是一致的。
         - 用一个select元素定义一个查询SQL，id和Mapper定义的接口方法一致，parameter是传递SQL的参数类型，resultType是返回的数据类型，在mybatis-config.xml中定义的别名。
 

      
