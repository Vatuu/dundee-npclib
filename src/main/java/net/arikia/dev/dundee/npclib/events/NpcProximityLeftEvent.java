package net.arikia.dev.dundee.npclib.events;

import com.obadiahpcrowe.dundee.event.impl.DundeeEvent;
import com.obadiahpcrowe.dundee.player.Player;
import net.arikia.dev.dundee.npclib.PlayerNPC;

public class NpcProximityLeftEvent extends DundeeEvent{

    private PlayerNPC npc;
    private Player player;

    public NpcProximityLeftEvent(PlayerNPC npc, Player player){
        this.npc = npc;
        this.player = player;
    }
}
