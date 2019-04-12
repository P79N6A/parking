package com.zhuyitech.parking.common.utils;

/**
 * 关于坐标、距离的工具类
 *
 * @author walkman
 */
public final class LocationUtil {

    private static final Double PI = Math.PI;

    private static final Double PK = 180 / PI;

    /**
     * 地球半径
     */
    private static final Double EARTH_RADIUS = 6378137d;

    private LocationUtil() {
    }

    /**
     * 获取两个经纬度之间的距离（单位：米）
     *
     * @param aLatitude  A点纬度
     * @param aLongitude A点经度
     * @param bLatitude  B点纬度
     * @param bLongitude B点经度
     * @return 距离(单位米)
     */
    public static Double getDistanceFromTwoPoints(Double aLatitude, Double aLongitude, Double bLatitude, Double bLongitude) {
        Double distance = null;
        if (aLatitude != null && aLongitude != null && bLatitude != null && bLongitude != null) {
            if (aLatitude.equals(bLatitude) && aLongitude.equals(bLongitude)) {
                return 0.0d;
            }
            double t1 = Math.cos(aLatitude / PK) * Math.cos(aLongitude / PK) * Math.cos(bLatitude / PK) * Math.cos(bLongitude / PK);
            double t2 = Math.cos(aLatitude / PK) * Math.sin(aLongitude / PK) * Math.cos(bLatitude / PK) * Math.sin(bLongitude / PK);
            double t3 = Math.sin(aLatitude / PK) * Math.sin(bLatitude / PK);

            double tt = Math.acos(t1 + t2 + t3);
            distance = EARTH_RADIUS * tt;
        }
        return distance;
    }

    /**
     * 根据经纬度和半径计算经纬度范围
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @param radius    半径(单位米)
     * @return {最小经度minLng,最大经度maxLng,最小纬度minLat,最大纬度maxLat}
     */
    public static Double[] getAroundPosition(Double longitude, Double latitude, Double radius) {
        //先计算查询点的经纬度范围
        if (longitude == null || latitude == null || radius == null || radius.compareTo(0d) < 0) {
            return null;
        }
        if (radius.compareTo(0d) == 0) {
            return new Double[]{longitude, longitude, latitude, latitude};
        }

        // 计算经度弧度,从弧度转换为角度
        double dLongitude = 2 * (Math.asin(Math.sin(radius / (2 * EARTH_RADIUS)) / Math.cos(Math.toRadians(latitude))));
        dLongitude = Math.toDegrees(dLongitude);

        double minLongitude = longitude - dLongitude;
        double maxLongitude = longitude + dLongitude;

        // 计算纬度角度
        double dLatitude = radius / EARTH_RADIUS;
        dLatitude = Math.toDegrees(dLatitude);

        double minLatitude = latitude - dLatitude;
        double maxLatitude = latitude + dLatitude;
        return new Double[]{minLongitude, maxLongitude, minLatitude, maxLatitude};
    }

}
