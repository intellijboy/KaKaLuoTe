package com.mongo.java.hello;

import com.jxufe.mongo.gridfs.CustomGridFsTemplate;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

/**
 * Created by liuburu on 2017/7/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-fs.xml"})
public class MongoFileUtilTest {

    @Autowired
    CustomGridFsTemplate gridFsTemplate;

    @Test
    public void test() {
        System.out.println("MongoDB==>" + gridFsTemplate);
    }


    @Test
    public void testGridFsQuery() throws IOException {
        GridFSDBFile fsdbFile = gridFsTemplate.load("5961819176e3d6413cbdf55f");
        System.out.println("fsdbFile==>" + fsdbFile.getLength());
        InputStream inputStream = new FileInputStream(new File("C:\\\\Users\\\\liubu\\\\Pictures\\\\流程图图标.jpg"));
        System.out.println("size="+inputStream.available());
//        List<GridFSDBFile> id = gridFsTemplate.find(new Query(Criteria.where("_id").is("596058f376e3d61abc8d24c4")));
//        System.out.println(" List<GridFSDBFile> ==>" + id);
    }

    @Test
    public void testGridStore() throws FileNotFoundException {
        File file = new File("C:\\Users\\liubu\\Desktop\\mongoDB.rar");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        GridFSFile fsFile = gridFsTemplate.store(bufferedInputStream, "测试文件");
        System.out.println("保存结果==>" + fsFile);
    }

}
