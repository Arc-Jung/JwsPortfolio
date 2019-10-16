package project.store.logic;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
 
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import org.springframework.beans.factory.annotation.Autowired;

import project.domain.News;
import project.service.NewsService;
import project.store.RSSParce;
 
public class RSSParceLogic implements RSSParce
{
        static final String TITLE = "title";
        static final String DESCRIPTION = "description";
        static final String CHANNEL = "channel";
        static final String LANGUAGE = "language";
        static final String COPYRIGHT = "copyright";
        static final String LINK = "link";
        static final String AUTHOR = "author";
        static final String ITEM = "item";
        static final String PUB_DATE = "pubDate";
        static final String GUID = "guid";
        static final String PUBDATE ="pubdate";
        
        final URL url;
        
        @Autowired
        public RSSParceLogic(String feedUrl) {
            try {
                    this.url = new URL(feedUrl);
            } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
            }
    }
            
		@Override
		public News readFeed() {
			// TODO Auto-generated method stub
			News news = null;
            try {
                    boolean isFeedHeader = true;
                    // Set header values intial to the empty string
                    String description = "";
                    String title = "";
                    String link = "";
                    String language = "";
                    String copyright = "";
                    String author = "";
                    String pubdate = "";
                    String guid = "";

                    // First create a new XMLInputFactory
                    XMLInputFactory inputFactory = XMLInputFactory.newInstance();
                    // Setup a new eventReader
                    InputStream in = read();
                    XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
                    // read the XML document
                    while (eventReader.hasNext()) {
                            XMLEvent event = eventReader.nextEvent();
                            if (event.isStartElement()) {
                                    String localPart = event.asStartElement().getName()
                                                    .getLocalPart();
                                    switch (localPart) {
                                    case ITEM:
                                            if (isFeedHeader) {
                                                    isFeedHeader = false;
                                                    news = new News(title, link, description, language,
                                                                    copyright, pubdate);
                                            }
                                            event = eventReader.nextEvent();
                                            break;
                                    case TITLE:
                                            title = getCharacterData(event, eventReader);
                                            break;
                                    case DESCRIPTION:
                                            description = getCharacterData(event, eventReader);
                                            break;
                                    case LINK:
                                            link = getCharacterData(event, eventReader);
                                            break;
                                    case GUID:
                                            guid = getCharacterData(event, eventReader);
                                            break;
                                    case LANGUAGE:
                                            language = getCharacterData(event, eventReader);
                                            break;
                                    case AUTHOR:
                                            author = getCharacterData(event, eventReader);
                                            break;
                                    case PUB_DATE:
                                            pubdate = getCharacterData(event, eventReader);
                                            break;
                                    case COPYRIGHT:
                                            copyright = getCharacterData(event, eventReader);
                                            break;
                                    case PUBDATE:
                                        pubdate = getCharacterData(event, eventReader);
                                        break;
                                            
                                    }
                            } else if (event.isEndElement()) {
                                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
                                            NewsService message = new NewsService();
                                            message.setAuthor(author);
                                            message.setDescription(description);
                                            message.setGuid(guid);
                                            message.setLink(link);
                                            message.setTitle(title);
                                            message.setPubdate(pubdate);
                                            news.getMessages().add(message);
                                            event = eventReader.nextEvent();
                                            continue;
                                    }
                            }
                    }
            } catch (XMLStreamException e) {
                    throw new RuntimeException(e);
            }
            return news;

		}
		
		
		@Override
		public String getCharacterData(XMLEvent event, XMLEventReader eventReader)
		{
			try {
				String result = "";
				event = eventReader.nextEvent();
				if (event instanceof Characters) {
					result = event.asCharacters().getData();
				}
				return result;
			}
			catch(XMLStreamException e)
			{
				System.out.println("XML파싱 오류발생 ");
			}
			return null;
        }
		
		
		@Override
		public InputStream read() {
			// TODO Auto-generated method stub
			
			try {
                return url.openStream();
			} 
			catch (IOException e) {
                throw new RuntimeException(e);
			}
		}
}
