package project.service.logic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.Pagination;
import project.domain.TravelInfo;
import project.service.TravelInfoService;
import project.store.TravelInfoStore;

@Service
public class TravelInfoServiceLogic implements TravelInfoService{

	
	@Autowired
	private TravelInfoStore infoStore;
	
	@Override
	public String registerInfo(TravelInfo travelInfo) {
		// TODO Auto-generated method stub
		travelInfo.setVoteCount("0");
		//infoStore.create(travelInfo);
		return infoStore.create(travelInfo);
	}

	@Override
	public TravelInfo findInfo(String infoId) {
		// TODO Auto-generated method stub
		return infoStore.retrieve(infoId);
	}

	@Override
	public void removeInfo(String infoId) {
		// TODO Auto-generated method stub
		infoStore.delete(infoId);
		
	}

	@Override
	public void modifyInfo(TravelInfo travelInfo) {
		// TODO Auto-generated method stub
		infoStore.update(travelInfo);
	}

	@Override
	public List<TravelInfo> findAllInfos() {
		
		return  infoStore.retrieveAll();
	}

	@Override
	public List<TravelInfo> findByName(String name) {
		
		return infoStore.readByName(name);
	}
	
	@Override
	public List<TravelInfo> findToIndex() {
		// TODO Auto-generated method stub
		return infoStore.findToIndex();
	}

	@Override
	public void addVote(String infoID) {
		// TODO Auto-generated method stub
		
		infoStore.addVote(infoID);
		
	}

	@Override
	public Map<String, List<TravelInfo>> findToPlanner() {
		// TODO Auto-generated method stub
		return infoStore.findToPlanner();
	}
	@Override
	public List<TravelInfo> findByPage(Pagination pagination) {
		
		return infoStore.readByPage(pagination);
	}

	@Override
	public List<TravelInfo> findByNamePage(Pagination pagination, String name) {
		// TODO Auto-generated method stub
		return infoStore.readByNamePage(pagination,name);
	}

	@Override
	public List<TravelInfo> categoryRetrieveAll(String category) {
		// TODO Auto-generated method stub
		return infoStore.categoryRetrieveAll(category);
	}

	@Override
	public List<TravelInfo> findToLikeLocation(String location) {
		// TODO Auto-generated method stub
		return infoStore.findToLikeLocation(location);
	}
	
}
