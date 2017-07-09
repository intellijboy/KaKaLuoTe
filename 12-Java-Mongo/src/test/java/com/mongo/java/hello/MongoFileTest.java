package com.mongo.java.hello;

import com.alibaba.fastjson.JSONObject;
import com.jxufe.mongo.vo.Student;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.regex;

/**
 * Created by liuburu on 2017/7/6.
 */
public class MongoFileTest {
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    @Before
    public void init() {
        //1.服务器地址 和 端口配置
        ServerAddress serverAddress1 = new ServerAddress("127.0.0.1", 27017);
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        addrs.add(serverAddress1);

        //2.用户名 数据库名称 密码配置
        MongoCredential credential = MongoCredential.createScramSha1Credential("fileoper", "filespace", "fileoper".toCharArray());
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential);
        //3.认证获取MongoDB连接
        mongoClient = new MongoClient(addrs, credentials);
        //4.连接到数据库
        mongoDatabase = mongoClient.getDatabase("filespace");
    }

    @After
    public void destoy(){
        mongoClient.close();
    }

    @Test
    public void insertFile() throws Exception {
        DB db = mongoClient.getDB("filespace");
        GridFS gridFS = new GridFS(db);
        File file = new File("C:\\Users\\liubu\\Desktop\\mongoDB.rar");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        GridFSInputFile gridFSFile = gridFS.createFile(bufferedInputStream);
        gridFSFile.setFilename("mongodb的源代码");
        DBObject metadata = new BasicDBObject();
        Student student = new Student(1001,"刘卜铷",new Date(),25,1);
        metadata.put("student", JSONObject.toJSONString(student));
        gridFSFile.setMetaData(metadata);
        gridFSFile.save();
        System.out.println("上传文件成功.");
    }



    @Test
    public void fileQuery(){
        DB db = mongoClient.getDB("filespace");
        GridFS gridFS = new GridFS(db);
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.append("filename","mongodb的源代码");
        List<GridFSDBFile> gridFSDBFiles = gridFS.find(basicDBObject);
        for (GridFSDBFile fsdbFile:gridFSDBFiles){
            System.out.println(fsdbFile);
        }

    }


    /**
     * 模糊查询
     */
    @Test
    public void fileQuery1(){
        MongoCollection<Document> collection = mongoDatabase.getCollection("fs.files");
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println("printBlock==>"+document.toJson());
                DB db = mongoClient.getDB("filespace");
                GridFS gridFS = new GridFS(db);
                BasicDBObject basicDBObject = new BasicDBObject();
                basicDBObject.append("filename",document.get("filename"));
                GridFSDBFile fsdbFile = gridFS.findOne(basicDBObject);
                System.out.println(fsdbFile);
            }
        };
        collection.find(regex("filename", "^mongo")).forEach(printBlock);
    }

}
