package com.jxufe.mongo.gridfs;

import com.jxufe.mongo.gridfs.api.GenericGridFsOperations;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

/**
 * Created by liuburu on 2017/7/8.
 */
public class GenericGridFsTemplate extends GridFsTemplate implements GenericGridFsOperations {
    public GenericGridFsTemplate(MongoDbFactory dbFactory, MongoConverter converter) {
        super(dbFactory, converter);
    }

    public GenericGridFsTemplate(MongoDbFactory dbFactory, MongoConverter converter, String bucket) {
        super(dbFactory, converter, bucket);
    }

    public GridFSDBFile load(String id) {
        GridFSDBFile gridFsdbFile = this.findOne(new Query(Criteria.where("_id").is(id)));
        return gridFsdbFile;
    }

    public void delete(String id) {
        this.delete(new Query(Criteria.where("_id").is(id)));
    }
}