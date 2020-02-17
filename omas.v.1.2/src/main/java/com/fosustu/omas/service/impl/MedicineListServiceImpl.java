package com.fosustu.omas.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosustu.omas.mapper.MedicineListMapper;
import com.fosustu.omas.mapper.MedicineMapper;
import com.fosustu.omas.pojo.Medicine;
import com.fosustu.omas.pojo.MedicineList;
import com.fosustu.omas.pojo.MedicineTime;
import com.fosustu.omas.pojo.MedicineTimeInput;
import com.fosustu.omas.pojo.Record;
import com.fosustu.omas.service.MedicineListService;
import com.fosustu.omas.service.RecordService;

@Service
public class MedicineListServiceImpl implements MedicineListService {

	@Autowired
	private RecordService _recordService;
	@Autowired
	private MedicineListMapper _medicineListMapper;
	@Autowired
	private MedicineMapper _medicineMapper;
	@Override
	public List<MedicineTime> getMedicineTime(MedicineTimeInput mt) {
		
		Record r =_recordService.SearchNewRecord(mt.getPid());//找到最新的记录
		if(r==null) {
			return null;
		}
		List<MedicineList> list=_medicineListMapper.getMedicineList(r.getRecId());//查找要吃的药品列表
		if(list == null) {
			return null;
		}
		
		List<MedicineTime> mtList = new ArrayList<MedicineTime>();
//		List<Integer> temp =new ArrayList<Integer>();
		for(MedicineList m : list) { 
			String usage = m.getUsage();						 			
			int frequency=ChineseToNum(usage.substring(2, 3)), //每天吃药的频率(一天几次)
					d=ChineseToNum(usage.substring(7, 8)),//每次的用量
					n=Integer.parseInt(m.getNum()); //某种药物所开的总量			
			float days = n/(frequency*d);//可以吃的天数
			int time = 24/frequency; //每次吃药相隔几个小时
			
			Medicine medicine =_medicineMapper.searchMedicineNameById(m.getmId());//查找药品名称
			MedicineTime medicineTime = new MedicineTime();
/*			if(!temp.isEmpty()) {
				boolean isFind = false; //标记是否找到一样的频率的吃药时间
				for(int i:temp) {
					if(frequency==i) {						
						isFind =true;
						break;
					}
				}
				if(isFind) {
					for(MedicineTime mtOld:mtList) {//每天吃药的频率(一天几次)
						if(ChineseToNum(mtOld.getUsage().substring(2, 3))==frequency) {
							mtOld.setMedicineNames(mtOld.getMedicineNames()+"、"+medicine.getmName());
						}
					}
					
					continue; //找到就跳出本次循环，不再往下执行
				}
			}*/
			
			//temp.add(frequency);
			if(!mtList.isEmpty()) {
				boolean isFind = false; //标记是否找到一样的频率的吃药时间
				for(MedicineTime mtOld:mtList) {//看吃药的频率是否相同
					if(ChineseToNum(mtOld.getUsage().substring(2, 3))==frequency) {
						mtOld.setMedicineNames(mtOld.getMedicineNames()+"、"+medicine.getmName());
						isFind=true;
						break;
					}
				}
				if(isFind) {
					continue; //找到就跳出本次循环，不再往下执行
				}
			}
			List<Date> timeList = new ArrayList<Date>();
			Calendar calendar = Calendar.getInstance();
			Date dateTemp = ((mt.getStartTime()==null)? new Date() : mt.getStartTime());
			calendar.setTime(dateTemp);   //开始吃药时间			
			for(int i=1;i<n/d;i++) {					
				calendar.add(Calendar.HOUR_OF_DAY, time);
				Date date =calendar.getTime();
				timeList.add(date);					
			}
			medicineTime.setStartTime(mt.getStartTime());
			medicineTime.setMedicineTime(timeList);	
			medicineTime.setMedicineNames(medicine.getmName());
			medicineTime.setUsage(usage);
			mtList.add(medicineTime);
		
		}		
		return mtList;
	}
	static char[] cnArr = new char [] {'零','一','二','三','四','五','六','七','八','九'};
	public static int ChineseToNum(String s) {
        char c = s.toCharArray()[0];
        int value = 0;
        for (int j = 0; j < cnArr.length; j++) {
            if (c == cnArr[j]) {
            	System.out.println(j);
            	value=j;
            }
        }
        return value;        
	}

}
