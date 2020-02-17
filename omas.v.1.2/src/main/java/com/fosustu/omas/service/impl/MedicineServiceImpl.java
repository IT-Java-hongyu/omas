package com.fosustu.omas.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosustu.omas.mapper.MedicineListMapper;
import com.fosustu.omas.mapper.MedicineMapper;
import com.fosustu.omas.mapper.PrescriptionMapper;
import com.fosustu.omas.pojo.Medicine;
import com.fosustu.omas.pojo.MedicineList;
import com.fosustu.omas.pojo.Prescription;
import com.fosustu.omas.service.MedicineService;
@Service
public class MedicineServiceImpl implements MedicineService {
	
	@Autowired
	private MedicineMapper medicineMapper;
	
	@Autowired
	private MedicineListMapper medicineListMapper;
	
	@Autowired
	private PrescriptionMapper prescriptionMapper; 
	/**
	 * 通过药物名获取药物
	 */
	@Override
	public List<Medicine> findMedicineByMedicineName(String medicineName) {
		// TODO Auto-generated method stub
		return medicineMapper.findMedicineByMedicineName(medicineName);
	}
	/**
	 * 通过症状获取药物
	 */
	@Override
	public List<Medicine> findMedicineByMedicineSyptoms(String smyptoms) {
		String[] symptoms1 = smyptoms.split(",");
		
		System.out.println("smyptoms::::"+smyptoms);
		List<Medicine> medicineList = new ArrayList<Medicine>();
		for(String s : symptoms1) {
			List<Medicine> mdc= medicineMapper.findMedicineByMedicineSyptoms(s);
			for(Medicine m : mdc) {
				if(!medicineList.contains(m))
					medicineList.add(m);
			}
		}
		System.out.println("medicineList===="+medicineList.toString());
		return medicineList;
	}
	
	//保存药物列表
	@Override
	public void saveMedicineListAndChufang(HttpServletRequest request, String medicinePriceString ,String medicineIdString, String medicineNumString , String medicineUsageString ,String medicineZengduanResult) {
		String[] medicineId = medicineIdString.split(",");
		String[] medicineNum = medicineNumString.split(",");
		String[] mdecinePrice = medicinePriceString.split(",");
		String[] medicineUsage = medicineUsageString.split(";");
		String prescrptionId = UUID.randomUUID().toString().replaceAll("-", "");
		//记录日期
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String nowTime = dateFormat.format(now);//格式化然后放入字符串中
		Double totalPrice = 0.0;
		
		
		//计算费用
		for(int i=0;i<medicineId.length;i++) {
			String strNum = medicineNum[i].replaceAll("[^\\d+]", "");
			System.out.println(strNum);
			totalPrice = totalPrice + (double) (Integer.parseInt(strNum) * Double.parseDouble(mdecinePrice[i])); 
			
		}
		System.out.println(totalPrice);
		//获取病历id  未获取
		String recId = (String)request.getSession().getAttribute("recordId");
		
		
		
		Prescription pre = new Prescription();
		pre.setPreId(prescrptionId);
		pre.setDate(nowTime);
		pre.setFee(totalPrice);
		pre.setRecId(recId);
		pre.setPharmacy("药房302");
		pre.setZengduanResult(medicineZengduanResult);
		//保存处方
		prescriptionMapper.saveChufang(pre);
		
		//保存药物列表
		MedicineList ml = new MedicineList();
		
		for(int i=0;i<medicineId.length;i++) {
			//药物列表ID
			//String medicineListId = UUID.randomUUID().toString().replaceAll("-", "");
			
			ml.setmId(medicineId[i]);
			ml.setNum(medicineNum[i]);
			ml.setPreId(prescrptionId);
			ml.setUsage(medicineUsage[i]);
			medicineListMapper.saveMedicineList(ml);
		}
	}

}
