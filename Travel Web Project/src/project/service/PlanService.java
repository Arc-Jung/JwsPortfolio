package project.service;

import java.util.List;
import java.util.Map;

import project.domain.Plan;

public interface PlanService {
	
	public void settingPlanner(Plan plan) throws Exception;
	
	public List<Plan> showAllPlanner(String userID) throws Exception;
	
	public void deletePlanner(String planID) throws Exception;
}
