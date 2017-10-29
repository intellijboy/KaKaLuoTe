package com.ylzinfo.esb.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

/**
 * Copyright ylzinfo Corporation. All rights reserved.
 * @author:  <a href="xmlvrl@163.com">LvRongLin</a>
 * History:  2012-12-11 Created.
 * Version: 3.0
 */

public class HttpClientUtils {

	 private static  MultiThreadedHttpConnectionManager connectionManager;

	 private static  HttpConnectionManagerParams connectionManagerParams;
    
     public final static int MAX_TOTAL_CONNECTIONS = 128; //默认 20
    
     public final static int MAX_ROUTE_CONNECTIONS = 32; //默认 2
      
     public final static int CONNECT_TIMEOUT =540000; //9分钟
                                              
      
     public final static int READ_TIMEOUT = 540000; //9分钟
     static { 
    	 connectionManagerParams = new HttpConnectionManagerParams();
    	 connectionManagerParams.setDefaultMaxConnectionsPerHost(MAX_ROUTE_CONNECTIONS);
         connectionManagerParams.setMaxTotalConnections(MAX_TOTAL_CONNECTIONS); 
         connectionManagerParams.setBooleanParameter("http.protocol.expect-continue", false);
         connectionManagerParams.setParameter("Connection", "close");
         connectionManagerParams.setParameter("http.connection.timeout",CONNECT_TIMEOUT);//连接超时
         connectionManagerParams.setParameter("http.socket.timeout",CONNECT_TIMEOUT);//连接超时
         //connectionManagerParams.setParameter("http.connection-manager.timeout",READ_TIMEOUT);
         connectionManager=new MultiThreadedHttpConnectionManager();
         connectionManager.setParams(connectionManagerParams);
     }

     public static HttpClient getHttpClient() { 
      
    	 return new HttpClient(connectionManager); 
     } 
     
}
