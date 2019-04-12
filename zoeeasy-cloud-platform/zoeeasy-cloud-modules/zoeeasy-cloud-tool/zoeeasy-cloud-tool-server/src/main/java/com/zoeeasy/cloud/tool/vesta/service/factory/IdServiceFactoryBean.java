package com.zoeeasy.cloud.tool.vesta.service.factory;

import com.zoeeasy.cloud.tool.vesta.intf.IdService;
import com.zoeeasy.cloud.tool.vesta.service.impl.IdServiceImpl;
import com.zoeeasy.cloud.tool.vesta.service.impl.provider.IpConfigurableMachineIdProvider;
import com.zoeeasy.cloud.tool.vesta.service.impl.provider.PropertyMachineIdProvider;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author walkman
 */
@Slf4j
public class IdServiceFactoryBean implements FactoryBean<IdService> {

    @Getter
    @Setter
    private Type providerType;

    @Getter
    @Setter
    private long machineId;

    @Getter
    @Setter
    private String ips;

    @Getter
    @Setter
    private long genMethod = -1;

    @Getter
    @Setter
    private long type = -1;

    @Getter
    @Setter
    private long version = -1;

    private IdService idService;

    public void init() {
        if (providerType == null) {
            log.error("The type of Id service is mandatory.");
            throw new IllegalArgumentException(
                    "The type of Id service is mandatory.");
        }
        switch (providerType) {
            case PROPERTY:
                idService = constructPropertyIdService(machineId);
                break;
            case IP_CONFIGURABLE:
                idService = constructIpConfigurableIdService(ips);
                break;
        }
    }

    @Override
    public IdService getObject() throws Exception {
        return idService;
    }

    private IdService constructPropertyIdService(long machineId) {
        log.info("Construct Property IdService machineId {}", machineId);

        PropertyMachineIdProvider propertyMachineIdProvider = new PropertyMachineIdProvider();
        propertyMachineIdProvider.setMachineId(machineId);

        IdServiceImpl idServiceImpl = new IdServiceImpl();
        idServiceImpl.setMachineIdProvider(propertyMachineIdProvider);
        if (genMethod != -1)
            idServiceImpl.setGenMethod(genMethod);
        if (type != -1)
            idServiceImpl.setType(type);
        if (version != -1)
            idServiceImpl.setVersion(version);
        idServiceImpl.init();
        return idServiceImpl;
    }

    private IdService constructIpConfigurableIdService(String ips) {
        log.info("Construct Ip Configurable IdService ips {}", ips);

        IpConfigurableMachineIdProvider ipConfigurableMachineIdProvider = new IpConfigurableMachineIdProvider(ips);

        IdServiceImpl idServiceImpl = new IdServiceImpl();
        idServiceImpl.setMachineIdProvider(ipConfigurableMachineIdProvider);
        if (genMethod != -1)
            idServiceImpl.setGenMethod(genMethod);
        if (type != -1)
            idServiceImpl.setType(type);
        if (version != -1)
            idServiceImpl.setVersion(version);
        idServiceImpl.init();
        return idServiceImpl;
    }

    @Override
    public Class<?> getObjectType() {
        return IdService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public enum Type {
        PROPERTY,
        IP_CONFIGURABLE,
    }

}
