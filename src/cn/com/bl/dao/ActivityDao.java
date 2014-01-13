package cn.com.bl.dao;

import java.util.List;

import cn.com.bl.bean.ActivityBean;

public interface ActivityDao {
	/**
	 * 添加活动
	 * @param activityBean
	 * @return
	 */
	int addActivity(ActivityBean activityBean);
	
	/**
	 * 删除活动
	 * @param id 活动id
	 * @return
	 */
	int deleteActivity(String id);
	
	/**
	 * 根性活动
	 * @param activityBean
	 * @return
	 */
	int updateActivity(ActivityBean activityBean);

	/**
	 * 根据活动id查询活动
	 * @param id 活动id
	 * @return
	 */
	ActivityBean selectActivityById(String id);
	
	/**
	 * 查询所有的活动
	 */
	List<ActivityBean> selectAllActivityList();
}
