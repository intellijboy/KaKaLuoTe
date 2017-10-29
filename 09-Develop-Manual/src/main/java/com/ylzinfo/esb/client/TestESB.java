package com.ylzinfo.esb.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ylzinfo.esb.bas.EsbResponse;

public class TestESB {
public static void main(String[] args) throws java.text.ParseException { 
		
	  	XMLRequest xmlReq=new XMLRequest();
		ArrayList paramList=new ArrayList();
		ArrayList totalInfo=new ArrayList();
		totalInfo.add("totalInfo");  //���ܼ�¼
		Map<String,String> totalMap=new HashMap<String,String>();
		totalMap.put("hblx" , "01");//��������             
		totalMap.put("dqdm" ,  "440200");//�������
		totalMap.put("yhdm" ,  "01023930" );//���д���
		totalMap.put("zmnypc" , "20130401");//��Ŀ������� 
		totalMap.put("hzrs" ,  "2000" );//��������
		float hzje=0f;
		ArrayList rdInfo=new ArrayList(); //��ϸ��¼
		rdInfo.add("rdInfo");
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement ps = null;
		long beginTime =System.currentTimeMillis();
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn=DriverManager.getConnection("jdbc:oracle:thin:@10.10.30.5:1522:LYYB","yb","yb");		
		ps = conn.prepareStatement("Select t.sfzhao sfzh,i.cardno sbkh,i.dwid00 yhzh,t.sngzze hbje,t.id0000 xtgzh From tb_grdak0 t,ic_card i Where i.id0000= t.sfzhao and rownum <10");
		rs=ps.executeQuery();
		int i=1;
		while(rs.next()){
			Map<String,String> map=new HashMap<String,String>();
			map.put("sfzh" , rs.getString("sfzh"));//�շ���Ŀid sfxmid              
		    map.put("sbkh" , rs.getString("sbkh"));
			map.put("hbje" , String.valueOf(rs.getFloat("hbje")));//�շ���Ŀ��� sfxmmc  
			map.put("xtgzh" , rs.getString("xtgzh"));//�շ����� sfsl 
			map.put("yhzh" ,  rs.getString("yhzh"));//�����˺�
			rdInfo.add(map);
			hzje+=rs.getFloat("hbje");
			if(i%2000==0){
				totalMap.put("hzje" , String.valueOf(hzje));//���� ���
				totalInfo.add(totalMap);
			    paramList.add(totalInfo);
				paramList.add(rdInfo);
				xmlReq.setParamList(paramList);
				xmlReq.setEsbUserPwd(new String[]{"esb","8E000FDB54B7FD93"});
				xmlReq.setSvid("batchInsert");
				xmlReq.setParam(new String[]{"yhjkm"});
			    xmlReq.setParamValue(new String[]{"4302121122000001"});
				xmlReq.setEsbUrl("http://localhost:8080/YlzinfoESB/esbproxy");
				long time =System.currentTimeMillis();
				EsbResponse esbRes=xmlReq.postXmlRequest();
				System.out.println(esbRes.getResponseData());
				System.out.println("ÿ2000���ύ:"+(System.currentTimeMillis()-time)/1000.0);
				paramList=new ArrayList();
				rdInfo=new ArrayList();
				rdInfo.add("rdInfo");
				hzje=0f;
				totalInfo=new ArrayList();
				totalInfo.add("totalInfo");  //���ܼ�¼
			}
			i++;
		}		
		}catch(Exception e){
			e.printStackTrace();
		}
		 finally{
			  try 
			   {
				if (rs != null) 
						rs.close();
				if (ps != null) 
					    ps.close();
			    if(conn!=null)
			    	    conn.close();
			   }
			    catch (SQLException e) {}  //����
		}
		    paramList.add(rdInfo);
			totalMap.put("hzrs" ,  "100");//��������
			totalMap.put("hzje" ,  String.valueOf(hzje));//��Ŀ������� 
			totalInfo.add(totalMap);
		    paramList.add(totalInfo);
			xmlReq.setParamList(paramList);
			
			xmlReq.setEsbUserPwd(new String[]{"esb","8E000FDB54B7FD93"});
			xmlReq.setSvid("batchInsert");
			xmlReq.setParam(new String[]{"yhjkm"});
		    xmlReq.setParamValue(new String[]{"4302121122000001"});
			xmlReq.setEsbUrl("http://localhost:8080/YlzinfoESB/esbproxy");
			long time =System.currentTimeMillis();
			EsbResponse esbRes=xmlReq.postXmlRequest();
			System.out.println(esbRes.getResponseData());
			System.out.println((System.currentTimeMillis()-time)/1000.0);	
			System.out.println("�ܺ�ʱ��"+(System.currentTimeMillis()-beginTime)/1000f);	

  }
}
