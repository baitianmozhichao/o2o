package com.mzc.o2o.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mzc.o2o.entity.Shop;
import com.mzc.o2o.vo.ShopVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Description:
 * @Auther: mzc
 * @Date: 2019/3/31 19:15
 */
public interface ShopDao extends BaseMapper<Shop> {

    @Select("SELECT\n" +
            "\tshop.*,personinfo.name,area.area_name,shopcat.shop_category_name,shopcat_p.shop_category_name as parent_category_name\n" +
            "FROM\n" +
            "\ttb_shop shop\n" +
            "\tLEFT JOIN tb_person_info personinfo ON shop.owner_id = personinfo.user_id \n" +
            "\tLEFT JOIN tb_area area ON shop.area_id = area.area_id\n" +
            "\tLEFT JOIN tb_shop_category shopcat ON shop.shop_category_id = shopcat.shop_category_id\n" +
            "\tLEFT JOIN tb_shop_category shopcat_p ON shop.parent_category_id = shopcat_p.shop_category_id\n" +
            "WHERE\n" +
            "\tshop.shop_id = ${shopId}")
    ShopVo queryShopWithName(@Param("shopId") Integer shopId);
}
