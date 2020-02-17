package com.fosustu.omas.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class Record {
    private String recId;

    private String pId;

    private String docId;

    private String date;

    private Integer status;
    
    private String descDetail;

    public String getDescDetail() {
		return descDetail;
	}

	public void setDescDetail(String descDetail) {
		this.descDetail = descDetail;
	}
    
    //补充：症状列表
    private List<Symptoms> rSymptomsList = new ArrayList<Symptoms>();
    
    private int sign=0;
    
    public int getSign() {
		return sign;
	}
	public void setSign(int sign) {
		this.sign = sign;
	}
	public String getRecId() {
        return recId;
    }
    public List<Symptoms> getrSymptomsList() {
		return rSymptomsList;
	}

	public void setrSymptomsList(List<Symptoms> rSymptomsList) {
		this.rSymptomsList = rSymptomsList;
	}
    public void setRecId(String recId) {
        this.recId = recId == null ? null : recId.trim();
    }
    @JSONField(name="pId")
    public String getpId() {
        return pId;
    }
    @JSONField(name="pId")
    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId == null ? null : docId.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "Record [recId=" + recId + ", pId=" + pId + ", docId=" + docId + ", date=" + date + ", status=" + status
				+ ", descDetail=" + descDetail + ", rSymptomsList=" + rSymptomsList + ", sign=" + sign + "]";
	}
}