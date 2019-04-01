package com.mzc.o2o.dao;

import com.mzc.o2o.BaseTest;
import com.mzc.o2o.entity.PersonInfo;
import com.mzc.o2o.entity.Shop;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/31 19:50
 */
public class ShopDaoTest extends BaseTest {

    @Autowired
    private ShopDao shopDao;
    @Test
    public void addShop() {
        Shop shop = new Shop();
        shop.setShopName("测试");
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(10L);
        shop.setOwner(personInfo);
        shop.setEnableStatus(1);
        shopDao.addShop(shop);
        System.out.println(shop.getShopId());
    }
    @Test
    public void updateShop() {
        Shop shop = new Shop();
        shop.setShopId(31L);
        shop.setShopName("测试==");
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(10L);
        shop.setOwner(personInfo);
        shop.setEnableStatus(0);
        shopDao.updateShop(shop);
        System.out.println(shop.getShopId());
    }
}