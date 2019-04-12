package com.zhuyitech.parking.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author AkeemSuper
 * @date 2018/11/29 0029
 */
public class FakeUtils {

    private FakeUtils() {
    }

    public static final String DATE_FORMAT = "yyyyMMddHHmmss";

    public static CarItem createPlateNumber() {

        Random random = new Random();
        List<CarItem> cars = new ArrayList<>();
        cars.add(new CarItem("浙AB2500", 1, 0));
        cars.add(new CarItem("浙AV300G", 1, 0));
        cars.add(new CarItem("浙A98765", 1, 0));
        cars.add(new CarItem("浙A87654", 1, 0));
        cars.add(new CarItem("浙AD1D65", 1, 0));
        cars.add(new CarItem("浙A54321", 1, 0));
        cars.add(new CarItem("浙BB2500", 1, 0));
        cars.add(new CarItem("浙A22222", 1, 0));
        cars.add(new CarItem("粤E11111", 1, 0));
        cars.add(new CarItem("浙A66666", 1, 0));
        cars.add(new CarItem("浙DE15E3", 1, 0));
        cars.add(new CarItem("京B12345", 1, 0));
        cars.add(new CarItem("苏A54321", 1, 0));
        cars.add(new CarItem("浙A98765", 1, 0));
        cars.add(new CarItem("浙A87654", 1, 0));
        cars.add(new CarItem("浙AADG24", 1, 0));
        cars.add(new CarItem("豫S3L988", 1, 0));
        cars.add(new CarItem("浙AE3134", 1, 0));
        cars.add(new CarItem("京A22222", 1, 0));
        cars.add(new CarItem("浙A7GN96", 1, 0));
        cars.add(new CarItem("浙A88888", 1, 0));
        int i = random.nextInt(cars.size());
        if (i % 4 == 0) {
            return cars.get(i);
        } else {
            AutoCar autoCar = new AutoCar();
            CarItem carTtem = new CarItem(autoCar.car(), 1, 0);
            return carTtem;
        }
    }

    public static String getDevMacCode() {
        List<String> macCode = new ArrayList<>();
        macCode.add("132516696538743807");
        macCode.add("132516812502860799");
        macCode.add("132516879074853887");
        macCode.add("132516896254723071");
        Random random = new Random();
        int i = random.nextInt(macCode.size());
        return macCode.get(i);
    }

    public static String getDevDeviceId() {
        List<String> deviceId = new ArrayList<>();
        deviceId.add("132516836125180927");
        deviceId.add("132516853305050111");
        deviceId.add("132516909139624959");
        deviceId.add("132516920950785023");
        Random random = new Random();
        int i = random.nextInt(deviceId.size());
        return deviceId.get(i);
    }

    public static String getFatMacCode() {
        List<String> macCode = new ArrayList<>();
        macCode.add("132537200712614911");
        macCode.add("132537227556160511");
        macCode.add("132537284464477183");
        macCode.add("132537293054411775");
        Random random = new Random();
        int i = random.nextInt(macCode.size());
        return macCode.get(i);
    }

    public static String getFatDeviceId() {
        List<String> deviceId = new ArrayList<>();
        deviceId.add("132537249030998015");
        deviceId.add("132537267284607999");
        deviceId.add("132537314529248255");
        deviceId.add("132537328487891967");
        Random random = new Random();
        int i = random.nextInt(deviceId.size());
        return deviceId.get(i);
    }

}
