package com.sz.demo.rpc.service.server;

/**
 * @program: demo
 * @description:
 * @author: jie fu
 * @create: 2019-07-27 14:36
 */
public class DrinkServiceImpl implements IDrinkService {



    @Override
    public String drinkWater(String userName) {
        return (userName != null ? userName+"有水喝":"没水喝");
    }
}
