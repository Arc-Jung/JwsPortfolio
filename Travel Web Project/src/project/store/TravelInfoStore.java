package project.store;

import java.util.List;
import java.util.Map;

import project.Pagination;
import project.domain.TravelInfo;

public interface TravelInfoStore {
	String create(TravelInfo travelInfo);
	TravelInfo retrieve(String infoId);
	void update(TravelInfo travelInfo);
	void delete(String infoId);
	List<TravelInfo> readByName(String name);
	List<TravelInfo> retrieveAll();
	List<TravelInfo> readByPage(Pagination pagination);
	List<TravelInfo> readByNamePage(Pagination pagination, String name);
	//인덱스 페이지로 출력되는 액션
	List<TravelInfo> findToIndex();
	
	//추천버튼에 대한 액션
	void addVote(String infoID);
	
	//플래너로 부르기 위한 액션
	public Map<String, List<TravelInfo>> findToPlanner();
	
	List<TravelInfo> categoryRetrieveAll(String category);
	
	List<TravelInfo> findToLikeLocation(String location);
}
