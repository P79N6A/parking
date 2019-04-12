package com.zoeeasy.cloud.inspect.provider;

import com.scapegoat.infrastructure.core.client.DefaultOpenClient;
import com.scapegoat.infrastructure.core.client.OpenClient;
import com.scapegoat.infrastructure.core.client.OpenClientProvider;

import java.util.HashMap;
import java.util.Map;

public class DefaultOpenClientProvider implements OpenClientProvider {

    private static Map<String, OpenClient> map;

    private static DefaultOpenClientProvider defaultOpenClientProvider;

    private DefaultOpenClientProvider() {
        map = new HashMap<>();
        //web
        map.put("71fe93d94b5e4fcba7d55183efca91f2", new DefaultOpenClient("71fe93d94b5e4fcba7d55183efca91f2", "25a4991e45e64d43b7f90ac528747957"));
        //inspect
        map.put("35d20098bb964470a75c64a62e0a541f", new DefaultOpenClient("35d20098bb964470a75c64a62e0a541f", "4f795221443343e6a05026e83608d07b"));

    }

    public static DefaultOpenClientProvider getInstance() {
        if (defaultOpenClientProvider == null) {
            defaultOpenClientProvider = new DefaultOpenClientProvider();
        }
        return defaultOpenClientProvider;
    }

    @Override
    public OpenClient loadOpenClient(String accessKey) {
        return map.getOrDefault(accessKey, null);
    }
}
