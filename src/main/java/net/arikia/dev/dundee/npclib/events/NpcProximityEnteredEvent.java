package net.arikia.dev.dundee.npclib.events;

import com.obadiahpcrowe.dundee.event.impl.DundeeEvent;
import com.obadiahpcrowe.dundee.player.Player;
import lombok.Getter;
import net.arikia.dev.dundee.npclib.PlayerNPC;

@Getter
public class NpcProximityEnteredEvent extends DundeeEvent {

    private PlayerNPC npc;
    private Player player;

    public NpcProximityEnteredEvent(PlayerNPC npc, Player player){
        this.npc = npc;
        this.player = player;
    }
}
