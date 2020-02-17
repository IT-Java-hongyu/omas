package com.fosustu.omas.mapper;

import com.fosustu.omas.pojo.Record;

public interface RecordMapper {
	Record SearchNewRecord(String pid);  //查找新病历
	void saveRecord(Record record);      //保存病历
}