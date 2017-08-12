import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.example.bean.Student;
import org.mybatis.example.dao.StudentMapper;
import org.mybatis.example.dao.StudentSQLInjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * SQL注入测试
 *
 * @author liuburu
 * @create 2017/08/07
 **/
public class TestSQLInject {
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
        String resource = "mybatis-sql-inject.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @After
    public void destorySqlsession(){
        this.sqlSession.close();
    }


    @Test
    public void rightQuery(){
        this.sqlSession = sqlSessionFactory.openSession();
        StudentSQLInjectMapper studentSQLInjectMapper = this.sqlSession.getMapper(StudentSQLInjectMapper.class);
        System.out.println(studentSQLInjectMapper);
        Student student = new Student();
        student.setName("铷973");
        student.setPassword("liuburu");
        student.setAge(25);
        Student student1 = studentSQLInjectMapper.rightLoginQuery(student);
        System.out.println(student1);
    }

    @Test
    public void wrongQuery(){
        this.sqlSession = sqlSessionFactory.openSession();
        StudentSQLInjectMapper studentSQLInjectMapper = this.sqlSession.getMapper(StudentSQLInjectMapper.class);
        System.out.println(studentSQLInjectMapper);
        Student student = new Student();
        student.setName("铷973");
        student.setPassword("'88888' or (name='铷973' and 1 = 1 )");
        Student student1 = studentSQLInjectMapper.wrongLoginQuery(student);
        System.out.println(student1);
    }

}
