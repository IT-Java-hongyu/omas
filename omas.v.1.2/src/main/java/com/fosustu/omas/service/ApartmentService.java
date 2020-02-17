package com.fosustu.omas.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.fosustu.omas.pojo.Apartment;
import com.fosustu.omas.pojo.Pagination;
import com.fosustu.omas.pojo.Tag;
import com.fosustu.omas.pojo.TagList;


public interface ApartmentService {
     
	List<Apartment> listByPage(Pagination agination);  //部门分页
     Apartment getApartementById(String id);           //通过id获取部门
     void updateApartmentById(Apartment apartment);    //通过id更新部门
     void deleteApartmentById(Apartment apartment);     //通过id删除部门
     void addApartment(Apartment apartment);           //新增部门
     int getCount();                                   //获取部门总数

     List<Apartment> getRankedApartments(TagList tagList);
     
     List<Apartment> getAllApartement();               //获取所有部门的列表

     List<Tag> getAllTags();
      
     public void export(OutputStream os);              //导出部门信息
      public void doImport(InputStream is);            //导入部门信息
}
