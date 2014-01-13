package cn.com.bl.dao;

import java.util.List;

import cn.com.bl.bean.ShopBean;

public interface ShopDao {
	/**
	 * 添加商铺
	 * @param shopBean
	 * @return
	 */
	int addShop(ShopBean shopBean);
	
	/**
	 * 删除商铺
	 * @param id 商铺id
	 * @return
	 */
	int deleteShop(String id);
	
	/**
	 * 根性商铺
	 * @param shopBean
	 * @return
	 */
	int updateShop(ShopBean shopBean);

	/**
	 * 根据商铺id查询商铺
	 * @param id 商铺id
	 * @return
	 */
	ShopBean selectShopById(String id);
	
	List<ShopBean> selectAllShopList();
}
