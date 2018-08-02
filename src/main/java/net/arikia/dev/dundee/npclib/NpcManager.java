package net.arikia.dev.dundee.npclib;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.obadiahpcrowe.dundee.player.DundeePlayer;
import com.obadiahpcrowe.dundee.player.Player;
import com.obadiahpcrowe.dundee.util.Tuple;
import lombok.Getter;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NpcManager {

    @Getter
    private static final Set<PlayerNPC> NPCS = Sets.newConcurrentHashSet();
    @Getter
    private static final Map<PlayerNPC, Set<Player>> NPCS_PROXIMITY = Maps.newConcurrentMap();

    public PlayerNPC createNpc(boolean useProximityEvents){
        PlayerNPC npc = new PlayerNPC();
        NPCS.add(npc);
        if(useProximityEvents)
            NPCS_PROXIMITY.put(npc, Sets.newConcurrentHashSet());
        return npc;
    }

    public PlayerNPC getByUuid(UUID uuid){
        return null;
    }
}
