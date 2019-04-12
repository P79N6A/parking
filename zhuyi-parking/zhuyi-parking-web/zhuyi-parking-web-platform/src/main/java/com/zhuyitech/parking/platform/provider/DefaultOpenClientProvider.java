package com.zhuyitech.parking.platform.provider;

import com.scapegoat.infrastructure.core.client.DefaultOpenClient;
import com.scapegoat.infrastructure.core.client.OpenClient;
import com.scapegoat.infrastructure.core.client.OpenClientProvider;

import java.util.HashMap;
import java.util.Map;

public class DefaultOpenClientProvider implements OpenClientProvider {

    private static Map<String, OpenClient> map =
            new HashMap<String, OpenClient>() {{
                //web
                put("71fe93d94b5e4fcba7d55183efca91f2", new DefaultOpenClient("71fe93d94b5e4fcba7d55183efca91f2", "25a4991e45e64d43b7f90ac528747957"));
                //inspect
                put("35d20098bb964470a75c64a62e0a541f", new DefaultOpenClient("35d20098bb964470a75c64a62e0a541f", "4f795221443343e6a05026e83608d07b"));
            }};

    @Override
    public OpenClient loadOpenClient(String accessKey) {
        return map.getOrDefault(accessKey, null);
    }
}
