import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 生命周期方法测序
 *
 * @author liuburu
 * @create 2017/07/19
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-life.xml"})
public class SpringLifeTest {

    @Test
    public void lifeTest(){

    }
}
