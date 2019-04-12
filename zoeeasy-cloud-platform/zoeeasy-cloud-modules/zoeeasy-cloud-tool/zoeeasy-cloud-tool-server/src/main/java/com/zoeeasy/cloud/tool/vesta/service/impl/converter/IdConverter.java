package com.zoeeasy.cloud.tool.vesta.service.impl.converter;


import com.zoeeasy.cloud.tool.vesta.bean.Id;

public interface IdConverter {

    long convert(Id id);

    Id convert(long id);

}
