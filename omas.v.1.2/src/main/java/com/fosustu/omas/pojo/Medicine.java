package com.fosustu.omas.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class Medicine {
    private String mId;

    private String mName;

    private String mAttend;

    private String mUsage;

    private Double mPrice;
    private String medicineImg;
   
   public String getMedicineImg() {
		return medicineImg;
	}

	public void setMedicineImg(String medicineImg) {
		this.medicineImg = medicineImg;
	}

@JSONField(name="mId")
    public String getmId() {
        return mId;
    }
    
    @JSONField(name="mId")
    public void setmId(String mId) {
        this.mId = mId == null ? null : mId.trim();
    }
    @JSONField(name="mName")
    public String getmName() {
        return mName;
    }
    @JSONField(name="mName")
    public void setmName(String mName) {
        this.mName = mName == null ? null : mName.trim();
    }
    @JSONField(name="mAttend")
    public String getmAttend() {
        return mAttend;
    }
    @JSONField(name="mAttend")
    public void setmAttend(String mAttend) {
        this.mAttend = mAttend == null ? null : mAttend.trim();
    }
    @JSONField(name="mUsage")
    public String getmUsage() {
        return mUsage;
    }
    @JSONField(name="mUsage")
    public void setmUsage(String mUsage) {
        this.mUsage = mUsage == null ? null : mUsage.trim();
    }
    @JSONField(name="mPrice")
    public Double getmPrice() {
        return mPrice;
    }
    @JSONField(name="mPrice")
    public void setmPrice(Double mPrice) {
        this.mPrice = mPrice;
    }

	@Override
	public String toString() {
		return "Medicine [mId=" + mId + ", mName=" + mName + ", mAttend=" + mAttend + ", mUsage=" + mUsage + ", mPrice="
				+ mPrice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mAttend == null) ? 0 : mAttend.hashCode());
		result = prime * result + ((mId == null) ? 0 : mId.hashCode());
		result = prime * result + ((mName == null) ? 0 : mName.hashCode());
		result = prime * result + ((mPrice == null) ? 0 : mPrice.hashCode());
		result = prime * result + ((mUsage == null) ? 0 : mUsage.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicine other = (Medicine) obj;
		if (mAttend == null) {
			if (other.mAttend != null)
				return false;
		} else if (!mAttend.equals(other.mAttend))
			return false;
		if (mId == null) {
			if (other.mId != null)
				return false;
		} else if (!mId.equals(other.mId))
			return false;
		if (mName == null) {
			if (other.mName != null)
				return false;
		} else if (!mName.equals(other.mName))
			return false;
		if (mPrice == null) {
			if (other.mPrice != null)
				return false;
		} else if (!mPrice.equals(other.mPrice))
			return false;
		if (mUsage == null) {
			if (other.mUsage != null)
				return false;
		} else if (!mUsage.equals(other.mUsage))
			return false;
		return true;
	}
    
}