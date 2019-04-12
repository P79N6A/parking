package com.zoeeasy.cloud.tool.vesta.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Id implements Serializable {

    private static final long serialVersionUID = 6870931236218221183L;

    private long machine;
    private long seq;
    private long time;
    private long genMethod;
    private long type;
    private long version;

    public Id(long machine, long seq, long time, long genMethod, long type, long version) {
        super();
        this.machine = machine;
        this.seq = seq;
        this.time = time;
        this.genMethod = genMethod;
        this.type = type;
        this.version = version;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("machine=").append(machine).append(",");
        sb.append("seq=").append(seq).append(",");
        sb.append("time=").append(time).append(",");
        sb.append("genMethod=").append(genMethod).append(",");
        sb.append("type=").append(type).append(",");
        sb.append("version=").append(version).append("]");
        return sb.toString();
    }

}
