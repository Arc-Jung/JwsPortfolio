package project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import project.domain.Comment;
import project.service.CommentService;

@Repository
@RequestMapping("comment")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@RequestMapping("regist.do")
	public String regist(HttpSession session,Comment comment,Model model) {
		System.out.println((String) session.getAttribute("loginID"));
		comment.setCreatorName((String) session.getAttribute("loginID"));
		commentService.registerComment(comment);
		model.addAttribute("contentId",comment.getContentId());
		return "redirect:/content/find.do";
	}
	
	@RequestMapping("remove.do")
	public String remove(String contentId,String commentId,Model model) {
		model.addAttribute("contentId",contentId);
		commentService.removeComment(commentId);
		return "redirect:/content/find.do";
	}
}
