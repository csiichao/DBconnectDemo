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
- 编写一个demo需要步骤
   - 第一步、编写一个配置mybatis-config.xml
   - 第二步、编写一个Mapper.xml
      - 用来配置SQL和接口MAP映射关系
   - 第三步、编写一个POJO
   - 第四步、编写一个Mapper接口
      - 只定义接口，定义的方法是Mapper.xml中定义的id
## Mybatis的生命周期
### SqlSessionFactoryBuilder
- SqlSessionFactoryBuilder 是利用XML 或者java编码获得资源来构建SqlSessionFactory的，通过它可以构建多个SessionFactory
- 它的作用是一个构建器，一旦构建了SqlSessionFactory，他的作用就完结了，就可以回收了。
- 它的生命周期只存在于方法的局部，它的作用就是生成SqlSessionFactory对象。
### SqlSessionFactory
- SqlSessionFactory的作用是SqlSession，是一个会话，相当于JDBC中的Connection对象。
- 每次应用程序访问数据库，就要通过SqlSessionFactory创建SqlSession；SqlSessionFactory应该在Mybatis应用的**整个生命周期**。
- 如果多次创建同一个数据的SqlSessionFactory ，则每次创建SqlSessionFactory会打开多个数据库连接，连接资源会被耗尽，所有采用**单例模式**。
### SqlSession
- 是一个会话，相当于JDBC的一个Connection对象，它的生命周期应该是请求数据库处理事务的过程。
- 是一个线程不安全的对象，多线程时操作数据库需要注意隔离级别，数据库锁等高级特性。
- 每次创建的SqlSession都必须及时关闭它。
### Mapper
- Mapper 是一个接口，而没有实现类，它的作用是发送SQL。
- 它应该在一个SqlSession事务方法之内，是一个方法级别的东西。
- 它就像JDBC中的一条SQL语法的执行，最大范围和SqlSession相同。
- 尽管想一直保存着Mapper，但很难控制，所以尽量在一个SqlSession事务的方法中使用它，然后废弃掉。


      
