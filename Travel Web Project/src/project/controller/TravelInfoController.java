package project.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import project.Pagination;
import project.domain.InfoFile;
import project.domain.TravelInfo;
import project.service.InfoFileService;
import project.service.TravelInfoService;
import project.store.jdbc.S3Util;

@Controller
@RequestMapping("info")
public class TravelInfoController {
	
	@Autowired
	private TravelInfoService infoService;
	@Autowired
	private InfoFileService fileSerivce;
	
	S3Util s3 = new S3Util();
	
	@RequestMapping("main.do")
	public String findAll(@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, Model model) {
		int listCnt = infoService.findAllInfos().size();
		Pagination pagination = new Pagination();
		pagination.pageInfo(page, range, listCnt);
		List<TravelInfo> travelInfos = infoService.findByPage(pagination);
		for (TravelInfo travelinfo : travelInfos) {
			travelinfo.setThumbImg(s3.getFileURL("travelprojectlinkplus", travelinfo.getThumbImg()));
		}
		model.addAttribute("infoName","");
		model.addAttribute("pagination", pagination);
		model.addAttribute("travelInfos", travelInfos);
		return "project/travelInfo/places";
	}
	
	@RequestMapping(value="category.do", method=RequestMethod.GET)
	public String categoryView(@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range,
			String category, Model model)
	{
		List<TravelInfo> infos = infoService.categoryRetrieveAll(category);
		
		for (TravelInfo travelinfo : infos) {
			travelinfo.setThumbImg(s3.getFileURL("travelprojectlinkplus", travelinfo.getThumbImg()));
		}
		
		model.addAttribute("travelInfos", infos);
		return "project/travelInfo/places";
		
	}
	
	
	@RequestMapping(value="regist.do", method=RequestMethod.GET)
	public String regist() {
		return "project/travelInfo/placeCreate";
	}
	
	@RequestMapping(value="regist.do",method=RequestMethod.POST)
	public String regist(TravelInfo travelInfo,
			@RequestParam("uploadFile") List<MultipartFile> multipartFiles) throws Exception {
		
		String infoId = infoService.registerInfo(travelInfo);

		if(!multipartFiles.isEmpty()) {
				fileSerivce.uploadInfoFile(multipartFiles ,infoId);
		}
		TravelInfo info = infoService.findInfo(infoId);
		info.setThumbImg(fileSerivce.findallInfoFiles(infoId).get(0).getFilePath());
		infoService.modifyInfo(info);
		return "redirect:/info/main.do";
	}
	
	@RequestMapping("find.do")
	public String find(String infoId,Model model) {	
		TravelInfo travelInfo = infoService.findInfo(infoId);
		List<InfoFile> infoFiles = fileSerivce.findallInfoFiles(infoId);
		for (InfoFile infoFile : infoFiles) {
			infoFile.setFilePath(s3.getFileURL("travelprojectlinkplus", infoFile.getFilePath()));
		}
		model.addAttribute("travelInfo", travelInfo);
		model.addAttribute("infoFiles", infoFiles);
		
		
		
		List<TravelInfo> travelInfos = new ArrayList<TravelInfo>();
		travelInfos = infoService.findToLikeLocation(travelInfo.getLocationID());
		
		for (TravelInfo travelinfo : travelInfos) {
			travelinfo.setThumbImg(s3.getFileURL("travelprojectlinkplus", travelinfo.getThumbImg()));
		}
		
		model.addAttribute("infos", travelInfos);
		
		
		return "project/travelInfo/placeDetail";
	}
	@RequestMapping("remove.do")
	public String remove(String infoId) {	
		infoService.removeInfo(infoId);
		fileSerivce.deleteInfoFile(infoId);
		return "redirect:/info/main.do";
	}
	
	
	@RequestMapping(value="search.do", method=RequestMethod.GET)
	public String search( @RequestParam("infoName")String infoName , @RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int range, Model model) {
		System.out.println("infoname"+infoName);
		int listCnt = infoService.findByName(infoName).size();
		Pagination pagination = new Pagination();
		pagination.pageInfo(page, range, listCnt);
		List<TravelInfo> travelInfos = infoService.findByNamePage(pagination, infoName);
		for (TravelInfo travelinfo : travelInfos) {
			travelinfo.setThumbImg(s3.getFileURL("travelprojectlinkplus", travelinfo.getThumbImg()));
		}
		model.addAttribute("infoName",infoName);
		model.addAttribute("pagination", pagination);
		model.addAttribute("travelInfos", travelInfos);
		return "project/travelInfo/places";
	}
	
	@JsonProperty
	@RequestMapping("like.do")
	public @ResponseBody boolean like(@RequestParam("infoID") String infoID)
	{
		try
		{
			infoService.addVote(infoID);
			System.out.println("컨트롤러측: "+infoID+"부분 1증가 성공!!");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("컨트롤러측: 여행지정보 추천 수 갱신 실패!");
			return false;
		}

	}
}
