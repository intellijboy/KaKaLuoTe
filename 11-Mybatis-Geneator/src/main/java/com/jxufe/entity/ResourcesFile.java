package com.jxufe.entity;

import java.util.Date;

public class ResourcesFile {
    private String resourceid;

    private String userid;

    private String resourcestate;

    private String title;

    private Date sendtime;

    private Date deadline;

    private String resourcescope;

    private String resourcedata;

    public String getResourceid() {
        return resourceid;
    }

    public void setResourceid(String resourceid) {
        this.resourceid = resourceid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getResourcestate() {
        return resourcestate;
    }

    public void setResourcestate(String resourcestate) {
        this.resourcestate = resourcestate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getResourcescope() {
        return resourcescope;
    }

    public void setResourcescope(String resourcescope) {
        this.resourcescope = resourcescope;
    }

    public String getResourcedata() {
        return resourcedata;
    }

    public void setResourcedata(String resourcedata) {
        this.resourcedata = resourcedata;
    }
}