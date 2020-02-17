package com.fosustu.omas.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.fosustu.omas.pojo.Doctor;
import com.fosustu.omas.pojo.Pagination;

public interface DoctorService {
    List<Doctor> listByPage(Pagination agination);  //医生分页

    Doctor getDoctorById(String id);                //通过id获取医生

    void updateDoctorById(Doctor doctor);           //通过id更新医生

    void deleteDoctorById(Doctor doctor);           //通过id删除医生

    void addDoctor(Doctor doctor);                  //新增医生

    int getCount();                                 //获取医生总数

    List<Doctor> getDoctorByApartId(String apartId); //通过部门id获取医生

    List<Doctor> getAllDoctor();                     //获取所有医生

    public void export(OutputStream os);            //导出医生信息

    public void doImport(InputStream is);           //导入医生信息

    List<Doctor> getAlldoctorInformation();

    List<Doctor> getRankedDoctorByApartId(String apartId);

    void updateDoctorScoreById(Doctor doctor);
}
