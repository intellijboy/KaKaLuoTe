package com.jxufe.mongo.gridfs.api;

import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

/**
 * Created by liuburu on 2017/7/8.
 */
public interface CustomGridFsOperations extends GridFsOperations {
    GridFSDBFile load(String var1);

    void delete(String var1);
}