package com.jxufe.logback;


import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.mongodb.*;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class MongoDBSpringAppenderBase<E> extends UnsynchronizedAppenderBase<E> {
    /*基本参数*/
    private String host;//主机
    private int port;//端口
    private String userName;//用户名
    private String password;//密码
    private String source;//认证库
    private String databaseName;//操作库
    private String collectionName;//操作文档对象
    private  MongoClient mongoClient;//mongo客户端
    private MongoDbFactory mongoDbFactory;//mongo工厂
    private MongoTemplate mongoTemplate = null;//mongo模板

    /*拓展参数:参考http://www.cnblogs.com/lykbk/archive/2013/05/03/dehgrt55556565.html*/
    private WriteConcern writeConcern = WriteConcern.ACKNOWLEDGED;
    private int minConnectionsPerHost;
    private int maxConnectionsPerHost = 100;
    private int threadsAllowedToBlockForConnectionMultiplier = 5;
    private int serverSelectionTimeout = 1000 * 10;
    private int maxWaitTime = 1000 * 60 * 2;//被阻塞线程从连接池获取连接的最长等待时间
    private int maxConnectionIdleTime;
    private int maxConnectionLifeTime;
    private int connectTimeout = 1000 * 10;//套接字连接时的超时时间（ms）
    private int socketTimeout = 0;//套接字超时时间默以为0（无穷）
    private boolean socketKeepAlive = false;
    private boolean sslEnabled = false;
    private boolean sslInvalidHostNameAllowed = false;
    private boolean alwaysUseMBeans = false;
    private int heartbeatFrequency = 10000;
    private int minHeartbeatFrequency = 500;
    private int heartbeatConnectTimeout = 20000;
    private int heartbeatSocketTimeout = 20000;
    private int localThreshold = 15;



    public MongoDBSpringAppenderBase() {
    }

    protected void initMongoTemplete() {
        ServerAddress serverAddress = new ServerAddress(host, port);
        List<ServerAddress> seeds = new ArrayList<>();
        seeds.add(serverAddress);
        List<MongoCredential> credentialsList = new ArrayList<>();
        MongoCredential credential = MongoCredential.createScramSha1Credential(userName, source, password.toCharArray());
        credentialsList.add(credential);
        this.mongoClient = new MongoClient(seeds, credentialsList, buildOptions());
        this.mongoDbFactory = new SimpleMongoDbFactory(mongoClient,databaseName);
        this.mongoTemplate = new MongoTemplate(mongoDbFactory);
    }

    private MongoClientOptions buildOptions() {
        /**
         * 参照：
         * <mongo:client-options connections-per-host="8" threads-allowed-to-block-for-connection-multiplier="4"
         * connect-timeout="1000"
         * max-wait-time="1500" socket-keep-alive="true" socket-timeout="1500"
         * write-concern="NORMAL"/>
         */
        MongoClientOptions options = new MongoClientOptions.Builder()
                .connectionsPerHost(maxConnectionsPerHost)
                .threadsAllowedToBlockForConnectionMultiplier(threadsAllowedToBlockForConnectionMultiplier)
                .connectTimeout(connectTimeout)
                .maxWaitTime(maxWaitTime)
                .socketKeepAlive(socketKeepAlive)
                .writeConcern(writeConcern)
                .build();
        return options;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public void start() {
        IllegalStateException i;
        initMongoTemplete();
        if (this.mongoTemplate == null) {
            i = new IllegalStateException("name为" + this.getName() + "的MongoDBSpringAppenderBase 没有定义mongoTemplate");
            i.printStackTrace();
            this.addError("请设置一个不是空的MongoTemplate");
        } else if (this.collectionName == null) {
            i = new IllegalStateException("name为" + this.getName() + "的MongoDBSpringAppenderBase 没有定义collectionName");
            i.printStackTrace();
            this.addError("请设置一个不是空的collectionName");
        } else {
            super.start();
        }
    }

    @Override
    protected void append(E event) {
        this.mongoTemplate.insert(this.toMongoDocument(event), this.collectionName);
    }

    @Override
    public void stop() {
        System.out.println("关闭资源");
        this.mongoClient.close();
        super.stop();
    }

    protected abstract BasicDBObject toMongoDocument(E var1);

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        System.out.println("Set函数");
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public WriteConcern getWriteConcern() {
        return writeConcern;
    }

    public void setWriteConcern(WriteConcern writeConcern) {
        this.writeConcern = writeConcern;
    }

    public int getMinConnectionsPerHost() {
        return minConnectionsPerHost;
    }

    public void setMinConnectionsPerHost(int minConnectionsPerHost) {
        this.minConnectionsPerHost = minConnectionsPerHost;
    }

    public int getMaxConnectionsPerHost() {
        return maxConnectionsPerHost;
    }

    public void setMaxConnectionsPerHost(int maxConnectionsPerHost) {
        this.maxConnectionsPerHost = maxConnectionsPerHost;
    }

    public int getThreadsAllowedToBlockForConnectionMultiplier() {
        return threadsAllowedToBlockForConnectionMultiplier;
    }

    public void setThreadsAllowedToBlockForConnectionMultiplier(int threadsAllowedToBlockForConnectionMultiplier) {
        this.threadsAllowedToBlockForConnectionMultiplier = threadsAllowedToBlockForConnectionMultiplier;
    }

    public int getServerSelectionTimeout() {
        return serverSelectionTimeout;
    }

    public void setServerSelectionTimeout(int serverSelectionTimeout) {
        this.serverSelectionTimeout = serverSelectionTimeout;
    }

    public int getMaxWaitTime() {
        return maxWaitTime;
    }

    public void setMaxWaitTime(int maxWaitTime) {
        this.maxWaitTime = maxWaitTime;
    }

    public int getMaxConnectionIdleTime() {
        return maxConnectionIdleTime;
    }

    public void setMaxConnectionIdleTime(int maxConnectionIdleTime) {
        this.maxConnectionIdleTime = maxConnectionIdleTime;
    }

    public int getMaxConnectionLifeTime() {
        return maxConnectionLifeTime;
    }

    public void setMaxConnectionLifeTime(int maxConnectionLifeTime) {
        this.maxConnectionLifeTime = maxConnectionLifeTime;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public boolean isSocketKeepAlive() {
        return socketKeepAlive;
    }

    public void setSocketKeepAlive(boolean socketKeepAlive) {
        this.socketKeepAlive = socketKeepAlive;
    }

    public boolean isSslEnabled() {
        return sslEnabled;
    }

    public void setSslEnabled(boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
    }

    public boolean isSslInvalidHostNameAllowed() {
        return sslInvalidHostNameAllowed;
    }

    public void setSslInvalidHostNameAllowed(boolean sslInvalidHostNameAllowed) {
        this.sslInvalidHostNameAllowed = sslInvalidHostNameAllowed;
    }

    public boolean isAlwaysUseMBeans() {
        return alwaysUseMBeans;
    }

    public void setAlwaysUseMBeans(boolean alwaysUseMBeans) {
        this.alwaysUseMBeans = alwaysUseMBeans;
    }

    public int getHeartbeatFrequency() {
        return heartbeatFrequency;
    }

    public void setHeartbeatFrequency(int heartbeatFrequency) {
        this.heartbeatFrequency = heartbeatFrequency;
    }

    public int getMinHeartbeatFrequency() {
        return minHeartbeatFrequency;
    }

    public void setMinHeartbeatFrequency(int minHeartbeatFrequency) {
        this.minHeartbeatFrequency = minHeartbeatFrequency;
    }

    public int getHeartbeatConnectTimeout() {
        return heartbeatConnectTimeout;
    }

    public void setHeartbeatConnectTimeout(int heartbeatConnectTimeout) {
        this.heartbeatConnectTimeout = heartbeatConnectTimeout;
    }

    public int getHeartbeatSocketTimeout() {
        return heartbeatSocketTimeout;
    }

    public void setHeartbeatSocketTimeout(int heartbeatSocketTimeout) {
        this.heartbeatSocketTimeout = heartbeatSocketTimeout;
    }

    public int getLocalThreshold() {
        return localThreshold;
    }

    public void setLocalThreshold(int localThreshold) {
        this.localThreshold = localThreshold;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public String getCollectionName() {
        return collectionName;
    }


    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
