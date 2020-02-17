package com.fosustu.omas.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fosustu.omas.mapper.DoctorMapper;
import com.fosustu.omas.pojo.Apartment;
import com.fosustu.omas.pojo.Doctor;
import com.fosustu.omas.pojo.Pagination;
import com.fosustu.omas.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	private DoctorMapper doctorMapper;
	/**
	 * 医生分页
	 */
	@Override
	public List<Doctor> listByPage(Pagination agination) {
		return doctorMapper.listByPage(agination);
	}
	/**
	 * 通过id获取医生
	 */
	@Override
	public Doctor getDoctorById(String id) {
		return doctorMapper.getDoctorById(id);
	}
	/**
	 * 通过id更新医生
	 */
	@Override
	public void updateDoctorById(Doctor doctor) {
		doctorMapper.updateDoctorById(doctor);
		
	}
	/**
	 * 通过id删除医生
	 *
	 */
	@Override
	public void deleteDoctorById(Doctor doctor) {
		doctorMapper.deleteDoctorById(doctor);
	}
	/**
	 * 新增医生
	 */
	@Override
	public void addDoctor(Doctor doctor) {
		doctorMapper.addDoctor(doctor);
	}
	/**
	 * 获取医生总数
	 */
	@Override
	public int getCount() {
		
		return doctorMapper.getCount();
	}
	/**
	 * 通过部门id获取医生
	 */
	@Override
	public List<Doctor> getDoctorByApartId(String apartId) {
		// TODO Auto-generated method stub
		return doctorMapper.getDoctorByAprtId(apartId);
	}
	/**
	 * 获得所有医生列表
	 */
	@Override
	public List<Doctor> getAllDoctor() {
		// TODO Auto-generated method stub
		return doctorMapper.getAllDoctor();
	}
	/*
	 * 导出医生信息
	 * (non-Javadoc)
	 * @see com.fosustu.omas.service.DoctorService#export(java.io.OutputStream)
	 */
	@Override
	public void export(OutputStream os) {
		//获取要导出的数据列表
		List<Doctor> list = doctorMapper.getAllDoctorToExcel();
		//创建一个工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		
		String sheetName = "医生信息";
		
		//创建一个工作表
		HSSFSheet sheet = wb.createSheet(sheetName);
		//创建一行,行的索引是从0开始, 写标题
		HSSFRow row = sheet.createRow(0);
		String[] header = {"编号","医生名称","性别","生日","电话号码","简介","职称","科室"};
		int[] width = {5000,8000,8000,8000,8000,8000,8000,8000};
		HSSFCell cell = null;
		for(int i = 0; i < header.length; i++){
			cell = row.createCell(i);
			cell.setCellValue(header[i]);
			//设置列宽
			sheet.setColumnWidth(i, width[i]);
		}
		//导出的内容
		int rowCount = 1;
		for(Doctor doctor : list){
			row = sheet.createRow(rowCount++);
			row.createCell(0).setCellValue(doctor.getDocId());//id
			row.createCell(1).setCellValue(doctor.getDocName());//名称
			row.createCell(2).setCellValue(judgeSex(doctor.getSex()));//性别
			row.createCell(3).setCellValue(doctor.getBirthday());//生日
			row.createCell(4).setCellValue(doctor.getPhone());//电话号码
			row.createCell(5).setCellValue(doctor.getSynopsis());//简介
			row.createCell(6).setCellValue(doctor.getTileName());//职称
			row.createCell(7).setCellValue(doctor.getApartName());//科室
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
			Doctor doctor = null;
			for(int i = 1; i <= lastRow; i++){
				doctor = new Doctor();
				//判断是否已经存在，通过ID来判断
				Doctor doctor1 = doctorMapper.getDoctorById(sheet.getRow(i).getCell(0).getStringCellValue()); 
				if( doctor1 == null){
					doctor.setDocId(sheet.getRow(i).getCell(0).getStringCellValue());
					doctor.setDocName(sheet.getRow(i).getCell(1).getStringCellValue());
					doctor.setSex((int)Integer.parseInt(sheet.getRow(i).getCell(2).getStringCellValue()));
					doctor.setBirthday(sheet.getRow(i).getCell(3).getStringCellValue());
					doctor.setPhone(sheet.getRow(i).getCell(4).getStringCellValue());
					doctor.setSynopsis(sheet.getRow(i).getCell(5).getStringCellValue());
					doctor.setApartId(sheet.getRow(i).getCell(6).getStringCellValue());
					doctor.setTileId(sheet.getRow(i).getCell(7).getStringCellValue());
					doctorMapper.addDoctor(doctor);
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
	@Override
	public List<Doctor> getAlldoctorInformation() {
		// TODO Auto-generated method stub
		return doctorMapper.getAlldoctorInformation();
	}

	@Override
	public List<Doctor> getRankedDoctorByApartId(String apartId) {
		List<Doctor> list = doctorMapper.getDoctorByAprtId(apartId);
		for(int i=0;i<list.size();i++){
			for(int j=1;j<list.size()-i;j++){
				if(Double.parseDouble(list.get(j).getScore()) > Double.parseDouble(list.get(j-1).getScore())){
					Doctor doctor = list.get(j);
					list.set(j,list.get(j-1));
					list.set(j-1,doctor);
				}
			}
		}
		return list;
	}

	@Override
	public void updateDoctorScoreById(Doctor doctor1) {
		Doctor doctor0 = doctorMapper.getDoctorById(doctor1.getDocId());
		double score = Double.parseDouble(doctor0.getScore());
		int scoreTimes = Integer.parseInt(doctor0.getScoreTimes());
		double newScore = (score*scoreTimes + Double.parseDouble(doctor1.getScore()))/(scoreTimes+1);
		doctor0.setScore(String.valueOf(newScore).substring(0,3));
		doctor0.setScoreTimes(String.valueOf(scoreTimes+1));
		doctorMapper.updateDoctorScoreById(doctor0);
	}
}
