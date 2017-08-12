import com.jxufe.util.JDBCUtil;
import oracle.jdbc.proxy.annotation.Pre;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * Created by liuburu on 2017/6/6.
 */
public class TestMyUtil {

    @Test
    public void testQuery() {
        String sql = "select stuId id,name,age,sex,motto from student";
        List<Student> students = JDBCUtil.queryList(Student.class, sql);
        for (Student student : students) {
            System.out.println(student);
        }
    }

    @Test
    public void testInsert() {
        String sql = "insert into student(name,age,sex,motto) values(?,?,?,?)";
        Connection connection = JDBCUtil.getConnection();
        int insertRow = JDBCUtil.update(connection, sql, "liuburu", 24, 1, "爱拼才会赢");
        System.out.println(insertRow > 0 ? "插入成功!" : "插入失败");
    }

    @Test
    public void testUpdate() {
        String sql = "update student set name = ? where stuId = ?";
        Connection connection = JDBCUtil.getConnection();
        int insertRow = JDBCUtil.update(connection, sql, "卡卡罗特444", 1008);
        System.out.println(insertRow > 0 ? "更新成功!" : "更新失败");
    }

    @Test
    public void testRemove() {
        String sql = "delete from student where stuId = ?";
        Connection connection = JDBCUtil.getConnection();
        int insertRow = JDBCUtil.update(connection, sql, 1008);
        System.out.println(insertRow > 0 ? "删除成功!" : "删除失败");
    }

    /**
     * 获取统计值或者字段值
     */
    @Test
    public void testGetValue() {
        String sql = "select count(1) from student where age > ?";
        //String sql ="select age from student where stuId = 1001";
        Object result = JDBCUtil.getValue(sql, 20);
        System.out.println("大于二十岁的人有?   " + result + "个");
        // System.out.println("ID为1001的年龄为？    " + result + "");
    }

    /**
     * 插入同时获取主键值
     */
    @Test
    public void testGetPrimaryKey() {
        String sql = "insert into student(name,age,sex,motto) values(?,?,?,?)";
        Connection connection = JDBCUtil.getConnection();
        Object keyValue = JDBCUtil.insert(connection, sql, "liuburu", 24, 1, "爱拼才会赢");
        System.out.println("返回主键值:" + keyValue);
    }


    @Test
    public void testTransaction() {
        String sql1 = "insert into student(name,age,sex,motto) values(?,?,?,?)";
        String sql2 = "insert into student(name,age,sex,motto) values(?,?,?,?)";
        Connection connection = JDBCUtil.getConnection();
        JDBCUtil.beginTransaction(connection);
        int resultRow1 = JDBCUtil.update(connection, sql1, "xiaoming", 24, 1, "爱拼才会赢");
        int resultRow2 = JDBCUtil.update(connection, sql2, "xiaohong", 20, 0, "七分靠打拼");
        System.out.println("insertRow1:" + resultRow1);
        System.out.println("insertRow2:" + resultRow2);
        JDBCUtil.rollback(connection);//回滚事务
        /// JDBCUtil.commit(connection);//提交事务
    }


    private static Logger log = LoggerFactory.getLogger(TestMyUtil.class);

    /**
     * 测试logback日志
     */
    @Test
    public void testLogBack() {
        for (int i = 0; i < 2; i++) {
            log.trace("======trace" + i);
            log.debug("======debug" + i);
            log.info("======info" + i);
            log.warn("======warn" + i);
            log.error("======error" + i);
        }
    }

    /**
     * 批量插入数据操作
     * @throws SQLException
     */
    @Test
    public void batchInsert() throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        String sql = "insert into student(stuno,name,birthday,age,sex) values(?,?,?,?,?)";
        JDBCUtil.beginTransaction(connection);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < 100000; i++) {
                preparedStatement.setObject(1, i);
                preparedStatement.setObject(2, "铷" + i);
                preparedStatement.setObject(3, new Date(System.currentTimeMillis()));
                preparedStatement.setObject(4, 25);
                preparedStatement.setObject(5, 1);
                preparedStatement.addBatch();
                if (i % 100 == 0) {
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }
            preparedStatement.executeBatch();
            preparedStatement.clearBatch();
            JDBCUtil.commit(connection);
        } catch (SQLException e) {
           JDBCUtil.rollback(connection);
        }
        connection.close();
    }

}
