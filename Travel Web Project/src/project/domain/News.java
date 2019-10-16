package project.domain;

import java.util.ArrayList;
import java.util.List;

import project.service.NewsService;
 
public class News {

    final String title; // 타이틀
    final String link; // 링크의 url 
    final String description; // 설명 
    final String language; // RSS의 언어정보 
    final String copyright; // RSS의 저작권정보 
    final String pubDate; // RSS의 게시일자 
 
    final List<NewsService> entries = new ArrayList<NewsService>();
 
    public News(String title, String link, String description, String language,
                    String copyright, String pubDate) {
            this.title = title;
            this.link = link;
            this.description = description;
            this.language = language;
            this.copyright = copyright;
            this.pubDate = pubDate;
    }
 
    public List<NewsService> getMessages() {
            return entries;
    }
 
    public String getTitle() {
            return title;
    }
 
    public String getLink() {
            return link;
    }
 
    public String getDescription() {
            return description;
    }
 
    public String getLanguage() {
            return language;
    }
 
    public String getCopyright() {
            return copyright;
    }
 
    public String getPubDate() {
            return pubDate;
    }
 
    //콘솔창 첫화면
    @Override
    public String toString() {
            return "Feed [copyright=" + copyright + ", description=" + description
                            + ", language=" + language + ", link=" + link + ", pubDate="
                            + pubDate + ", title=" + title + "]";
    }
 
}
