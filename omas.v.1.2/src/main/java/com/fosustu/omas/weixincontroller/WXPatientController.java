package com.fosustu.omas.weixincontroller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fosustu.omas.pojo.Apartment;
import com.fosustu.omas.pojo.CardInform;
import com.fosustu.omas.pojo.Doctor;
import com.fosustu.omas.pojo.InputForSearchDoctor;
import com.fosustu.omas.pojo.Maps;
import com.fosustu.omas.pojo.Patient;
import com.fosustu.omas.pojo.MedicineTime;
import com.fosustu.omas.pojo.MedicineTimeInput;
import com.fosustu.omas.pojo.Record;
import com.fosustu.omas.pojo.Symptoms;
import com.fosustu.omas.service.MapService;
import com.fosustu.omas.service.MedicineListService;
import com.fosustu.omas.service.PatientService;
import com.fosustu.omas.service.WXLoginService;
import com.fosustu.omas.utils.AjaxReturnUtil;
import com.fosustu.omas.utils.OmasException;

@Controller
@RequestMapping("wxpatient")
public class WXPatientController {
	
	@Autowired
	private PatientService _patientService;
	@Autowired
	private MapService _mapService;
	@Autowired
	private PatientService patientService;
	private WXLoginService _wxLoginServiceImpl;
	@Autowired
	private MedicineListService _medicineListService;
	/**
	 * 查询病人的症状，方便形成反馈
	 * @param pid
	 * @return
	 */
	@RequestMapping("searchSymptoms")
	public @ResponseBody Record SearchSymptoms(String pid) {
		Record r=new Record();
		if(pid != null && !"".equals(pid.trim())) { //有病人id才查
			r=_patientService.SearchSymptoms(pid);
		}		
		//r为空代表没有该病人的就诊记录或者没有该病人，在小程序中可以通过判断msg的值给予相应的提示
		return r;
	}
	
	/**
	 * 记录病人填写的反馈信息(日常记录和复诊前记录均调用此接口，sign用于标记是哪一种情况(0为日常记录,1为复诊))
	 * sign写在record类中
	 * @param r
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="updateSymptomsOrTrack" ,method=RequestMethod.POST)
	public @ResponseBody boolean UpdateSymptomsOrTrack(HttpServletRequest request)throws ParseException{
		String[] str = request.getParameterValues("record");
		Map json = (Map) JSONObject.parse(str[0]);
		Record r = JSON.toJavaObject((JSON)json, Record.class);
		List symptoms = JSON.toJavaObject((JSON)json.get("rSymptomsList"), List.class);
		
		List<Symptoms> symList = new ArrayList<Symptoms>();
		for(int i=0;i<symptoms.size();i++) {
			Symptoms symp = JSON.toJavaObject((JSON)symptoms.get(i),Symptoms.class);
			symList.add(symp);
		}
		r.setrSymptomsList(symList);
		boolean result=_patientService.UpdateSymptomsOrTrack(r,r.getSign());
		return result;
	}
	/**
	 * 医生推荐
	 * @param condition
	 * @return
	 */
	@RequestMapping(value="searchDoctorList",produces="application/json;charset=utf-8")
	public @ResponseBody InputForSearchDoctor SearchDoctorList(@RequestBody InputForSearchDoctor condition){
		 try {
			 condition=_patientService.SearchDoctorList(condition);
		 }catch(OmasException e) {
			 e.printStackTrace();
		 }
		return condition;
	}
	
	/**
	 * 获得地图
	 * @param map
	 * @return
	 */
	@RequestMapping(value="getMap",produces = "text/plain;charset=utf-8")
	public @ResponseBody String GetMap(@RequestBody Maps map) {
		
		map=_mapService.searchMap(map);		
		if(map.getUrl()==null || map.getUrl().isEmpty()) {
			return AjaxReturnUtil.ajaxReturn(false,"sorry,该路线不存在,待完善,请查询其他路线！");
		}
		String path ="/images/map/";
		StringBuffer buffer = new StringBuffer();
		buffer.append(path);
		map.setUrl(buffer.append(map.getUrl()).toString());
		Map<String, Object> message = new HashMap<String,Object>();
		message.put("map", map);
		return AjaxReturnUtil.ajaxReturn(true,message);
	}
	/**
	 * 获取吃药时间
	 * @param mt
	 * @param request
	 * @return
	 */
	@RequestMapping(value="getMedicineTime",produces = "application/json;charset=utf-8")
	public @ResponseBody String GetMedicineTime(@RequestBody MedicineTimeInput mt,HttpServletRequest request) {
		List<MedicineTime> timeList=_medicineListService.getMedicineTime(mt);
		if(timeList.isEmpty()) {
			return AjaxReturnUtil.ajaxReturn(false,"无法找到对应的记录!");
		}
		String token= request.getHeader("token");
		String openid=_wxLoginServiceImpl.getUserByToken(token).getOpenid();
		Map<String, Object> message = new HashMap<String,Object>();
		message.put("timeList", timeList);
		message.put("openid", openid);
		return AjaxReturnUtil.ajaxReturn(true,message);
	}
	
	/**
	 * 更新病人信息
	 * @param patient
	 * @return
	 */
	@RequestMapping("updataPatient")
	@ResponseBody
	public String updataPatient(@RequestBody Patient patient) {
		patientService.updatePatientById(patient);
		return AjaxReturnUtil.ajaxReturn(true, "保存成功");
	}
	
	/**
	 * 查询病人信息
	 * @param patientId
	 * @return
	 */
	@RequestMapping(value="getPatientById",produces = "apllication/json;charset=utf-8")
	@ResponseBody
	public String getPatientById(@RequestBody String patientId) {
		Patient patient = patientService.getPatientById(patientId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("patient", patient);
		return AjaxReturnUtil.ajaxReturn(true, map);
	}
	
	/**
	 * 添加医疗卡
	 * @param patiendId
	 * @param cardId
	 * @return
	 */
	@RequestMapping(value="card",produces = "apllication/json;charset=utf-8")
	@ResponseBody
	public String addCard( @RequestBody CardInform cardInform) {
		System.out.println(cardInform);
		if ("".equals(cardInform.getCardId())) {
			String card = patientService.getPatientCardById(cardInform.getPatientId());
//			Map< String, Object>map = new HashMap<String,Object>();
//			map.put("cardId", card);
			return AjaxReturnUtil.ajaxReturn(true, card);
		}else {
			patientService.updataCardId(cardInform);
			return AjaxReturnUtil.ajaxReturn(true, "绑定成功！");
		}
		
		
		
	}
}
