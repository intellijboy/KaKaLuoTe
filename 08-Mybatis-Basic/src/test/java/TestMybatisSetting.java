import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mybatis.example.bean.Employ;
import org.mybatis.example.dao.EmployAnotationMapper;
import org.mybatis.example.dao.EmployMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by liuburu on 2017/6/8.
 */
public class TestMybatisSetting {

    /**
     * 非线程安全的，用完之后需要马上关闭,请求或方法作用域
     */
    private SqlSession sqlSession;

    /**
     * sqlSessionFactory的作用域：一旦被创建就应该在应用的运行期间一直存在
     */
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 直接读取配置文件，初始化数据源
     * @throws IOException
     */
    @Before
    public void initSqlSessionFactory() throws IOException {
        System.out.println("initSqlSessionFactory way init sqlSession");
        String resource = "org/mybatis/example/mybatis-config-setting.xml";
        InputStream resourceStream = Resources.getResourceAsStream(resource);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceStream);
    }

    /**
     * 通过属性文件配置数据源信息
     * @throws IOException
     */
//    @Before
    public void initSqlSessionFactory1() throws IOException {
        System.out.println("initSqlSessionFactory1 way init sqlSession");
        String resource = "org/mybatis/example/mybatis-config-setting.xml";
        String jdbcProperties = "jdbc.properties";
        InputStream resourceStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        InputStream jdbcStream = Resources.getResourceAsStream(jdbcProperties);
        Properties properties = new Properties();
        properties.load(jdbcStream);
        //可以在这里直接注入数据源属性
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceStream);
    }

    @After
    public void destorySqlsession(){
       this.sqlSession.close();
    }

    /**
     * 获取Sqlsession对象
     * @throws IOException
     */
    @Test
    public void  testSqlSession1() throws IOException {
        sqlSession = sqlSessionFactory.openSession();
        System.out.println(sqlSession);
    }

    /**
     * 测试查询
     */
    @Test
    public void testSelect(){
        this.sqlSession = sqlSessionFactory.openSession();
        EmployMapper employMapper = this.sqlSession.getMapper(EmployMapper.class);
        Employ employ = employMapper.selectEmploy(7934);
        System.out.println(employ);
    }


    @Test
    public void testMybatisSetting(){
        /**
         * 1.测试列名别名规则
         */

        /**
         * 2.测试驼峰命名规则
         */

        /**
         * 3.测试自增主键规则
         */

    }
}
