import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * Access数据库访问
 *
 * @author liuburu
 * @create 2017/08/29
 **/
public class ConnectAccessTest {
    /**
     * 初学者请注意：
     * 1:先建立一个access文件a1.mdb,并放在D:/下;
     * 2:在数据库文件a1.mdb中建立一个表Table1；
     * 3：为Table1添加一列，并插入至少一条记录；
     * 4：本文是一个完整的类，直接拿去运行就可以。
     */
    public static void main(String args[]) throws Exception {
        ConnectAccessTest ca = new ConnectAccessTest();
        ca.ConnectAccessFile();
        //ca.ConnectAccessDataSource();
    }

    public void ConnectAccessFile() throws Exception {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        /**
         * 直接连接access文件。
         */
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=H:/mdb/student.mdb";
        Connection con = DriverManager.getConnection(url);
        System.out.println(con);

//        String dbur1 = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=H:\\mdb\\iCCard.mdb";
//        Connection conn = DriverManager.getConnection(dbur1, "", "168168");
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery("select * from Table1");
//        while (rs.next()) {
//            System.out.println(rs.getString(1));
//        }
//        rs.close();
//        stmt.close();
//        conn.close();
    }

    public void ConnectAccessDataSource() throws Exception {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        /**
         * 采用ODBC连接方式 如何建立ODBC连接？
         * 答：在windows下，【开始】->【控制面板】->【性能和维护】->【管理工具】->【数据源】，在数据源这里添加一个指向a1.mdb文件的数据源。
         * 比如创建名字为dataS1
         */
        String dbur1 = "jdbc:odbc:dataS1";// 此为ODBC连接方式
        Connection conn = DriverManager.getConnection(dbur1, "username", "password");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from Table1");
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        rs.close();
        stmt.close();
        conn.close();
    }


    @Test
    public void testConenct1() throws Exception {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Properties prop = new Properties();
        prop.put("charSet", "gb2312"); // 这里是解决中文乱码
        String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=H:/mdb/student.mdb";
        Connection con = DriverManager.getConnection(url, prop);
        Statement st = con.createStatement();
        String sql = "select * from basic";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.println(
                    rs.getString(1) + "    "
                            + rs.getString(2) + "      "
                            + rs.getString(3) + "      "
                            + rs.getString(4) + "      "
                            + rs.getString(5) + "      "
                            + rs.getString(6) + "      "
                            + rs.getString(7) + "      "
            );
        }
        System.out.println(con);
        rs.close();
        st.close();
        con.close();
    }

    @Test
    public void testConenct2() throws Exception {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Properties prop = new Properties();
        prop.put("charSet", "gb2312"); // 这里是解决中文乱码
        prop.put("user", "168168");
        prop.put("password", "168168");
        String url = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb)};DBQ=H:/mdb/iCCard.mdb";
        Connection con = DriverManager.getConnection(url, prop);
        Statement st = con.createStatement();
        String sql = "select * from t_b_Group";
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            System.out.println(
                    rs.getString(1) + "    "
                            + rs.getString(2) + "      "
                         /*   + rs.getString(3) + "      "*/
                           /* + rs.getString(4) + "      "*/
                          /*  + rs.getString(5) + "      "
                            + rs.getString(6) + "      "
                            + rs.getString(7) + "      "*/
            );
        }
        System.out.println(con);
        rs.close();
        st.close();
        con.close();
    }
}
