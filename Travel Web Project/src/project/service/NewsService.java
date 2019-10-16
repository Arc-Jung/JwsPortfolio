package project.service;

import org.springframework.stereotype.Service;

@Service
public class NewsService {
    
    /**
     * 아이템 이름
     */
     String title;
     
     /**
      * 아이템 설명
      */
     String description;
     
     /**
      * 아이템 link url
      */
     String link;
     
     /**
      * 아이템 작성장
      */
     String author;
     
     /**
      * 아이템 기본키 개념의 유일한 아이디값
      */
     String guid;
     
     /**
      * 작성시간
      */
     String pubdate;
 
     public String getTitle() {
             return title;
     }
 
     public void setTitle(String title) {
             this.title = title;
     }
 
     public String getDescription() {
             return description;
     }
 
     public void setDescription(String description) {
             this.description = description;
     }
 
     public String getLink() {
             return link;
     }
 
     public void setLink(String link) {
             this.link = link;
     }
 
     public String getAuthor() {
             return author;
     }
 
     public void setAuthor(String author) {
             this.author = author;
     }
 
     public String getGuid() {
             return guid;
     }
 
     public void setGuid(String guid) {
             this.guid = guid;
     }
 
     public String getPubdate() {
          return pubdate;
     }
 
     public void setPubdate(String pubdate) {
         this.pubdate = pubdate;
     }
 
    @Override
    public String toString() {
        return "FeedMessage [title=" + title + ", description=" + description + ", link=" + link + ", author=" + author
                + ", guid=" + guid + ", pubdate=" + pubdate + "]";
    }

}