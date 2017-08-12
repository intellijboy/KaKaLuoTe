import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.example.bean.Employ;
import org.mybatis.example.dao.EmployAnotationMapper;
import org.mybatis.example.dao.EmployMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liuburu on 2017/6/8.
 */
public class TestMybatis {

    /**
     * 非线程安全的，用完之后需要马上关闭,请求或方法作用域
     */
    private SqlSession sqlSession;

    /**
     * sqlSessionFactory的作用域：一旦被创建就应该在应用的运行期间一直存在
     */
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void initSqlSessionFactory() throws IOException {
        String resource = "org/mybatis/example/mybatis-config-basic.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
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
     * 1.通过命名空间+selectId的方式调用方法
     * @throws IOException
     */
    @Test
    public void testSelect1() throws IOException {
        this.sqlSession = sqlSessionFactory.openSession();
        Employ employ  = (Employ) sqlSession.selectOne("org.mybatis.example.dao.EmployMapper.selectEmploy", 7934);
        System.out.println(employ);
    }

    /**
     * 2.通过接口的方式调用
     */
    @Test
    public void testSelect2(){
        this.sqlSession = sqlSessionFactory.openSession();
        EmployMapper employMapper = this.sqlSession.getMapper(EmployMapper.class);
        Employ employ = employMapper.selectEmploy(7934);
        System.out.println(employ);
    }

    /**
     * 3.使用 Java 注解
     */
    @Test
    public void testSelect3(){
        this.sqlSession = sqlSessionFactory.openSession();
        EmployAnotationMapper anotationMapper = this.sqlSession.getMapper(EmployAnotationMapper.class);
        Employ employ = anotationMapper.selectEmploy(7934);
        System.out.println(employ);
    }
}
