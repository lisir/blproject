package cn.com.bl.bean;

import java.io.Serializable;
import java.util.Date;

public class Discount implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1980084870888452786L;

	private String id;//主键
	private String discountNumber;//优惠劵劵号
	private String discountUrl;//优惠劵图片地址
	private String discountDesc;//优惠劵描述
	private Date discountCreatedate;//优惠劵创建日期
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDiscountNumber() {
		return discountNumber;
	}
	public void setDiscountNumber(String discountNumber) {
		this.discountNumber = discountNumber;
	}
	public String getDiscountUrl() {
		return discountUrl;
	}
	public void setDiscountUrl(String discountUrl) {
		this.discountUrl = discountUrl;
	}
	public String getDiscountDesc() {
		return discountDesc;
	}
	public void setDiscountDesc(String discountDesc) {
		this.discountDesc = discountDesc;
	}
	public Date getDiscountCreatedate() {
		return discountCreatedate;
	}
	public void setDiscountCreatedate(Date discountCreatedate) {
		this.discountCreatedate = discountCreatedate;
	}
}
