import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

/**
 * 加密规则
 *
 * @author liuburu
 * @create 2017/07/19
 **/
public class SHA1Test {

    @Test
    public void test() {
        /**
         * user:073d4c3ae812935f23cb3f2a71943f49e082a718
         * admin:ce2f6417c7e1d32c1d81a797ee0b499f87c5de06
         * kakaluote:01ebd54bb173e26d0b2be90bc01a09db17c7e7a0
         * liuburu:bb6641a61006fc8ce454f8ead2fbbf459438a9ce
         */
        String hashAlgorithmName = "SHA1";
        Object credentials = "123456";
        Object salt = ByteSource.Util.bytes("user");
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        System.out.println(result);//01ebd54bb173e26d0b2be90bc01a09db17c7e7a0
    }
}
