package project.store;

import java.util.List;
import java.util.Map;

import project.domain.Plan;

public interface PlanStore {
	
	public void settingPlanner(Plan plan);
	
	public List<Plan> showAllPlanner(String UserID);
	
	public void deletePlanner(String planID);

}
