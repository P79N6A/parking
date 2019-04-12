/*
package com.zhuyitech.parking.pms;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dubbo.starter.QuickDubboBootstrap;
import com.scapegoat.infrastructure.dubbo.test.anotation.ScapegoatDubboApplicationConfiguration;
import com.scapegoat.infrastructure.httpclient.utils.HttpClientUtils;
import com.zhuyitech.parking.pms.domain.CarBrand;
import com.zhuyitech.parking.pms.service.CarBrandCrudService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ScapegoatDubboApplicationConfiguration(QuickDubboBootstrap.class)
public class CarBrandApiTest {

    // 你的appkey
    public static final String APPKEY = "84a0699c7a2be013";

    public static final String URL_CAR_BRAND = "http://api.jisuapi.com/car/brand";

    public static final String URL_CAR_TYPE = "http://api.jisuapi.com/car/type";

    public static final String URL_CAR_CAR = "http://api.jisuapi.com/car/car";

    @Autowired
    private CarBrandCrudService carBrandCrudService;

    */
/**
     * 根据品牌获取车型
     *//*

    @Test
    public void GetAllBrand() {

        String result = null;
        String url = URL_CAR_BRAND + "?appkey=" + APPKEY;

        try {

            result = HttpClientUtils.getData(url);
            JSONObject json = JSONObject.parseObject(result);
            if (json.getInteger("status") != 0) {
                System.out.println(json.getString("msg"));
            } else {
                JSONArray resultArray = json.getJSONArray("result");
                List<CarBrand> carBrandList = new ArrayList();
                for (int i = 0; i < resultArray.size(); i++) {
                    JSONObject obj = (JSONObject) resultArray.get(i);
                    Long id = obj.getLong("id");
                    String name = obj.getString("name");
                    String initial = obj.getString("initial");
                    Long parentid = obj.getLong("parentid");
                    String logo = obj.getString("logo");
                    Integer depth = obj.getInteger("depth");
                    CarBrand carBrand = new CarBrand();
                    carBrand.setId(id);
                    carBrand.setName(name);
                    carBrand.setParentId(parentid);
                    carBrand.setInitial(initial);
                    carBrand.setLogo(logo);
                    carBrand.setDepth(depth);
                    carBrandList.add(carBrand);
                    System.out.println("i=" + i + ":" + id + " " + name + " " + initial + " " + parentid + " " + logo + " " + depth);
                }
                carBrandCrudService.insertBatch(carBrandList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GetAllType() {

        String result = null;
        try {
            EntityWrapper<CarBrand> entityWrapper = new EntityWrapper<>();
            List<CarBrand> brandList = carBrandCrudService.selectList(entityWrapper);
            if (brandList != null && !brandList.isEmpty()) {

                for (int k = 0; k < brandList.size(); k++) {

                    Long curParentId = brandList.get(k).getId();
                    String url = URL_CAR_TYPE + "?appkey=" + APPKEY + "&parentid=" + curParentId;
                    result = HttpClientUtils.getData(url);
                    JSONObject json = JSONObject.parseObject(result);
                    if (json.getInteger("status") != 0) {
                        System.out.println(json.getString("msg"));
                    } else {
                        JSONArray resultArray = json.getJSONArray("result");
                        List<CarBrand> carBrandList = new ArrayList();
                        for (int i = 0; i < resultArray.size(); i++) {
                            JSONObject obj = (JSONObject) resultArray.get(i);
                            Long parentid = obj.getLong("id");
                            String parentName = obj.getString("name");
                            String parentFullName = obj.getString("fullname");
                            String initial = obj.getString("initial");
                            JSONArray listArray = obj.getJSONArray("list");
                            CarBrand parBrand = new CarBrand();
                            parBrand.setId(parentid);
                            parBrand.setName(parentName);
                            parBrand.setFullName(parentFullName);
                            parBrand.setParentId(curParentId);
                            parBrand.setInitial(initial);
                            parBrand.setDepth(2);
                            carBrandList.add(parBrand);
                            if (listArray != null && !listArray.isEmpty()) {
                                for (int j = 0; j < listArray.size(); j++) {
                                    JSONObject objSub = (JSONObject) listArray.get(j);
                                    Long id = objSub.getLong("id");
                                    String name = objSub.getString("name");
                                    String fullname = objSub.getString("fullname");
                                    String logo = objSub.getString("logo");
                                    String salestate = objSub.getString("salestate");
                                    Integer depth = objSub.getInteger("depth");
                                    CarBrand carBrand = new CarBrand();
                                    carBrand.setId(id);
                                    carBrand.setFullName(fullname);
                                    carBrand.setName(name);
                                    carBrand.setParentId(parentid);
                                    carBrand.setInitial(initial);
                                    carBrand.setLogo(logo);
                                    carBrand.setDepth(depth);
                                    carBrand.setSaleState(salestate);
                                    carBrandList.add(carBrand);
                                }
                            }
                        }
                        carBrandCrudService.insertBatch(carBrandList);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void GetAllCar() {

        String result = null;
        try {

            EntityWrapper<CarBrand> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("depth", 3);
            List<CarBrand> carTypeList = carBrandCrudService.selectList(entityWrapper);
            if (carTypeList != null && !carTypeList.isEmpty()) {

                for (int k = 0; k < carTypeList.size(); k++) {

                    Long curParentId = carTypeList.get(k).getId();
                    String url = URL_CAR_CAR + "?appkey=" + APPKEY + "&parentid=" + curParentId;
                    result = HttpClientUtils.getData(url);
                    JSONObject json = JSONObject.parseObject(result);
                    if (json.getInteger("status") != 0) {
                        System.out.println(json.getString("msg"));
                    } else {

                        JSONObject jsonObjectNew = json.getJSONObject("result");

                        Long parentid = jsonObjectNew.getLong("id");
                        JSONArray listArray = jsonObjectNew.getJSONArray("list");

                        if (listArray != null && !listArray.isEmpty()) {
                            List<CarBrand> carBrandList = new ArrayList();
                            for (int j = 0; j < listArray.size(); j++) {
                                JSONObject objSub = (JSONObject) listArray.get(j);
                                Long id = objSub.getLong("id");
                                String name = objSub.getString("name");
                                String initial = objSub.getString("initial");
                                String fullname = objSub.getString("fullname");
                                String logo = objSub.getString("logo");
                                String price = objSub.getString("price");
                                String yeartype = objSub.getString("yeartype");
                                String productionstate = objSub.getString("productionstate");
                                String salestate = objSub.getString("salestate");
                                String sizetype = objSub.getString("sizetype");
                                CarBrand carBrand = new CarBrand();
                                carBrand.setId(id);
                                carBrand.setName(name);
                                carBrand.setInitial(initial);
                                carBrand.setFullName(fullname);
                                carBrand.setDepth(4);
                                carBrand.setLogo(logo);
                                carBrand.setPrice(price);
                                carBrand.setYearType(yeartype);
                                carBrand.setProductionState(productionstate);
                                carBrand.setSaleState(salestate);
                                carBrand.setSizeType(sizetype);
                                carBrand.setParentId(parentid);
                                carBrandList.add(carBrand);
                            }
                            carBrandCrudService.insertBatch(carBrandList);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
