package net.arikia.dev.dundee.npclib;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.UUID;

public class NpcManager {

    private static final Set<PlayerNPC> NPCS = Sets.newConcurrentHashSet();

    public PlayerNPC createNpc(){
        PlayerNPC npc = new PlayerNPC();
        NPCS.add(npc);
        return npc;
    }

    public PlayerNPC getByUuid(UUID uuid){
        return NPCS.stream().filter(npc -> npc.getId() == uuid).findFirst().orElse(null);
    }
}
