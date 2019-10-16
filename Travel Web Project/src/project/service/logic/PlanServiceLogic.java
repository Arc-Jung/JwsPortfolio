package project.service.logic;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Plan;
import project.service.PlanService;
import project.store.PlanStore;

@Service
public class PlanServiceLogic implements PlanService{

	@Autowired
	private PlanStore store;
	
	@Override
	public void settingPlanner(Plan plan) {
		// TODO Auto-generated method stub
		store.settingPlanner(plan);
		
	}


	@Override
	public List<Plan> showAllPlanner(String userID) throws Exception {
		// TODO Auto-generated method stub
		return store.showAllPlanner(userID);
	}


	@Override
	public void deletePlanner(String planID) throws Exception {
		// TODO Auto-generated method stub
		
		store.deletePlanner(planID);
	}
}


