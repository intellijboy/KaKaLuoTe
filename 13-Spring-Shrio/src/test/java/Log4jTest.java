import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by liuburu on 2017/7/9.
 */
public class Log4jTest {
    Logger log = Logger.getLogger(Log4jTest.class.getName());

    @Test
    public void test(){
        for(int i=0;i<100;i++){
            log.trace("trace"+i);
            log.debug("Debug"+i);
            log.warn("warn"+i);
            log.info("Info"+i);
            log.error("error"+i);
        }
    }
}
