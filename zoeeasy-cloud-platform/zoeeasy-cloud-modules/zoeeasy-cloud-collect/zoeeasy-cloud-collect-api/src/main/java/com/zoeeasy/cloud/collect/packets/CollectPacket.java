package com.zoeeasy.cloud.collect.packets;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.tio.core.intf.Packet;

/**
 * @author Inmier
 */
@ToString
public class CollectPacket extends Packet {

    @Getter
    @Setter
    private int len;

    @Getter
    @Setter
    private byte[] body;

}
