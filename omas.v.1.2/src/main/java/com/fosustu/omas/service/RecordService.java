package com.fosustu.omas.service;

import com.fosustu.omas.pojo.Record;

public interface RecordService {
	Record SearchNewRecord(String pid);//找最新病例
}
