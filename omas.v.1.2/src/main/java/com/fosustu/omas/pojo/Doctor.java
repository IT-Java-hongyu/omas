package com.fosustu.omas.pojo;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Date;


import com.fasterxml.jackson.annotation.JsonFormat;

public class Doctor {
    private String docId;

    private String docName;

    private Integer sex;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private String birthday;


    public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

   

	private String phone;

    private String synopsis;

    private String tileId;
    private String tileName;

    private String apartId;
    private String apartName;

    private String score;

    private String scoreTimes;
     
    
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private List<Date> dateList = new ArrayList<Date>();
    

    public List<Date> getDateList() {
		return dateList;
	}

	public void setDateList(List<Date> dateList) {
		this.dateList = dateList;
	}

	public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId == null ? null : docId.trim();
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName == null ? null : docName.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis == null ? null : synopsis.trim();
    }

    public String getTileId() {
        return tileId;
    }

    public void setTileId(String tileId) {
        this.tileId = tileId == null ? null : tileId.trim();
    }

    public String getApartId() {
        return apartId;
    }

    public void setApartId(String apartId) {
        this.apartId = apartId == null ? null : apartId.trim();
    }

	public String getTileName() {
		return tileName;
	}

	public void setTileName(String tileName) {
		this.tileName = tileName;
	}

	public String getApartName() {
		return apartName;
	}

	public void setApartName(String apartName) {
		this.apartName = apartName;
	}

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getScoreTimes() {
        return scoreTimes;
    }

    public void setScoreTimes(String scoreTimes) {
        this.scoreTimes = scoreTimes;
    }
}