package cn.com.bl.bean;

import java.io.Serializable;
import java.util.Date;

public class ShopBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8155207311949614296L;
	private String id;//主键
	private String shopName;//商铺名称
	private String shopDesc;//商铺简介
	private String shopUrl;//商铺图片地址
	private String shopFloor;//商铺楼层
	private String shopType;//商铺种类
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopDesc() {
		return shopDesc;
	}
	public void setShopDesc(String shopDesc) {
		this.shopDesc = shopDesc;
	}
	public String getShopUrl() {
		return shopUrl;
	}
	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}
	public String getShopFloor() {
		return shopFloor;
	}
	public void setShopFloor(String shopFloor) {
		this.shopFloor = shopFloor;
	}
	public String getShopType() {
		return shopType;
	}
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	public Date getShopCreatedate() {
		return shopCreatedate;
	}
	public void setShopCreatedate(Date shopCreatedate) {
		this.shopCreatedate = shopCreatedate;
	}
	private Date shopCreatedate;//商铺楼层

}
