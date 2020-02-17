package com.fosustu.omas.pojo;

import java.util.List;

public class Prescription {
    private String preId;
    private String pid;
    private String date;

    private Double fee;

    private String listId;

    private String recId;
    
    private String pharmacy;
    
    private String ZengduanResult;
  
	private String patientname;
	private String patientSex;
	private String patientAge;
	private String patientBirthday;
	
	private List<ChufanMedicineList> chufanMedicineList;

	
	
	
    public String getZengduanResult() {
		return ZengduanResult;
	}

	public void setZengduanResult(String zengduanResult) {
		ZengduanResult = zengduanResult;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}

	public String getPatientBirthday() {
		return patientBirthday;
	}

	public void setPatientBirthday(String patientBirthday) {
		this.patientBirthday = patientBirthday;
	}

	public List<ChufanMedicineList> getChufanMedicineList() {
		return chufanMedicineList;
	}

	public void setChufanMedicineList(List<ChufanMedicineList> chufanMedicineList) {
		this.chufanMedicineList = chufanMedicineList;
	}

	public String getPharmacy() {
		return pharmacy;
	}

	public void setPharmacy(String pharmacy) {
		this.pharmacy = pharmacy;
	}

	public String getPreId() {
        return preId;
    }

    public void setPreId(String preId) {
        this.preId = preId == null ? null : preId.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getListId() {
        return listId;
    }

    public void setListId(String listId) {
        this.listId = listId == null ? null : listId.trim();
    }

    public String getRecId() {
        return recId;
    }

    public void setRecId(String recId) {
        this.recId = recId == null ? null : recId.trim();
    }

	@Override
	public String toString() {
		return "Prescription [preId=" + preId + ", pid=" + pid + ", date=" + date + ", fee=" + fee + ", listId="
				+ listId + ", recId=" + recId + ", pharmacy=" + pharmacy + ", patientname=" + patientname
				+ ", patientSex=" + patientSex + ", patientAge=" + patientAge + ", patientBirthday=" + patientBirthday
				+ ", chufanMedicineList=" + chufanMedicineList + "]";
	}
    
    
}