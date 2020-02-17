package com.fosustu.omas.weixincontroller;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.fosustu.omas.pojo.Tag;
import com.fosustu.omas.pojo.TagList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fosustu.omas.pojo.Apartment;
import com.fosustu.omas.pojo.ApartmentList;
import com.fosustu.omas.service.ApartmentService;

@Controller
@RequestMapping("wxApartment")
public class WinXinApartmentController {
    @Autowired
    private ApartmentService apartmentService;

    @RequestMapping("getAllApartment")
    @ResponseBody
    public ApartmentList getAllApartment() {
        List<Apartment> apartmentList = apartmentService.getAllApartement();
        ApartmentList a = new ApartmentList();
        a.setApartmentList(apartmentList);
        //String apartmentJsonList = JSON.toJSONString(apartmentList, true);
        return a;
    }

    @RequestMapping("getRankedApartment")
    @ResponseBody
    public ApartmentList getRankedApartment(@RequestBody TagList tagList) {
        List<Apartment> apartmentList = apartmentService.getRankedApartments(tagList);
        ApartmentList a = new ApartmentList();
        a.setApartmentList(apartmentList);
        return a;
    }

    @RequestMapping("getAllTags")
    @ResponseBody
    public List<Tag> getAllTags() {
        List<Tag> list = apartmentService.getAllTags();
        return list;
    }

}
