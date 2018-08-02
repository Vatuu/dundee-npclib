package net.arikia.dev.dundee.npclib;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SkinOverlay {

    CAPE((byte)0x01),
    CHEST((byte)0x02),
    LEFT_ARM((byte)0x04),
    RIGHT_ARM((byte)0x08),
    LEFT_LEG((byte)0x10),
    RIGHT_LEG((byte)0x20),
    HEAD((byte)0x40);

    private final byte flag;
}
