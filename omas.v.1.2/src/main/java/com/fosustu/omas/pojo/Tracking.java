package com.fosustu.omas.pojo;

import java.util.Date;

public class Tracking {
    private String desId;

    private String pId;

    private String recId;

    private Date desDate;

    private String symptom;

    private String describe;

    private Integer status;

    public String getDesId() {
        return desId;
    }

    public void setDesId(String desId) {
        this.desId = desId == null ? null : desId.trim();
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId == null ? null : recId.trim();
    }

    public Date getDesDate() {
        return desDate;
    }

    public void setDesDate(Date desDate) {
        this.desDate = desDate;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom == null ? null : symptom.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}