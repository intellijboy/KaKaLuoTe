package com.jxufe.dto;

import java.util.List;

/**
 * Created by liuburu on 2017/7/7.
 */
public class UEditorListDTO {
    private String state;
    private List<String> list; //{"url": "upload/1.jpg"}
    private int start;
    private int total;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
