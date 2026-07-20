package com.naviroq.patterns.structural.proxy.ec2Instance.ec2proxy;

import com.naviroq.patterns.structural.proxy.ec2Instance.EC2Instance;
import com.naviroq.patterns.structural.proxy.ec2Instance.EC2Services;

import java.util.HashMap;
import java.util.Map;

public class EC2ProxyInstance implements EC2Services {
    private final EC2Instance ec2Instance;
    private final Map<String, String> cache = new HashMap<>();

    // constructor
    public EC2ProxyInstance () {
        ec2Instance = new EC2Instance();
    }

    @Override
    public String compute(String input) {
        if (cache.containsKey(input)) {
            System.out.println("[EC2Proxy] ✅ CACHE HIT for: " + input);
            return cache.get(input);
        }

        System.out.println("[EC2Proxy] ❌ CACHE MISS for: " + input + " — starting EC2 instance...");
        String result = ec2Instance.compute(input);
        cache.put(input, result);
        System.out.println("[EC2Proxy] 💾 Cached result for: " + input);
        return result;
    }
}
