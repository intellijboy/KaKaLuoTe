package com.mongo.java.hello;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuburu on 2017/7/6.
 */
public class HelloJavaMongo {

    /**
     * 1.获取默认的mongo数据库连接对象
     */
    @Test
    public void mongoGet() {
        try {
            // 连接到 mongodb 服务
//            MongoClient mongoClient = new MongoClient("192.168.56.102", 27017);
            MongoClient mongoClient = new MongoClient("192.168.56.103");
            // 连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
            System.out.println("Connect to database successfully");

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     * 1.1 获取带有凭证的mongo连接对象
     */
    @Test
    public void mongGetCredical() {
        try {
            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
            //ServerAddress()两个参数分别为 服务器地址 和 端口
            ServerAddress serverAddress1 = new ServerAddress("192.168.56.102", 27017);
            ServerAddress serverAddress2 = new ServerAddress("127.0.0.1", 27017);
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();
            addrs.add(serverAddress1);
            addrs.add(serverAddress2);

            //MongoCredential.createScramSha1Credential()三个参数分别为 用户名 数据库名称 密码
            MongoCredential credential1 = MongoCredential.createScramSha1Credential("eva_sso", "sso", "eva_sso".toCharArray());
            MongoCredential credential2 = MongoCredential.createScramSha1Credential("dba", "admin", "dba".toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential1);
            credentials.add(credential2);

            //通过连接认证获取MongoDB连接
            MongoClient mongoClient = new MongoClient(addrs, credentials);

            //连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("admin");
            System.out.println("Connect to database successfully" + mongoDatabase);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }


    /**
     * 2.创建MongoDB数据库
     */
    @Test
    public void creatMongoDB() {
        try {
            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
            //1.服务器地址 和 端口配置
            ServerAddress serverAddress1 = new ServerAddress("lbr.mongo.cn", 27017);
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();
            addrs.add(serverAddress1);
            //2.用户名 数据库名称 密码配置
            MongoCredential credential1 = MongoCredential.createScramSha1Credential("eva_sso", "sso", "eva_sso".toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential1);
            //3.认证获取MongoDB连接
            MongoClient mongoClient = new MongoClient(addrs, credentials);
            //4.连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("sso");
            System.out.println("连接到数据库成功 ==>" + mongoDatabase);
            //5.创建数据库集合
            mongoDatabase.createCollection("school");
            System.out.println("数据库创建成功==>school");
            //6.在数据库中添加集合
            MongoCollection<Document> collection = mongoDatabase.getCollection("courses");
            System.out.println("集合创建成功==>" + collection);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    /**
     *3.向mongodb数据库中插入文档对象
     */
    @Test
    public void createDocument(){
        try {
            //连接到MongoDB服务 如果是远程连接可以替换“localhost”为服务器所在IP地址
            //1.服务器地址 和 端口配置
            ServerAddress serverAddress1 = new ServerAddress("192.168.56.103", 27017);
            List<ServerAddress> addrs = new ArrayList<ServerAddress>();
            addrs.add(serverAddress1);
            //2.用户名 数据库名称 密码配置
            MongoCredential credential1 = MongoCredential.createScramSha1Credential("eva_sso", "sso", "eva_sso".toCharArray());
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(credential1);
            //3.认证获取MongoDB连接
            MongoClient mongoClient = new MongoClient(addrs, credentials);
            //4.连接到数据库
            MongoDatabase mongoDatabase = mongoClient.getDatabase("sso");
            System.out.println("连接到数据库成功 ==>" + mongoDatabase);
            //5.创建数据库集合
//            mongoDatabase.createCollection("school");
//            System.out.println("数据库创建成功==>school");
            //6.在数据库中添加集合
            MongoCollection<Document> collection = mongoDatabase.getCollection("courses");
            System.out.println("集合创建成功==>" + collection);
            //7.在集合中添加文档对象
            Document document = new Document("title", "mysql").
                    append("description", "mysql5.0高级教程").
                    append("likes", 100).
                    append("by", "liuburu");
            collection.insertOne(document);
            System.out.println("文档插入成功");
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

}
