package com.jxufe.mongo.gridfs;/**
 * Created by liuburu on 2017/7/10.
 */

import com.jxufe.mongo.gridfs.api.CustomMongoOperations;
import com.mongodb.Mongo;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;

/**
 * CustomMongoOperations的实现类
 *
 * @author liuburu
 * @create 2017/07/10
 **/
public class CustomMongoTemplete extends MongoTemplate implements CustomMongoOperations {

    public CustomMongoTemplete(Mongo mongo, String databaseName) {
        super(mongo, databaseName);
    }

    public CustomMongoTemplete(Mongo mongo, String databaseName, UserCredentials userCredentials) {
        super(mongo, databaseName, userCredentials);
    }

    public CustomMongoTemplete(MongoDbFactory mongoDbFactory) {
        super(mongoDbFactory);
    }

    public CustomMongoTemplete(MongoDbFactory mongoDbFactory, MongoConverter mongoConverter) {
        super(mongoDbFactory, mongoConverter);
    }

}
