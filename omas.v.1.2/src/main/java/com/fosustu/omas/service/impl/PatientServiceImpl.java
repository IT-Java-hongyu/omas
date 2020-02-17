package com.fosustu.omas.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.fosustu.omas.mapper.ApartmentMapper;
import com.fosustu.omas.mapper.DoctorMapper;
import com.fosustu.omas.mapper.PatientMapper;
import com.fosustu.omas.mapper.RecordMapper;
import com.fosustu.omas.mapper.SymptomsMapper;
import com.fosustu.omas.mapper.TrackingMapper;
import com.fosustu.omas.mapper.VisitTimeMapper;
import com.fosustu.omas.pojo.Apartment;
import com.fosustu.omas.pojo.CardInform;
import com.fosustu.omas.pojo.Doctor;
import com.fosustu.omas.pojo.InputForSearchDoctor;
import com.fosustu.omas.pojo.Patient;
import com.fosustu.omas.pojo.Pagination;
import com.fosustu.omas.pojo.Record;
import com.fosustu.omas.pojo.Symptoms;
import com.fosustu.omas.pojo.Tracking;
import com.fosustu.omas.service.PatientService;
import com.fosustu.omas.utils.LuoErrorCode;
import com.fosustu.omas.utils.OmasException;
import com.fosustu.omas.utils.TimeUtils;

//事务注解
@Transactional
@Service
public class PatientServiceImpl implements PatientService{

	@Autowired
	private SymptomsMapper _symptomsMapper;
	@Autowired
	private RecordMapper _recordMapper;
	@Autowired
	private TrackingMapper _trackingMapper;
	
	@Autowired
	private PatientMapper patientMapper;
	@Autowired
	private ApartmentMapper _apartmentMapper;
	@Autowired
	private DoctorMapper _doctorMapper;
	@Autowired
	private VisitTimeMapper _visitTimeMapper;

	
	//查询病症形成用于填写医疗效果的反馈表
	public Record SearchSymptoms(String pid){	
		/*
		 * 1.先通过病人pid找到最新的病历（有可能是初诊，也可能是复诊（有可能要复诊很多次））
		 * 2.获取病历id
		 * 3.再通过病历id查出病症
		 * */
 
		Record  r = _recordMapper.SearchNewRecord(pid);
		List<Symptoms> list =new ArrayList<Symptoms>();
		if(r != null) {
			list = _symptomsMapper.SearchSymptoms(r.getRecId());
			if(list != null) {
				r.setrSymptomsList(list);
			}
		}	
		
		return r;
	}
	
	//保存填写的症状反馈信息
	public boolean UpdateSymptomsOrTrack(Record r,int sign) throws ParseException {
		//
		System.out.println("========================================"+r.toString()+"===============================================");
		List<Symptoms> symptomsList = r.getrSymptomsList();
		//把症状列表转换成json字符串存进数据库
		String s = JSON.toJSONString(symptomsList);
		Tracking t =new Tracking();
		t.setDesId(String.valueOf(Math.random()*100000));
		t.setpId(r.getpId());
		t.setRecId(r.getRecId());
		t.setDesDate(new Date());
		t.setSymptom(s);
		t.setStatus(0);	//0表示日常记录				
		if(sign == 1) {//复诊前记录,还要修改症状表的症状最新状态
			t.setStatus(1);//1是复诊前记录
			for(Symptoms symptoms : symptomsList ) {
				_symptomsMapper.UpdateSymptoms(symptoms);
			}
		}
		/*
		 * 1.先根据病人id和病历id和状态查询日常记录表最新的一条数据;
		 * 2.判断该数据是不是空的：
		 *     若是空，则插入
		 *     否则，判断时间是不是今天的，如果不是则插入，否则修改
		 */
		Tracking temp = _trackingMapper.SearchNewest(t);
		if(temp == null || !new TimeUtils().Compare(temp.getDesDate())) {
			_trackingMapper.insertTrack(t); 
		}else {
			_trackingMapper.UpdateTrack(t); 
		}
		return true;
	}

	@Override
	public InputForSearchDoctor SearchDoctorList(InputForSearchDoctor condition) {
		
	
/*		if(condition.getApartment()==null ) {
			不明确科室就先判断是什么科室，通过病症判断
			 * 1.查科室对应的典型症状来判断科室，查不到执行2
			 
			
			Apartment apartMent = _apartmentMapper.SearchBySymptoms(condition);
			if(apartMent ==null ||apartMent.getApartId().equals("")) {
				String symptoms =condition.getSymptoms();
				if(!symptoms.isEmpty()) {
					//2.可以查症状表或者医生特长，
					apartMent = _apartmentMapper.SearchApartmentByDocSpeciality(symptoms);
					if(apartMent ==null ||apartMent.getApartId().equals("")) {
						apartMent = _apartmentMapper.SearchApartmentBySymptoms(symptoms);
					}					
				}
			}
			if(apartMent != null) {
				condition.setApartment(apartMent);
			}else {
				//没有找到科室
				new OmasException(LuoErrorCode.UNSEARCH_APARTMENT);
			}
		}*/
		Apartment apartMent=condition.getApartment();
		if(apartMent!=null && (apartMent.getApartId()==null || apartMent.getApartId()=="")) {
			String symptoms =condition.getSymptoms();
			if(symptoms!=null && symptoms !="") {
				//通过查症状表或者医生特长等到科室
				apartMent = _apartmentMapper.SearchApartmentByDocSpeciality(symptoms);
				if(apartMent ==null) {
					apartMent = _apartmentMapper.SearchApartmentBySymptoms(symptoms);
				}	
				if(apartMent != null) {
					condition.setApartment(apartMent);
				}
			}
		}
		List<Doctor> docList =new ArrayList<Doctor>();
		//明确科室后进行其他条件的组合查询
		if(condition.getTitle()!=null || condition.getDate()!=null) {
			docList = _doctorMapper.SearchDocListByTitle(condition);
		}
		if(docList != null) {
			for(Doctor d : docList) { //查找医生出诊列表
				d.setDateList(_visitTimeMapper.SearchVisitList(d.getDocId(),new Date()));
			}
			condition.setDoctor(docList);
		}else {
			new OmasException(LuoErrorCode.UNSEARCH_DOCTORLIST);
		}
		return condition;
	}

	/**
	 * 病人分页
	 */
	@Override
	public List<Patient> listByPage(Pagination agination) {
		return patientMapper.listByPage(agination);
	}
	/**
	 * 通过id获取病人信息
	 */
	@Override
	public Patient getPatientById(String id) {
		return patientMapper.getPatientById(id);
	}
	/**
	 * 更新病人信息
	 */
	@Override
	public void updatePatientById(Patient patient) {
		patientMapper.updatePatientById(patient);
		
	}
	/**
	 * 删除病人
	 */
	@Override
	public void deletePatientById(Patient patient) {
		patientMapper.deletePatientById(patient);
	}
	/**
	 * 新增病人
	 */
	@Override
	public void addPatient(Patient patient) {
		patientMapper.addPatient(patient);
	}
	/**
	 * 获取病人总数
	 */
	@Override
	public int getCount() {
		
		return patientMapper.getCount();
	}
	
	/**
	 * 导出病人信息
	 */
	@Override
	public void export(OutputStream os) {
		//获取要导出的数据列表
		List<Patient> list = patientMapper.getAllPatient();
		//创建一个工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		
		String sheetName = "病人信息";
		
		//创建一个工作表
		HSSFSheet sheet = wb.createSheet(sheetName);
		//创建一行,行的索引是从0开始, 写标题
		HSSFRow row = sheet.createRow(0);
		String[] header = {"编号","病人名称","性别","生日","电话号码","地址","婚姻","邮箱","职业","医疗卡号"};
		int[] width = {5000,8000,8000,8000,8000,8000,8000,8000,8000,8000};
		HSSFCell cell = null;
		for(int i = 0; i < header.length; i++){
			cell = row.createCell(i);
			cell.setCellValue(header[i]);
			//设置列宽
			sheet.setColumnWidth(i, width[i]);
		}
		//导出的内容
		int rowCount = 1;
		for(Patient patient : list){
			row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(patient.getPatientId());//id
			row.createCell(1).setCellValue(patient.getPatientName());//名称
			row.createCell(2).setCellValue(judgeSex(patient.getPatientSex()));//性别
			row.createCell(3).setCellValue(patient.getPatientBirthday());//生日
			row.createCell(4).setCellValue(patient.getPatientPhone());//电话号码
			row.createCell(5).setCellValue(patient.getPatientAddress());//简介
			row.createCell(6).setCellValue(judgeMari(patient.getPatientMarriage()));//职称
			row.createCell(7).setCellValue(patient.getPatientEmail());//科室

			row.createCell(8).setCellValue(patient.getPatientPor());//科室
			row.createCell(9).setCellValue(patient.getCardId());//科室
		}
		try {
			wb.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String judgeSex(Integer num) {
		if(num==1)
			return "男";
		else
			return "女";
	}
	public String judgeMari(Integer num) {
		if(num==1)
			return "未婚";
		else
			return "已婚";
	}
	/**
	 * 导入数据
	 */
	@Override
	public void doImport(InputStream is) {
		HSSFWorkbook wb = null;
		try {
			try {
				wb = new HSSFWorkbook(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HSSFSheet sheet = wb.getSheetAt(0);
			//读取数据
			//最后一行的行号
			int lastRow = sheet.getLastRowNum();
			Patient patient = null;
			for(int i = 1; i <= lastRow; i++){
				patient = new Patient();
				//判断是否已经存在，通过ID来判断
				Patient patient1 = patientMapper.getPatientById(sheet.getRow(i).getCell(0).getStringCellValue()); 
				if( patient1 == null){
					patient.setPatientId(sheet.getRow(i).getCell(0).getStringCellValue());
					patient.setPatientName(sheet.getRow(i).getCell(1).getStringCellValue());
					patient.setPatientSex((int)Integer.parseInt(sheet.getRow(i).getCell(2).getStringCellValue()));
					patient.setPatientBirthday(sheet.getRow(i).getCell(3).getStringCellValue());
					patient.setPatientPhone(sheet.getRow(i).getCell(4).getStringCellValue());
					patient.setPatientAddress(sheet.getRow(i).getCell(5).getStringCellValue());
					patient.setPatientMarriage((int)Integer.parseInt(sheet.getRow(i).getCell(6).getStringCellValue()));
					patient.setPatientEmail(sheet.getRow(i).getCell(7).getStringCellValue());
					patient.setPatientPor(sheet.getRow(i).getCell(8).getStringCellValue());
					patient.setCardId(sheet.getRow(i).getCell(9).getStringCellValue());
					patientMapper.addPatient(patient);
				}
			}			
		} finally{
			if(null != wb){
				try {
					wb.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	/**
	 * 获取医疗卡
	 */
	@Override
	public String getPatientCardById(String patientId) {
		String card = patientMapper.getPatientCardById(patientId);
		return card;
	}
	/**
	 * 更新医疗卡
	 */
	@Override
	public void updataCardId(CardInform cardInform) {
		patientMapper.updataCardId(cardInform);
		
	}

	
}
