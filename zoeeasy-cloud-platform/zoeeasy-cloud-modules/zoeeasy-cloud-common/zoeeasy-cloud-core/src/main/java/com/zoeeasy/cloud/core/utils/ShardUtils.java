package com.zoeeasy.cloud.core.utils;

public class ShardUtils {

    public static void main(String[] args) {

        int i = 0;
        int step = 4;
        int count = 128;
        for (int index = 0; index < count; index++) {
            System.out.println(((step * index) + 1) + "-" + ((step * index) + 2) + "=" + index);
        }
        System.out.println("---------------------------------------------------");
        System.out.println("---------------------------------------------------");
        System.out.println("---------------------------------------------------");
        String tableName = "ord_parking_order";
        for (int index = 1; index < count; index++) {
            System.out.println("create table " + tableName + "_" + index + " like " + tableName + "_1;");
        }
    }
}
