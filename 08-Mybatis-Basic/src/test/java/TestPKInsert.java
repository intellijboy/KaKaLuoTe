import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.example.bean.Student;
import org.mybatis.example.dao.EmployMapper;
import org.mybatis.example.dao.StudentMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 插入获取主键
 *
 * @author liuburu
 * @create 2017/08/07
 **/
public class TestPKInsert {
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
        String resource = "org/mybatis/example/mybatis-config-pk.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @After
    public void destorySqlsession(){
        this.sqlSession.close();
    }


    @Test
    public void insertStudent(){
        this.sqlSession = sqlSessionFactory.openSession();
        StudentMapper studentMapper = this.sqlSession.getMapper(StudentMapper.class);
        Student student = new Student();
        student.setName("大圣3");
        student.setAge(18);
        student.setSex(1);
        int i = studentMapper.insertStudent(student);
        this.sqlSession.commit();
    }

}
