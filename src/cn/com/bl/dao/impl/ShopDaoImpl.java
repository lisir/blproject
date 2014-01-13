package cn.com.bl.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import cn.com.bl.bean.ShopBean;
import cn.com.bl.dao.ShopDao;

public class ShopDaoImpl implements ShopDao{
	private SqlMapClient sqlMapClient = null;

	@Override
	public int addShop(ShopBean shopBean) {
		// TODO Auto-generated method stub
		try {
			 sqlMapClient.insert("shop.addShop",shopBean);
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
	}

	@Override
	public int deleteShop(String id) {
		// TODO Auto-generated method stub
		try {
			 sqlMapClient.delete("shop.deleteShop",id);
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
	}

	@Override
	public int updateShop(ShopBean shopBean) {
		// TODO Auto-generated method stub
		try {
			 sqlMapClient.update("shop.updateShop",shopBean);
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
	}

	@Override
	public ShopBean selectShopById(String id) {
		// TODO Auto-generated method stub
		try {
			return (ShopBean)sqlMapClient.queryForObject("shop.selectShopById",id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public List<ShopBean> selectAllShopList() {
		// TODO Auto-generated method stub
		try {
			return sqlMapClient.queryForList("shop.selectAllShopList");
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
