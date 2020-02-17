package com.fosustu.omas.weixincontroller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fosustu.omas.pojo.Apartment;
import com.fosustu.omas.pojo.Doctor;
import com.fosustu.omas.service.ApartmentService;
import com.fosustu.omas.service.DoctorService;
import com.fosustu.omas.service.GuaHaoService;
import com.fosustu.omas.utils.AjaxReturnUtil;

@RequestMapping("wx")
@Controller
public class WinXinGuaHaoController {

    @Autowired
    private GuaHaoService guaHaoService;
//	private DoctorService doctorServise;
//	private ApartmentService apartmentService;

    @RequestMapping(value = "Guahao", produces = "apllication/json;charset=utf-8")
    @ResponseBody
    public String guaHao(String apartId, String doctorId, String patientId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (guaHaoService.getGuahaoStatus(patientId) == 0) {     //判断是否挂号  为0则不是挂号状态
            int[] res = guaHaoService.guaHao(apartId, doctorId, patientId);   //返回三个数据 挂号码 排队数 挂号状态
            Doctor doctor = guaHaoService.getDoctorByPatientId(patientId);
            System.err.println("==================");
            System.err.println(doctor.getDocName());
            String apartment = guaHaoService.getApartmentNameByPatientId(patientId);
            map.put("status", res);
            map.put("doctor", doctor);
            map.put("apartment", apartment);
            return AjaxReturnUtil.ajaxReturn(true, map);
        } else {
            int[] res = new int[3];
            int guahaoId = guaHaoService.findGuahaoIdBypatientIdAndGuahaoBiaoZhi(patientId);
            res[0] = guahaoId;   //挂号码
            res[1] = guaHaoService.findPatientNum(guahaoId, doctorId); //排队数
            res[2] = guaHaoService.getGuahaoStatus(patientId); //挂号状态
            Doctor doctor = guaHaoService.getDoctorByPatientId(patientId);
            System.err.println(doctor.getDocName());
            String apartment = guaHaoService.getApartmentNameByPatientId(patientId);
            map.put("doctor", doctor);
            map.put("apartment", apartment);
            map.put("status", res);
            System.out.println(JSON.toJSONString(map));
            return AjaxReturnUtil.ajaxReturn(true, map); //返回  1 表示已挂号
        }

    }

    @RequestMapping("checkGuahao")
    @ResponseBody
    public String checkGuahao(String patientId) {
        int status = guaHaoService.getGuahaoStatus(patientId);
        System.out.println(patientId + "================================================================================================================" + status);
        Map<String, Object> map = new HashMap<String, Object>();
        if (status == 1) {
            map.put("status", 1);
            return AjaxReturnUtil.ajaxReturn(true, map);
        } else {
            map.put("status", 0);
            return AjaxReturnUtil.ajaxReturn(true, map);
        }

    }
}
