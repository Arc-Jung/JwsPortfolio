package project.store;

import java.io.InputStream;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.events.XMLEvent;

import project.domain.News;

public interface RSSParce {
	abstract News readFeed();
	abstract String getCharacterData(XMLEvent event, XMLEventReader eventReader);
	abstract InputStream read();

}
