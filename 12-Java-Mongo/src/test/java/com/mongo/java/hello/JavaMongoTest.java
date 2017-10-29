package com.mongo.java.hello;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.BSON;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Sorts.descending;

/**
 * Created by liuburu on 2017/7/6.
 */
public class JavaMongoTest {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    @Before
    public void init() {
        //1.服务器地址 和 端口配置
        ServerAddress serverAddress1 = new ServerAddress("127.0.0.1", 27017);
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        addrs.add(serverAddress1);
        //2.用户名 数据库名称 密码配置
        MongoCredential credential1 = MongoCredential.createScramSha1Credential("system", "admin", "system".toCharArray());
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential1);
        //3.认证获取MongoDB连接
        mongoClient = new MongoClient(addrs, credentials);
        //4.连接到数据库
        mongoDatabase = mongoClient.getDatabase("test");
    }

    @Test
    public  void testMongoConnection(){

    }

    /**
     * 插入一个文档
     */
    @Test
    public void isnertOneDocument() {
        try {
            MongoCollection<Document> collection = mongoDatabase.getCollection("courses");
            System.out.println("集合创建成功==>" + collection);
            //7.在集合中添加文档对象
            Document document = new Document("title", "php").
                    append("description", "php is best language!!!").
                    append("likes", 100).
                    append("by", "hy");
            collection.insertOne(document);
            System.out.println("文档插入成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入多个文档
     */
    @Test
    public void isnertOManyDocument() {
        try {
            MongoCollection<Document> collection = mongoDatabase.getCollection("php");
            System.out.println("集合创建成功==>" + collection);
            //7.在集合中添加文档对象
            List<Document> documents = new ArrayList<Document>();
            for(int i=0;i<10;i++){
                Document document = new Document("title", "php"+i).
                        append("description", "php is best language!!!").
                        append("likes", 100).
                        append("by", "hy");
                documents.add(document);
            }
            collection.insertMany(documents);
            System.out.println("文档插入成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Mongodb的一些基本操作
     */
    @Test
    public void getCollectionCount(){
        MongoCollection<Document> collection = mongoDatabase.getCollection("php");
        Document first = collection.find().first();
        System.out.println("集合个数==>"+collection.count());
        System.out.println("集合第一个元素==>"+first);
        System.out.println("转换成JSON格式==>"+first.toJson());
    }

    /**
     * 使用游标遍历结果
     */
    @Test
    public  void getMongoCursor(){
        MongoCollection<Document> collection = mongoDatabase.getCollection("php");
        /* 1.Document作为条件 2.使用eq作为条件*/
//        Document bson = new Document();
//        bson.append("title","php5");
        collection.find().first();
        MongoCursor<Document> iterator = collection.find(eq("title", "php4")).iterator();
        while(iterator.hasNext()){
            Document next = iterator.next();
            System.out.println(next.toJson());
        }
        iterator.close();

    }

    @Test
    public void testQueryByCondition(){
        MongoCollection<Document> collection = mongoDatabase.getCollection("php");
        /* 1.Document作为条件 */
        Document bson = new Document();
        bson.append("title","php5");
        Document first = collection.find(bson).first();
        System.out.println("Document作为条件==>"+first);
        /*2.使用eq作为条件*/
        Document first1 = collection.find(eq("title", "php4")).first();
        System.out.println("使用eq作为条件==>"+first1);
        /*3.50 < likes <= 80:*/
        Document first2 = collection.find(and(gt("likes", 50), lte("likes", 80))).first();
        System.out.println("50 < likes <= 80==>"+first2);
        /*4.使用回调函数*/
        Block<Document> printBlock = new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println("printBlock==>"+document.toJson());
            }
        };
        collection.find(gt("likes", 90)).forEach(printBlock);

        /*5.条件查询并且排序*/
        System.out.println("条件查询并且排序==>");
        collection.find(exists("message")).sort(descending("title")).forEach(printBlock);

        /*6.属性排除*/
        Document first6 = collection.find().projection(excludeId()).first();
        System.out.println("属性排除==>"+first6);

        /*7.模糊查询*/
        System.out.println("模糊查询结果=>");
        collection.find(regex("title","^php")).forEach(printBlock);

    }


    /**
     * 更新文档对象
     */
    @Test
    public void updateDocument(){
        /*1.更新一条记录*/
        MongoCollection<Document> collection = mongoDatabase.getCollection("php");
        UpdateResult updateResult = collection.updateOne(eq("title", "PHP_0"), new Document("$set", new Document("title", "PHP_00000")));
        System.out.println("更新一条记录==>"+updateResult);
        /*2.更新多条记录 把likes大于100的，继续增+100*/
        UpdateResult updateResult1 = collection.updateMany(gt("likes", 90),
                new Document("$inc", new Document("likes", 10)));
        System.out.println("更新多条记录==>"+updateResult);

        /*3.删除一条记录*/
        DeleteResult deleteResult = collection.deleteOne(eq("title", "PHP_00000"));
        System.out.println("删除一条记录===>"+deleteResult);

        /*4.删除多条记录*/
        DeleteResult deleteResult1 = collection.deleteMany(lte("likes", 100));
        System.out.println("删除多条记录==>"+deleteResult1);
    }


}
