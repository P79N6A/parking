package com.zoeeasy.cloud.tool.vesta.intf;

import com.zoeeasy.cloud.tool.vesta.bean.Id;

import java.util.Date;

@SuppressWarnings("unused")
public interface IdService {

    long genId();

    Id expId(long id);

    long makeId(long time, long seq);

    long makeId(long time, long seq, long machine);

    long makeId(long genMethod, long time, long seq, long machine);

    long makeId(long type, long genMethod, long time, long seq, long machine);

    long makeId(long version, long type, long genMethod, long time, long seq, long machine);

    Date transTime(long time);
}
