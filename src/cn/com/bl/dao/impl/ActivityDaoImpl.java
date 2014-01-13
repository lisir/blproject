package cn.com.bl.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cn.com.bl.bean.ActivityBean;
import cn.com.bl.dao.ActivityDao;

public class ActivityDaoImpl implements ActivityDao{
	private SqlMapClient sqlMapClient = null;

	@Override
	public int addActivity(ActivityBean activityBean) {
		// TODO Auto-generated method stub
		try {
			 sqlMapClient.insert("activity.addActivity",activityBean);
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
	}

	@Override
	public int deleteActivity(String id) {
		// TODO Auto-generated method stub
		try {
			 sqlMapClient.delete("activity.deleteActivity",id);
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
	}

	@Override
	public int updateActivity(ActivityBean activityBean) {
		// TODO Auto-generated method stub
		try {
			 sqlMapClient.update("activity.updateActivity",activityBean);
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
	}

	@Override
	public ActivityBean selectActivityById(String id) {
		// TODO Auto-generated method stub
		try {
			return (ActivityBean)sqlMapClient.queryForObject("activity.selectActivityById",id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public List<ActivityBean> selectAllActivityList() {
		// TODO Auto-generated method stub
		try {
			return sqlMapClient.queryForList("activity.selectAllActivityList");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public SqlMapClient getSqlMapClient() {
		return sqlMapClient;
	}

	public void setSqlMapClient(SqlMapClient sqlMapClient) {
		this.sqlMapClient = sqlMapClient;
	}

	

}
