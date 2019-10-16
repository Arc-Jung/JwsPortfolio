package project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import project.domain.Content;
import project.domain.News;
import project.domain.TravelInfo;
import project.service.ContentService;
import project.service.NewsService;
import project.service.TravelInfoService;
import project.store.jdbc.S3Util;
import project.store.logic.RSSParceLogic;

@Controller
@RequestMapping("project")
public class NewsController {
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private TravelInfoService infoService;
	
	S3Util s3 = new S3Util();

	// 인덱스 페이지 뉴스 호출
	// 추천수 상위 4개 여행지 호출
	// 그리고 리뷰 호출
	@RequestMapping(value="index.do", method=RequestMethod.GET)
	public String news(Model model)
	{
		// 뉴스 파싱하는 로직
		RSSParceLogic parser;
		parser = new RSSParceLogic("http://rss.donga.com/travel.xml");
		News news = parser.readFeed();
	
		List<NewsService> receiveFeed = new ArrayList<NewsService>();
		
		int listIndex = 0;
		
		for(NewsService message:news.getMessages())
		{		
			receiveFeed.add(listIndex, message);
		}
		
		model.addAttribute("news", receiveFeed);		
		
		
		// 여행지 정보 상위 4개 출력하는 부분
		List<TravelInfo> travelInfos = new ArrayList<TravelInfo>();
		travelInfos = infoService.findToIndex();
		
		for (TravelInfo travelinfo : travelInfos) {
			travelinfo.setThumbImg(s3.getFileURL("travelprojectlinkplus", travelinfo.getThumbImg()));
		}
		
		model.addAttribute("infos", travelInfos);
		
		
		// 여행지 리뷰 출력하는 부분 (6개정도)
		List<Content> contents = new ArrayList<Content>();
		contents = contentService.toIndexContents();
		
		model.addAttribute("contents", contents);
		
		return "project/index";
		
	}
}
