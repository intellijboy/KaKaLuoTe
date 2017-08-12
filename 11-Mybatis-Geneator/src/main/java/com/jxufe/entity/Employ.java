package com.jxufe.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Employ {
    private BigDecimal empno;

    private String ename;

    private String job;

    private BigDecimal mgr;

    private Date hiredate;

    private BigDecimal sal;

    private BigDecimal comm;

    private BigDecimal deptno;

    public BigDecimal getEmpno() {
        return empno;
    }

    public void setEmpno(BigDecimal empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public BigDecimal getMgr() {
        return mgr;
    }

    public void setMgr(BigDecimal mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public BigDecimal getSal() {
        return sal;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }

    public BigDecimal getComm() {
        return comm;
    }

    public void setComm(BigDecimal comm) {
        this.comm = comm;
    }

    public BigDecimal getDeptno() {
        return deptno;
    }

    public void setDeptno(BigDecimal deptno) {
        this.deptno = deptno;
    }
}