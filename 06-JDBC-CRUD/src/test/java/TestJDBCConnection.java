import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by liuburu on 2017/6/1.
 */
public class TestJDBCConnection {

    /**
     * 获取数据库连接的方法
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Connection getConnection() throws IOException, ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Properties info = new Properties();
        InputStream inStream = getClass().getResourceAsStream("jdbc.properties");
        info.load(inStream);
        String url = info.getProperty("jdbc.url");
        String driverClass = info.getProperty("jdbc.driverClass");
        //可以通过new创建Driver对象，同时也可以使用反射创建Driver对象
        Driver driver = (Driver) Class.forName(driverClass).newInstance();
        Connection connection = (Connection) driver.connect(url, info);
        System.out.println(connection);
        return connection;
    }

    /**
     * 1.1 直接使用硬编码获取数据库连接
     *
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void testDriverConnection() throws SQLException, IOException {
        Driver driver = new Driver();
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "root";
        String password = "liuburu";
        Properties info = new Properties();
        info.put("user", user);
        info.put("password", password);
        Connection connection = (Connection) driver.connect(url, info);
        System.out.println(connection);
    }

    /**
     * 1.2 通过读取资源配置文件获取数据库连接
     *
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void testDriverConnection2() throws SQLException, IOException {
        Driver driver = new Driver();
        Properties info = new Properties();
        InputStream inStream = getClass().getResourceAsStream("jdbc.properties");
        info.load(inStream);
        Connection connection = (Connection) driver.connect(info.getProperty("jdbc.url"), info);
        System.out.println(connection);
    }

    /**
     * 1.3 通过读取资源配置文件获取数据库连接
     *
     * @throws SQLException
     * @throws IOException
     */
    @Test
    public void testDriverConnection3() throws SQLException, IOException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        Properties info = new Properties();
        InputStream inStream = getClass().getResourceAsStream("jdbc.properties");
        info.load(inStream);
        String url = info.getProperty("jdbc.url");
        String driverClass = info.getProperty("jdbc.driverClass");
        //可以通过new创建Driver对象，同时也可以使用反射创建Driver对象
        Driver driver = (Driver) Class.forName(driverClass).newInstance();
        Connection connection = (Connection) driver.connect(url, info);
        System.out.println(connection);
    }


    /**
     * 2 使用DriverManager类获取数据库连接
     */
    @Test
    public void testDriverManager() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://127.0.0.1:3306/test";
        String user = "root";
        String password = "liuburu";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = (Connection) DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    /**
     * 3.1 使用普通的Statement执行操作
     */
    @Test
    public void testStatement() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sname = "刘卜铷";
        String password = "123456";
        String sql = "select studentid,sname,age from student WHERE sname='" + sname + "' AND password='" + password + "'";
        System.out.println("执行sql:" + sql);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println("编号:" + resultSet.getInt("studentid") +
                    "   姓名:" + resultSet.getString("sname") +
                    "   年龄:" + resultSet.getInt("age"));
        }
        connection.close();
    }

    /**
     * 3.2 SQL注入案例，即使密码错误也能通过验证
     */
    @Test
    public void testStatementSQLInject() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sname = "刘卜铷";
        String password = "xxxxxx' OR '1'='1";//该语句的拼接能使之前的条件限定无效
        String sql = "SELECT studentid,sname,age FROM student WHERE sname='" + sname + "' AND password='" + password + "'";
        System.out.println("==>sql:" + sql);
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println("编号:" + resultSet.getInt("studentid") +
                    "   姓名:" + resultSet.getString("sname") +
                    "   年龄:" + resultSet.getInt("age"));
        }
        connection.close();
    }

    /**
     * 3.3 PrepareStatement预编译sql，能够防止SQL注入
     */
    @Test
    public void testPrepareStatement() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        Connection connection = getConnection();
        String sname = "刘卜铷";
        String password = "123456";//试图拼装sql语句
        //String password = "xxxxxx OR 1=1";//试图拼装sql语句(无效，会把OR当成一个字符串内容)
        //  String sql = "SELECT studentid,sname,age FROM student WHERE sname=? AND password = ?";
        String sql = "SELECT studentid id,sname stuName,password,age FROM student WHERE studentid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, 10086);
        //preparedStatement.setObject(2,password);//试图注入sql语句
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println("编号:" + resultSet.getInt("id") +
                    "   姓名:" + resultSet.getString("stuName") +
                    "   年龄:" + resultSet.getInt("age"));
        }
        connection.close();
    }

    /**
     * \
     * 4.1 添加操作
     */
    @Test
    public void testAdd() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        String studentid = "55555";
        String sname = "王老五";
        String password = "123456";
        String age = "33";
        Connection connection = getConnection();
        String sql = "INSERT INTO student(studentid,sname,password,age) VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, studentid);
        preparedStatement.setString(2, sname);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, age);
        int resultRows = preparedStatement.executeUpdate();
        System.out.println("插入影响结果rows:" + resultRows);
        connection.close();
    }

    /**
     * 4.2 删除操作
     */
    @Test
    public void testRemove() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        String studentid = "55555";
        Connection connection = getConnection();
        String sql = "DELETE FROM student where studentid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, studentid);
        int resultRows = preparedStatement.executeUpdate();
        System.out.println("删除影响结果rows:" + resultRows);
        connection.close();
    }

    /**
     * 4.2 更新操作
     */
    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        String studentid = "12345";
        String sname = "卡卡罗特";
        Connection connection = getConnection();
        String sql = "UPDATE student SET sname=? WHERE studentid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, sname);
        preparedStatement.setString(2, studentid);
        int resultRows = preparedStatement.executeUpdate();
        System.out.println("更新影响结果rows:" + resultRows);
        connection.close();
    }

    /**
     * 5 获取元数据
     */
    @Test
    public void testMetaData() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        Connection connection = getConnection();
        // String sql = "SELECT studentid id,sname name,password,age FROM student";
        String sql = "SELECT s.sname stuName,c.cname couName,sc.score FROM student s,course c,score sc WHERE s.studentid=sc.studentid AND c.courseid=sc.courseid AND sc.score>?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("preparedStatement metadata:" + preparedStatement.getMetaData());

        preparedStatement.setObject(1, 90);
        System.out.println("****************************表中的数据begin*****************************");
        ResultSet rs = preparedStatement.executeQuery();
        System.out.println("preparedStatement metadata:" + rs.getMetaData());
        while (rs.next()) {
            System.out.println(rs.getString("stuName") + "  " + rs.getString("couName") + "   " + rs.getFloat("score"));
        }
        System.out.println("****************************表中的数据end*****************************");
        ResultSetMetaData resultSetMetaData = preparedStatement.getMetaData();
        System.out.println("返回此 ResultSet 对象中的列数:" + resultSetMetaData.getColumnCount());
        System.out.println("获取指定列的名称getColumnName:" + resultSetMetaData.getColumnName(1));
        System.out.println("指定列的建议标题getColumnLabel:" + resultSetMetaData.getColumnLabel(1));
        System.out.println("获取指定列的表明称getTableName:" + resultSetMetaData.getTableName(1));
        System.out.println("获取指定列的表明称getTableName:" + resultSetMetaData.getTableName(2));
        System.out.println("获取指定列的表目录名称getCatalogName:" + resultSetMetaData.getCatalogName(1));
        List<String> columnNames = new ArrayList<String>();
        for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
            columnNames.add(resultSetMetaData.getColumnLabel(i));
        }
        System.out.println("查询列的别名集合:" + columnNames);
        connection.close();
    }


    @Test
    public void testOracleConnection() throws Exception {
        String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
        String user = "leaf_portal";//leaf_basic,leaf_share,leaf_biz
        String password = "leaf_portal";
        java.sql.Connection connection;
        connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

}
