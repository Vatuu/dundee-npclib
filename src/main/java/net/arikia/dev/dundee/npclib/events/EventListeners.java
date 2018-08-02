package net.arikia.dev.dundee.npclib.events;

import com.obadiahpcrowe.dundee.Dundee;
import com.obadiahpcrowe.dundee.entity.EntityType;
import com.obadiahpcrowe.dundee.event.Event;
import com.obadiahpcrowe.dundee.event.EventHandler;
import com.obadiahpcrowe.dundee.event.EventPriority;
import com.obadiahpcrowe.dundee.event.player.PlayerEntityInteractionEvent;
import com.obadiahpcrowe.dundee.event.player.PlayerMovementEvent;
import net.arikia.dev.dundee.NpcLib;
import net.arikia.dev.dundee.npclib.NpcManager;
import net.arikia.dev.dundee.npclib.PlayerNPC;
import net.arikia.dev.dundee.npclib.Utils;

public class EventListeners implements EventHandler {

    @Event(event = PlayerEntityInteractionEvent.class, priority = EventPriority.HIGH)
    public void onEntityInteract(PlayerEntityInteractionEvent e){
        if(e.getEntity().getEntityType() != EntityType.PLAYER)
            return;

        PlayerNPC p = NpcLib.getManager().getByUuid(e.getEntity().getUuid());
        if(p != null){
            e.setCancelled(true);
            Dundee.EVENT_DISPATCHER.callEvent(new NpcInteractEvent(p, e.getPlayer(), e.getType()));
        }
    }

    @Event(event = PlayerMovementEvent.class, priority = EventPriority.HIGH)
    public void onPlayerMove(PlayerMovementEvent e){
        //Enter/Leave Proximity Check
        NpcManager.getNPCS_PROXIMITY().forEach((npc, pl) -> {
            if(Utils.isInProximity(npc.getEntities().get(npc.getId()).getPosition(), npc.getProximityEventRadius(), e.getTo())){
                if(!pl.contains(e.getPlayer())){
                    pl.add(e.getPlayer());
                    Dundee.EVENT_DISPATCHER.callEvent(new NpcProximityEnteredEvent(npc, e.getPlayer()));
                }
            }else{
                if(pl.contains(e.getPlayer())){
                    pl.remove(e.getPlayer());
                    Dundee.EVENT_DISPATCHER.callEvent(new NpcProximityLeftEvent(npc, e.getPlayer()));
                }
            }
        });
    }
}
