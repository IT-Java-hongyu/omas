package com.fosustu.omas.pojo;

public class Symptoms {
    private String symId;

    private String recId;

    private String desc;
    
    

	private String startDegree;

    private String nowDegree;

    public String getSymId() {
        return symId;
    }

    public void setSymId(String symId) {
        this.symId = symId == null ? null : symId.trim();
    }

    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId == null ? null : recId.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getStartDegree() {
        return startDegree;
    }

    public void setStartDegree(String startDegree) {
        this.startDegree = startDegree == null ? null : startDegree.trim();
    }

    public String getNowDegree() {
        return nowDegree;
    }

    public void setNowDegree(String nowDegree) {
        this.nowDegree = nowDegree == null ? null : nowDegree.trim();
    }

	@Override
	public String toString() {
		return "Symptoms [symId=" + symId + ", recId=" + recId + ", desc=" + desc + ", startDegree=" + startDegree
				+ ", nowDegree=" + nowDegree + "]";
	}
    
}