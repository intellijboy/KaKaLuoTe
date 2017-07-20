import org.apache.log4j.Logger;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.CipherService;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

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

    @Test
    public void testBase64() throws UnsupportedEncodingException {
        String password = "123456";
        System.out.println("原始密码:"+password);

        byte[] encode = Base64.encode(password.getBytes());
        System.out.println("加密后:"+Hex.encodeToString(encode));

        byte[] decode = Base64.decode("4d54497a4e445532");
        System.out.println("解密后:"+Hex.encodeToString(decode));
    }
    @Test
    public void testBase64Demo() throws UnsupportedEncodingException {

    }
}
