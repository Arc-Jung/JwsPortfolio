package project.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.annotate.JsonProperty;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import project.domain.Plan;
import project.domain.TravelInfo;
import project.service.PlanService;
import project.service.TravelInfoService;

@Controller
@RequestMapping("project")
public class PlannerController {
		private static final Logger logger = LoggerFactory.getLogger(PlannerController.class);

	  @Autowired
	  private PlanService service;
	  
	  @Autowired
	  private TravelInfoService infoService;
	 
	  
	 
	  
	  @RequestMapping(value="myplan.do", method=RequestMethod.GET)
	  public String planner(Model model)
	  {
		  Map<String, List<TravelInfo>> infoMap = new HashMap<String, List<TravelInfo>>();
		 
		  try {
			  infoMap = infoService.findToPlanner();
			  
			  List<TravelInfo> travelInfosA = infoMap.get("A");
			  List<TravelInfo> travelInfosB = infoMap.get("B");
			  List<TravelInfo> travelInfosC = infoMap.get("C");
			  List<TravelInfo> travelInfosD = infoMap.get("D");
			  List<TravelInfo> travelInfosE = infoMap.get("E");
			  
			  model.addAttribute("travelInfosA", travelInfosA);
			  model.addAttribute("travelInfosB", travelInfosB);
			  model.addAttribute("travelInfosC", travelInfosC);
			  model.addAttribute("travelInfosD", travelInfosD);
			  model.addAttribute("travelInfosE", travelInfosE);
			  
			  // Service-Store측으로부터 잘 넘어왔는지 테스트
			  System.out.println("컨트롤러측: MAP의 크기: "+infoMap.size());
			  System.out.println("수도권지역 첫번째 여행지 이름: "+travelInfosA.get(0).getInfoName());
			  
			  
			  System.out.println("컨트롤러측: 지역정보 모델까지 출력 완료!!");
			  
		  }
		  
		  catch(Exception e) {
			  System.out.println("컨트롤러측: 플래너로 불러오는 중 오류발생!");
		  }
		  return "project/plan/myplan";
	  }
	  
	  
	  @JsonProperty
	  @RequestMapping(value="insert.do", method=RequestMethod.GET)
	  public @ResponseBody boolean insertDB(@RequestParam("location") String location,
			  @RequestParam("date") String date,
			  @RequestParam("start") String start,
			  @RequestParam("end") String end,
			  @RequestParam("vehicle") String vehicle,
			  @RequestParam("destinationID") String destinationID,
			  @RequestParam("longitude") String longitude,
			  @RequestParam("latitude") String latitude,
			  HttpServletResponse response, HttpSession session,
			  Plan plan) throws Exception 
	  {
		  
		  String loginID = (String)session.getAttribute("loginID");
		  String loginName = (String)session.getAttribute("loginName");
		  
	  
		  System.out.println("location :"+location);
		  System.out.println("date :"+date);
		  System.out.println("start :"+start);
		  System.out.println("end :"+end);
		  System.out.println("vehicle :"+vehicle);
		  
		  plan.setLocation(location);
		  plan.setDate(date);
		  plan.setStartTime(start);
		  plan.setEndTime(end);
		  plan.setVehicle(vehicle);
		  plan.setUserID(loginID);
		  plan.setLatitude(latitude);
		  plan.setLongitude(longitude);
		  
		  try {
			  service.settingPlanner(plan);
			  System.out.println("컨트롤러측: 플래너 저장 성공!");
			  return true;
		  }
		  catch(Exception e){
			  System.out.println("컨트롤러측: 플래너 저장중 오류 발생!");
			  return false;
		  }
	  }
	  
	  
	  @JsonProperty
	  @RequestMapping(value="deletePlan.do", method=RequestMethod.GET)
	  public @ResponseBody boolean deleteDB(@RequestParam("planID") String planID, HttpSession session)
	  {
		  try {
			  service.deletePlanner(planID);
			  System.out.println("컨트롤러측: 플레너 삭제 성공!!");
			  
			  return true;
		  }
		  catch(Exception e)
		  {
			  System.out.println("삭제 중 오류 발생!");
			  
			  return false;
		  }
	  }

  }
  
  
  
 