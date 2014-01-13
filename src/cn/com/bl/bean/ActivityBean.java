package cn.com.bl.bean;

import java.io.Serializable;
import java.util.Date;

public class ActivityBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8155207311949614296L;
	private String id;//主键
	private String activityName;//活动名称
	private String activityDesc;//活动简介
	private String activityUrl;//活动图片地址
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityDesc() {
		return activityDesc;
	}
	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	public String getActivityUrl() {
		return activityUrl;
	}
	public void setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
	}
	public Date getActivityCreatedate() {
		return activityCreatedate;
	}
	public void setActivityCreatedate(Date activityCreatedate) {
		this.activityCreatedate = activityCreatedate;
	}
	private Date activityCreatedate;//活动创建日期

}
