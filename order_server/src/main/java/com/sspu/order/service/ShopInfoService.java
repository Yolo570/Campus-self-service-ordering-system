package com.sspu.order.service;

import com.sspu.order.dao.ShopInfoDao;
import com.sspu.order.po.ShopInfo;
import com.sspu.order.po.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopInfoService {
    @Autowired
    private ShopInfoDao shopInfoDao;

    //查询所有店铺信息
    public List<ShopInfo> findAllShops() {
        return shopInfoDao.findAll();
    }

    //添加店铺信息
    public ShopInfo addShopInfo(ShopInfo shopInfo) {
        return shopInfoDao.save(shopInfo);
    }

    //更改店铺信息
    public ShopInfo updateShopInfo(String shop_name) {
        return shopInfoDao.updateShopInfo(shop_name);
    }


    //删除店铺信息
    public void deleteShopInfo(int shop_id) {
        shopInfoDao.deleteById(shop_id);
    }

    //根据店铺名称查询
    public ShopInfo findShopInfoByShop_name(String shop_name) {
        return shopInfoDao.findShopInfoByShop_name(shop_name);
    }

    public ShopInfo getOne(int id) {
        return shopInfoDao.getOne(id);
    }

}
