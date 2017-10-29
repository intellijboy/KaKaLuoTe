import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.example.bean.Employ;
import org.mybatis.example.dao.Cache2Mapper;
import org.mybatis.example.dao.CacheMapper;
import org.mybatis.example.dao.EmployMapper;
import org.mybatis.example.dao.StudentMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>Mybatis缓存测试</p>
 * @author liuburu
 * @create 2017/08/07
 **/
public class TestMybatisCache {
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
        String resource = "org/mybatis/example/mybatis-config-cache2.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    }

    @After
    public void destorySqlsession(){
        this.sqlSession.close();
    }


    /**
     * mybatis一级缓存测试
     */
    @Test
    public void testCache1(){
        this.sqlSession = sqlSessionFactory.openSession();
        CacheMapper cacheMapper = this.sqlSession.getMapper(CacheMapper.class);
        StudentMapper studentMapper = this.sqlSession.getMapper(StudentMapper.class);
        Employ employ1 = cacheMapper.selectEmploy("7369");
        employ1.setEname("卡卡罗特444");
        System.out.println("第一次查询:"+employ1);

        //执行update或者delete操作
        int result_row = studentMapper.updateStudent("f019419b358b45bcb9571262b71fe957", "777");
        System.out.println("result_row="+result_row);

        Employ employ2 = cacheMapper.selectEmploy("7369");
        System.out.println("第二次查询:"+employ2);
        System.out.println("是否为统一个对象?"+(employ1==employ2));
    }


    /**
     * 测试二级缓存
     */
    @Test
    public void testCache2(){
        this.sqlSession = sqlSessionFactory.openSession();
        Cache2Mapper cache2Mapper1 = this.sqlSession.getMapper(Cache2Mapper.class);
        Employ employ1 = cache2Mapper1.selectEmploy("7369");
        System.out.println(employ1);
        this.sqlSession.close();
        this.sqlSession = sqlSessionFactory.openSession();
        Cache2Mapper cache2Mapper2 = this.sqlSession.getMapper(Cache2Mapper.class);
        Employ employ2 = cache2Mapper2.selectEmploy("7369");
        System.out.println(employ2);
        employ2.setEname("篡改XXX");
        System.out.println(employ1==employ2);
    }

    private SqlSession sqlSession1;
    private SqlSession sqlSession2;
    /**
     * 脏读现象
     * 场景分析：
     * 开启第一个sqlsession，获取Mapper1的接口查询员工信息，关闭sqlsession，员工信息被放在二级缓存中；
     * 开启第二个sqlsession，获取Mapper2的接口查询员工信息，修改数据库员工信息，刷新Mapper2的命名空间缓存；
     * 再查询员工信息，
     */
    @Test
    public void testZangDu(){
        //第一个sqlSession:查询员工信息
        this.sqlSession = sqlSessionFactory.openSession();
        Cache2Mapper cache2Mapper1 = this.sqlSession.getMapper(Cache2Mapper.class);
        Employ employ1 = cache2Mapper1.selectEmploy("7369");
        this.sqlSession.close();

        //第二个sqlSession
        this.sqlSession = sqlSessionFactory.openSession();
        CacheMapper cacheMapper = this.sqlSession.getMapper(CacheMapper.class);
        Employ paramEmp = new Employ();
        paramEmp.setEmpno(employ1.getEmpno());
        paramEmp.setEname("KiWiPeach");
        int i = cacheMapper.updateEmploy(paramEmp);
        //提交数据更新
        this.sqlSession.commit();
        this.sqlSession.close();

        //第三个sqlSession
        this.sqlSession = sqlSessionFactory.openSession();
        cache2Mapper1 = this.sqlSession.getMapper(Cache2Mapper.class);
        //这次读取的为数据库缓存中的脏数据
        Employ employ2 = cache2Mapper1.selectEmploy("7369");
        this.sqlSession.close();

        System.out.println(employ1==employ2);
    }

    /**
     * 测试可读写缓存
     * 如果 copyOnWrite="true"，那么第一个查询对象会进入缓存，其他后面的查询就会拷贝成同一个对象进入缓存，
     * 后面的查询结果为同一个对象。
     * 如果 copyOnWrite="true"，那么所以查询对象公用同一个对象缓存，每次查询结果为同一个对象
     */
    @Test
    public void testReadWirteCache(){
        //第一个sqlSession:查询员工信息
        this.sqlSession = sqlSessionFactory.openSession();
        Cache2Mapper cache2Mapper1 = this.sqlSession.getMapper(Cache2Mapper.class);
        Employ employ1 = cache2Mapper1.selectEmploy("7369");
        employ1.setEname("kaka");
        this.sqlSession.close();

        //第二个sqlSession
        this.sqlSession = sqlSessionFactory.openSession();
        Cache2Mapper cache2Mapper2 = this.sqlSession.getMapper(Cache2Mapper.class);
        Employ employ2 = cache2Mapper2.selectEmploy("7369");
        employ2.setEname("kakaluote");
        this.sqlSession.close();
        System.out.println(employ1==employ2);

        //第三个sqlSession
        this.sqlSession = sqlSessionFactory.openSession();
        Cache2Mapper cache2Mapper3 = this.sqlSession.getMapper(Cache2Mapper.class);
        Employ employ3 = cache2Mapper3.selectEmploy("7369");
        employ3.setEname("kakaluote444");
        System.out.println(employ1==employ3);
        this.sqlSession.close();


    }

}
