package project.service;

import java.util.List;
import java.util.Map;

import project.Pagination;
import project.domain.TravelInfo;

public interface TravelInfoService {
	
	String registerInfo(TravelInfo travelInfo);
	
	TravelInfo findInfo(String infoId);
	
	void removeInfo(String infoId);
	
	void modifyInfo(TravelInfo travelInfo);
	
	List<TravelInfo> findByName(String name);
	
	List<TravelInfo> findAllInfos();
	
	List<TravelInfo> findToIndex();
	List<TravelInfo> findByPage(Pagination pagination);
	List<TravelInfo> findByNamePage(Pagination pagination, String name);
	List<TravelInfo> findToLikeLocation(String location);
	void addVote(String infoID);
	
	//플래너로 부르기 위한 액션
	Map<String, List<TravelInfo>> findToPlanner();
	
	List<TravelInfo> categoryRetrieveAll(String category);
}
