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

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.com.bl.bean.ShopBean;
import cn.com.bl.dao.ShopDao;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 商铺action
 * 
 * @author lism
 * 
 */
public class ShopAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8864865162568302528L;

	private ShopDao shopDao;
	private ShopBean shopBean;
	private List<ShopBean> shopList;
	private JSONObject jsonValue;
	private File shopFile; // 上传的文件
	private String shopFileFileName; // 文件名称
	private String shopFileContentType; // 文件类型
	private String shopName;// 商铺名称
	private String shopType;// 商铺类型
	private String shopFloor;// 商铺楼层

	// 添加商铺
	public String addShop() {
		Map result = new HashMap();
		if (null == shopBean) {
			result.put("result", false);
			result.put("cause", "商铺为空!");
			this.setJsonValue(JSONObject.fromObject(result));
			return SUCCESS;
		}
		String shopId = UUID.randomUUID().toString().replace("-", "");
		shopBean.setId(shopId);
		// 添加商铺图片
		if (shopFile != null) {
			FileOutputStream fos = null;
			FileInputStream fis = null;
			try {
				// 建立文件输出流
				String realPaht = ServletActionContext.getServletContext()
						.getRealPath("/file");
				String realName = shopId
						+ getShopFileFileName().substring(
								shopFileFileName.lastIndexOf("."));
				File file = new File(realPaht + "\\" + realName);
				if (!file.getParentFile().exists()) {
					file.mkdirs();
				}
				file.createNewFile();
				fos = new FileOutputStream(realPaht + "\\" + realName);
				// 建立文件上传流
				fis = new FileInputStream(getShopFile());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				shopFile = null;
				shopBean.setShopUrl("file/".concat(realName));
			} catch (Exception e) {
				result.put("result", false);
				result.put("cause", "添加商铺出错!");
				this.setJsonValue(JSONObject.fromObject(result));
				return SUCCESS;
			} finally {
				close(fos, fis);
			}
		}
		shopBean.setShopCreatedate(new Date());
		int c = shopDao.addShop(shopBean);
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

	// 返回所有商铺json串
	public String shopJsonData() {
		shopList = shopDao.selectAllShopList();
		return SUCCESS;

	}

	// 删除商铺
	public String deleteShop() {
		Map result = new HashMap();
		if (null == shopBean || null == shopBean.getId()) {
			result.put("result", false);
			result.put("cause", "商铺id为空!");
			this.setJsonValue(JSONObject.fromObject(result));
			return SUCCESS;
		}
		shopDao.deleteShop(shopBean.getId());
		result.put("result", true);
		JSONObject j = JSONObject.fromObject(result);
		this.setJsonValue(JSONObject.fromObject(result));
		return SUCCESS;
	}

	public void setShopDao(ShopDao shopDao) {
		this.shopDao = shopDao;
	}

	
	// 添加商铺
		public String updateShop() {
			Map result = new HashMap();
			if (null == shopBean) {
				result.put("result", false);
				result.put("cause", "商铺为空!");
				this.setJsonValue(JSONObject.fromObject(result));
				return SUCCESS;
			}
			// 添加商铺图片
			if (shopFile != null) {
				FileOutputStream fos = null;
				FileInputStream fis = null;
				try {
					// 建立文件输出流
					String realPaht = ServletActionContext.getServletContext()
							.getRealPath("/file");
					String realName = shopBean.getId()
							+ getShopFileFileName().substring(
									shopFileFileName.lastIndexOf("."));
					File file = new File(realPaht + "\\" + realName);
					if (!file.getParentFile().exists()) {
						file.mkdirs();
					}
					if(file.exists())file.delete();
					file.createNewFile();
					fos = new FileOutputStream(realPaht + "\\" + realName);
					// 建立文件上传流
					fis = new FileInputStream(getShopFile());
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					shopFile = null;
					shopBean.setShopUrl("file/".concat(realName));
				} catch (Exception e) {
					result.put("result", false);
					result.put("cause", "添加商铺出错!");
					this.setJsonValue(JSONObject.fromObject(result));
					return SUCCESS;
				} finally {
					close(fos, fis);
				}
			}
			int c = shopDao.updateShop(shopBean);
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
	
	public ShopBean getShopBean() {
		return shopBean;
	}

	public void setShopBean(ShopBean shopBean) {
		this.shopBean = shopBean;
	}

	public List<ShopBean> getShopList() {
		return shopList;
	}

	public void setShopList(List<ShopBean> shopList) {
		this.shopList = shopList;
	}

	public JSONObject getJsonValue() {
		return jsonValue;
	}

	public void setJsonValue(JSONObject jsonValue) {
		this.jsonValue = jsonValue;
	}

	public File getShopFile() {
		return shopFile;
	}

	public void setShopFile(File shopFile) {
		this.shopFile = shopFile;
	}

	public String getShopFileFileName() {
		return shopFileFileName;
	}

	public void setShopFileFileName(String shopFileFileName) {
		this.shopFileFileName = shopFileFileName;
	}

	public String getShopFileContentType() {
		return shopFileContentType;
	}

	public void setShopFileContentType(String shopFileContentType) {
		this.shopFileContentType = shopFileContentType;
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

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getShopFloor() {
		return shopFloor;
	}

	public void setShopFloor(String shopFloor) {
		this.shopFloor = shopFloor;
	}

}
