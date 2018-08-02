package net.arikia.dev.dundee.npclib.events;

import com.obadiahpcrowe.dundee.entity.EntityUseType;
import com.obadiahpcrowe.dundee.event.impl.DundeeEvent;
import com.obadiahpcrowe.dundee.player.Player;
import lombok.Getter;
import net.arikia.dev.dundee.npclib.PlayerNPC;

@Getter
public class NpcInteractEvent extends DundeeEvent {

    PlayerNPC npc;
    Player player;
    EntityUseType type;

    public NpcInteractEvent(PlayerNPC npc, Player player, EntityUseType type){
        this.npc = npc;
        this.player = player;
        this.type = type;
    }
}
