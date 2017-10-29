package com.ylzinfo.esb.client;

import com.ylzinfo.esb.bas.EsbResponse;
 
public class TestDemo {
 
 
public static void main(String[] args) throws Exception 
{
	XMLRequest request = new XMLRequest();		
	request.setGzip(true);
	request.setSvid("queryFs"); //服务名  
	request.setEsbUrl("http://127.0.0.1:8080/YlzinfoESB/esbproxy"); 	
	request.setEsbUserPwd(new String[]{"esb","8E000FDB54B7FD93"}); //ESB访问用户及密码 
    String[] v_param={"name"};//访问参数    	 
    String[] v_value={"张三"};//访问参数值
    request.setParam(v_param);
    request.setParamValue(v_value); 
	EsbResponse res = request.postXmlRequest(); 
	String result = res.getResponseData();
	System.out.println(result); 
		 
} 

 
 
 
}
