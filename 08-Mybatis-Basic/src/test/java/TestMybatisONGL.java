import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.example.bean.Employ;
import org.mybatis.example.bean.Student;
import org.mybatis.example.dao.ONGLMapper;
import org.mybatis.example.dao.StudentSQLInjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Mybatis的ONGL
 *
 * @author liuburu
 * @create 2017/08/07
 **/
public class TestMybatisONGL {
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
        this.sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void destorySqlsession() {
        this.sqlSession.close();
    }


    @Test
    public void rightQuery() {
        ONGLMapper onglMapper = this.sqlSession.getMapper(ONGLMapper.class);
//        List<Employ> employs1 = onglMapper.selectEmploy1("AR");
//        List<Employ> employs2 = onglMapper.selectEmploy2("AR");
//        List<Employ> employs3 = onglMapper.selectEmploy3("AR");
//        Employ employ = new Employ();
//        employ.setEname("AR");
//        List<Employ> employs4 = onglMapper.selectEmploy4(employ);
//        List<Employ> employs5 = onglMapper.selectEmploy5("AR","SALESMAN");
//        List<Employ> employs5 = onglMapper.selectEmploy5("AR",null);
//        List<Employ> employs6 = onglMapper.selectEmploy6("AR", null);
//        List<Employ> employs6 = onglMapper.selectEmploy7(null);
//        List<Employ> employs6 = onglMapper.selectEmploy8(null);
//        List<Employ> employs6 = onglMapper.selectEmploy9(null);
//        List<Employ> employs6 = onglMapper.selectEmploy10("AR");
        List<Student> students = onglMapper.selectStudent("王");
//        System.out.println(students);

    }


    @Test
    public void test(){
        System.out.println(Employ.class instanceof Serializable);
        System.out.println(new Employ() instanceof Serializable);
        System.out.println(Serializable.class instanceof Object);
        //System.out.println(new Student() instanceof String); //compile time error
        //System.out.println(new Student() instanceof Exception); //compile time error
        System.out.println(new Student() instanceof Object); //compilation and output true
        System.out.println(new Student() instanceof List); //compilation and output false
        System.out.println(new Student() instanceof List<?>); //compilation and output false
       // System.out.println(new Student() instanceof List<String>); //compile time error
        //System.out.println(new Student() instanceof List<Object>); //compile time error
       // System.out.println(new String() instanceof List); //compile time error
       // System.out.println(new String() instanceof List<?>); //compile time error
        System.out.println(null instanceof Object); //compilation and output false

    }

}
