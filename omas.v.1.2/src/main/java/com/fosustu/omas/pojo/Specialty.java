package com.fosustu.omas.pojo;

public class Specialty {
    private String speId;

    private String docId;

    private String describe;

    public String getSpeId() {
        return speId;
    }

    public void setSpeId(String speId) {
        this.speId = speId == null ? null : speId.trim();
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId == null ? null : docId.trim();
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }
}