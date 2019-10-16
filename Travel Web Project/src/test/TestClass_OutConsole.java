package test;

import project.domain.News;
import project.service.NewsService;
import project.store.logic.RSSParceLogic;

public class TestClass_OutConsole {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    
	    RSSParceLogic parser = new RSSParceLogic(
	            "http://rss.donga.com/travel.xml");
	    News news = parser.readFeed();
	    System.out.println(news);
	    	for (NewsService message : news.getMessages()) {
	    System.out.println(message);

	}
	}
}

