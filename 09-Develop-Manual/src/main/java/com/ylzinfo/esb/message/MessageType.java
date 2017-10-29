package com.ylzinfo.esb.message;

/**
 * 消息类型
 * Copyright ylzinfo Corporation. All rights reserved.
 * @author:    LvRongLin
 * History:  2009-11-15 Created.
 * Version: 1.0
 */

public enum MessageType {
     
	MT_COMMON((byte)48, "普通消息类型"), 
//	MT_SECURITY((byte)49, "加密消息类型"), 
	MT_SIGN_TIMP((byte)50, "数字签名时间戳类型"),
//    MT_SECURITY_SIGN_TIMP((byte)51, "加密数字签名时间戳消息类型"),
    MT_AUTOSTART_POINTSERVER((byte)52, "远程自动启动端服务器消息类型"),
	MT_MULTIESB((byte)53, "省中心端服务器消息类型"),
	MT_CITYESB((byte)54, "地市端服务器消息类型"),
    MT_AUTOSTOP_POINTSERVER((byte)55, "远程自动停止端服务器消息类型"),
    MT_MONITOR_POINTSERVER((byte)56, "监控端服务器运行消息类型"),
    MT_AUTOLOAD_CONFIGDB((byte)57, "远程自动加载端服务器配置信息"),
    MT_AUTOUPDATE_CONFIGDB((byte)58, "远程自动更新端服务器配置信息");
	
	private byte code;
	private String description;
	
	private MessageType(byte code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public byte getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	
	public static MessageType forCode(byte code) {
		MessageType type = MessageType.MT_COMMON;
		if (code == MessageType.MT_COMMON.getCode()) {
			type = MessageType.MT_COMMON;
		}
//		else if(code == MessageType.MT_SECURITY.getCode()) {
//			type = MessageType.MT_SECURITY;
//		} 
		else if (code == MessageType.MT_SIGN_TIMP.getCode()) {
			type = MessageType.MT_SIGN_TIMP;
		}
//		else if(code == MessageType.MT_SECURITY_SIGN_TIMP.getCode()) {
//			type = MessageType.MT_SECURITY_SIGN_TIMP;
//		}
		else if(code == MessageType.MT_AUTOSTART_POINTSERVER.getCode()) {
			type = MessageType.MT_AUTOSTART_POINTSERVER;	
		}
		else if(code == MessageType.MT_AUTOSTOP_POINTSERVER.getCode()) {
            type = MessageType.MT_AUTOSTOP_POINTSERVER;
        }else if(code == MessageType.MT_MONITOR_POINTSERVER.getCode()) {
            type = MessageType.MT_MONITOR_POINTSERVER;
        }
        else if(code == MessageType.MT_AUTOLOAD_CONFIGDB.getCode()) {
            type = MessageType.MT_AUTOLOAD_CONFIGDB;
        }
        else if(code == MessageType.MT_AUTOUPDATE_CONFIGDB.getCode()) {
            type = MessageType.MT_AUTOUPDATE_CONFIGDB;
        }
		else if(code == MessageType.MT_MULTIESB.getCode()){
			type=MessageType.MT_MULTIESB;
		}else if(code == MessageType.MT_CITYESB.getCode()){
			type=MessageType.MT_CITYESB;
		}
		return type;
	}
	
    //正常发送的消息(没有安全证书的消息)
	public static final int COMMON_MESSAGE  = 0; 
    //安全证书--消息加密
    public static final int ENCRYPT_MESSAGE =1;
    //安全证书--数字签名+时间戳
    public static final int SIGN_TIMP_MESSAGE=2;
    //安全证书--消息加密+数字签名+时间戳
    public static final int ENCRYPT_SIGN_TIMP_MESSAGE=3;
    
   
} 



 