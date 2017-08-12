import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.example.bean.Employ;
import org.mybatis.example.bean.Student;
import org.mybatis.example.dao.EmployMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Mybaits别名配置
 *
 * @author liuburu
 * @create 2017/08/06
 **/

public class TestMybatisTypeAlias {

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
        String resource = "org/mybatis/example/mybatis-config-typealias.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @After
    public void destorySqlsession(){
        this.sqlSession.close();
    }




    /**
     * 1.测试列名别名规则
     */

    @Test
    public void testGetEmp(){
        this.sqlSession = sqlSessionFactory.openSession();
        EmployMapper employMapper = this.sqlSession.getMapper(EmployMapper.class);
        Employ employ = employMapper.selectEmploy(7934);
        System.out.println(employ);
    }

    @Test
    public void getStudent(){
        this.sqlSession = sqlSessionFactory.openSession();
        EmployMapper employMapper = this.sqlSession.getMapper(EmployMapper.class);
//        List<Student> students = employMapper.selectStudent(981);
//        System.out.println(students);
    }
    @Test
    public void updateStudent(){
        this.sqlSession = sqlSessionFactory.openSession();
        EmployMapper employMapper = this.sqlSession.getMapper(EmployMapper.class);
        int i = employMapper.updateStudent(981);
        System.out.println(i);
        this.sqlSession.commit();
    }

    /**
     * 3.测试自增主键规则
     */
}
