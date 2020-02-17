package com.fosustu.omas.mapper;

import java.util.List;

import com.fosustu.omas.pojo.Doctor;
import com.fosustu.omas.pojo.InputForSearchDoctor;

import com.fosustu.omas.pojo.Pagination;

public interface DoctorMapper {
    List<Doctor> SearchDocListByTitle(InputForSearchDoctor condition);  //通过医生职称获取医生

    List<Doctor> listByPage(Pagination agination);                   //医生分页

    List<Doctor> getDoctorByAprtId(String aprtId);                   //通过部门id获取医生

    Doctor getDoctorById(String id);                                 //通过id获取医生

    void updateDoctorById(Doctor doctor);                            //更新医生

    void deleteDoctorById(Doctor doctor);                            //删除医生

    void addDoctor(Doctor doctor);                                   //新增医生

    int getCount();                                                  //获取医生总数

    List<Doctor> getAllDoctor();                                     //获取所有医生

    List<Doctor> getAllDoctorToExcel();                              //获取所有医生到Excel

    List<Doctor> getAlldoctorInformation();

    void updateDoctorScoreById(Doctor doctor);
}