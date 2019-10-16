package project.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import project.domain.Content;
import project.service.CommentService;
import project.service.ContentService;

@Controller
@RequestMapping("content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("main.do")
	public String findAll(Model model) {
		List<Content> contents = contentService.findallContents();
		model.addAttribute("contents",contents);
		
		return "project/content/board";
	}
	
	@RequestMapping(value="regist.do",method=RequestMethod.GET)
	public String regist() {
		return "project/content/boardCreate";
	}
	
	@RequestMapping(value="regist.do", method=RequestMethod.POST)
	public String regist(HttpSession session,Content content) {
		content.setCreatorName((String) session.getAttribute("loginID"));
		contentService.registerContent(content);
		System.out.println(content.getTitle());
		System.out.println(content.getContentText());
		return "redirect:/content/main.do";
	}
	
	@RequestMapping(value="modify.do", method=RequestMethod.GET)
	public String modify(HttpSession session, String contentId,Model model) {
		Content content = contentService.findContent(contentId);
		model.addAttribute("content", content);
		
		session.setAttribute("contentID", contentId);
		
		return "project/content/boardModify";
	}
	
	@RequestMapping(value="modify.do", method=RequestMethod.POST)
	public String modify(Content content, HttpSession session){

		content.setContentId((String)session.getAttribute("contentID"));
		contentService.modifyContent(content);
		return "redirect:/content/main.do";
	}
	@RequestMapping("remove.do")
	public String remove(String contentId) {
		contentService.removeContent(contentId);
		return "redirect:/content/main.do";
	}
	@RequestMapping("find.do")
	public String find(String contentId,Model model,HttpSession session) {
		Content content = contentService.findContent(contentId);
		int viewCount = Integer.parseInt(content.getViewCount());
		viewCount++;
		content.setViewCount(Integer.toString(viewCount));
		contentService.modifyViewCount(content);
		content.setComments(commentService.findallComments(contentId));
		model.addAttribute("content",content);
		session.setAttribute("contentID", contentId);
		
		return "project/content/boardDetail";
	}
	
	@JsonProperty
	@RequestMapping("like.do")
	public @ResponseBody boolean like(@RequestParam("contentID") String contentID,
			HttpServletResponse response)
	{
		try
		{
			contentService.addVote(contentID);
			System.out.println("컨트롤러측: "+contentID+"부분 1증가 성공!!");
			return true;
		}
		catch(Exception e)
		{
			System.out.println("컨트롤러측: 게시판 추천 수 갱신 실패!");
			return false;
		}
	}
	
	@RequestMapping(value = "category.do", method=RequestMethod.GET)
	public String viewCategory(String category, Model model, HttpSession session)
	{
		if (category.equals("A")||category.equals("B")||category.equals("C"))
		{
			List<Content> contents = contentService.findCategories(category);
			model.addAttribute("contents",contents);
			
			
			String categoryName;
			if(category.equals("A"))
			{
				categoryName = "여행 후기 & 팁";
				model.addAttribute("category",categoryName);
			}
			
			else if(category.equals("B"))
			{
				categoryName = "여행지 Q&A";
				model.addAttribute("category",categoryName);
			}
			
			else
			{
				categoryName = "자유게시판";
				model.addAttribute("category",categoryName);
			}
			
			return "project/content/boardCategory";
		}
		else if(category.equals("D"))
		{
			String sessionLoginID = (String)session.getAttribute("loginID");
			List<Content> contents = contentService.findMyContents(sessionLoginID);
			model.addAttribute("contents",contents);
			
			model.addAttribute("category","내가 쓴 글보기");
			return "project/content/boardCategory";
		}
		else
		{
			System.out.println("컨트롤러측: 잘못된 인자값선택");
			
			List<Content> contents = contentService.findallContents();
			model.addAttribute("contents",contents);
			
			return "project/content/board";
		}
		
	}
}
