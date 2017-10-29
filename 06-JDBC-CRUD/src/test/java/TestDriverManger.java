import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Create Date: 2017/10/29
 * Description: 测试DriverManager
 *
 * @author kiwipeach [1099501218@qq.com]
 */
public class TestDriverManger {

    @Test
    public void testMulltiDriverManger() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Class.forName("com.mysql.jdbc.Driver");
        Connection orclConnection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL","scott","123456");
        Connection msqlConnection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/jxufe","root","liuburu");
        System.out.println(orclConnection);
        System.out.println(msqlConnection);
    }
}
