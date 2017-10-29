import com.ylzinfo.eva.core.web.validate.AjaxPageResponse;
import com.ylzinfo.framework.sys.dao.SyscodeMapper;
import com.ylzinfo.framework.sys.domain.Syscode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ${DESCRIPTION}
 *
 * @author liuburu
 * @create 2017/08/15
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis-test.xml"})
public class ESBLogMapperTest {

    @Autowired
    private SyscodeMapper syscodeMapper;

    @Test
    public void testCodeMapper(){
        AjaxPageResponse page = new AjaxPageResponse();
        page.setPageNumber(1);
        page.setPageSize(5);
        syscodeMapper.query(new Syscode(), page);
        System.out.println(page.getData());
    }


}