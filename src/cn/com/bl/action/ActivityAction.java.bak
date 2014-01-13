package cn.com.bl.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONObject;
import cn.com.bl.bean.ActivityBean;
import cn.com.bl.dao.ActivityDao;

import com.opensymphony.xwork2.ActionSupport;

public class ActivityAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6581113632553897218L;
	private ActivityDao activityDao;
	private ActivityBean activityBean;
	private List<ActivityBean> activityList;
	private String activityName;
	private JSONObject jsonValue;
	private File activityFile; // 上传的文件
	private String activityFileFileName; // 文件名称
	private String activityFileContentType; // 文件类型
	
	// 添加活动
		public String addActivity() {
			Map result = new HashMap();
			if (null == activityBean) {
				result.put("result", false);
				result.put("cause", "活动为空!");
				this.setJsonValue(JSONObject.fromObject(result));
				return SUCCESS;
			}
			String activityId = UUID.randomUUID().toString().replace("-", "");
			activityBean.setId(activityId);
			// 添加商铺图片
			if (activityFile != null) {
				FileOutputStream fos = null;
				FileInputStream fis = null;
				try {
					// 建立文件输出流
					String realPaht = ServletActionContext.getServletContext()
							.getRealPath("/file");
					String realName = activityId
							+ getActivityFileFileName().substring(
									activityFileFileName.lastIndexOf("."));
					File file = new File(realPaht + "\\" + realName);
					if (!file.getParentFile().exists()) {
						file.mkdirs();
					}
					file.createNewFile();
					fos = new FileOutputStream(realPaht + "\\" + realName);
					// 建立文件上传流
					fis = new FileInputStream(getActivityFile());
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					activityFile = null;
					activityBean.setActivityUrl("file/".concat(realName));
				} catch (Exception e) {
					result.put("result", false);
					result.put("cause", "添加活动 出错!");
					this.setJsonValue(JSONObject.fromObject(result));
					return SUCCESS;
				} finally {
					close(fos, fis);
				}
			}
			int c = activityDao.addActivity(activityBean);
			if (c < 1) {
				result.put("result", false);
				result.put("cause", "添加商铺出错!");
				this.setJsonValue(JSONObject.fromObject(result));
				return SUCCESS;
			}
			result.put("result", true);
			JSONObject j = JSONObject.fromObject(result);
			this.setJsonValue(JSONObject.fromObject(result));
			return SUCCESS;
		}
	
		// 修改活动
				public String updateActivity() {
					Map result = new HashMap();
					if (null == activityBean) {
						result.put("result", false);
						result.put("cause", "商铺为空!");
						this.setJsonValue(JSONObject.fromObject(result));
						return SUCCESS;
					}
					// 添加活动图片
					if (activityFile != null) {
						FileOutputStream fos = null;
						FileInputStream fis = null;
						try {
							// 建立文件输出流
							String realPaht = ServletActionContext.getServletContext()
									.getRealPath("/file");
							String realName = activityBean.getId()
									+ getActivityFileFileName().substring(
											activityFileFileName.lastIndexOf("."));
							File file = new File(realPaht + "\\" + realName);
							if (!file.getParentFile().exists()) {
								file.mkdirs();
							}
							if(file.exists())file.delete();
							file.createNewFile();
							fos = new FileOutputStream(realPaht + "\\" + realName);
							// 建立文件上传流
							fis = new FileInputStream(getActivityFile());
							byte[] buffer = new byte[1024];
							int len = 0;
							while ((len = fis.read(buffer)) > 0) {
								fos.write(buffer, 0, len);
							}
							activityFile = null;
							activityBean.setActivityUrl("file/".concat(realName));
						} catch (Exception e) {
							result.put("result", false);
							result.put("cause", "添加商铺出错!");
							this.setJsonValue(JSONObject.fromObject(result));
							return SUCCESS;
						} finally {
							close(fos, fis);
						}
					}
					activityBean.setActivityCreatedate(new Date());
					int c = activityDao.updateActivity(activityBean);
					if (c < 1) {
						result.put("result", false);
						result.put("cause", "修改商铺出错!");
						this.setJsonValue(JSONObject.fromObject(result));
						return SUCCESS;
					}
					result.put("result", true);
					JSONObject j = JSONObject.fromObject(result);
					this.setJsonValue(JSONObject.fromObject(result));
					return SUCCESS;
				}
			
				// 返回所有活动json串
				public String activityJsonData() {
					activityList = activityDao.selectAllActivityList();
					return SUCCESS;

				}

				// 删除活动
				public String deleteActivity() {
					Map result = new HashMap();
					if (null == activityBean || null == activityBean.getId()) {
						result.put("result", false);
						result.put("cause", "商铺id为空!");
						this.setJsonValue(JSONObject.fromObject(result));
						return SUCCESS;
					}
					activityDao.deleteActivity(activityBean.getId());
					result.put("result", true);
					JSONObject j = JSONObject.fromObject(result);
					this.setJsonValue(JSONObject.fromObject(result));
					return SUCCESS;
				}
	
	public ActivityBean getActivityBean() {
		return activityBean;
	}
	public void setActivityBean(ActivityBean activityBean) {
		this.activityBean = activityBean;
	}
	public List<ActivityBean> getActivityList() {
		return activityList;
	}
	public void setActivityList(List<ActivityBean> activityList) {
		this.activityList = activityList;
	}
	public JSONObject getJsonValue() {
		return jsonValue;
	}
	public void setJsonValue(JSONObject jsonValue) {
		this.jsonValue = jsonValue;
	}
	public File getActivityFile() {
		return activityFile;
	}
	public void setActivityFile(File activityFile) {
		this.activityFile = activityFile;
	}
	public String getActivityFileFileName() {
		return activityFileFileName;
	}
	public void setActivityFileFileName(String activityFileFileName) {
		this.activityFileFileName = activityFileFileName;
	}
	public String getActivityFileContentType() {
		return activityFileContentType;
	}
	public void setActivityFileContentType(String activityFileContentType) {
		this.activityFileContentType = activityFileContentType;
	}
	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

		// 关闭流
		private void close(FileOutputStream fos, FileInputStream fis) {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println("FileInputStream关闭失败");
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					System.out.println("FileOutputStream关闭失败");
					e.printStackTrace();
				}
			}
		}

}
