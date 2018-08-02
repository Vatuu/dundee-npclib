package net.arikia.dev.dundee;

import com.obadiahpcrowe.dundee.Dundee;
import com.obadiahpcrowe.dundee.inventory.item.DundeeItem;
import com.obadiahpcrowe.dundee.inventory.item.LeatherArmourMeta;
import com.obadiahpcrowe.dundee.plugin.DundeePlugin;
import com.obadiahpcrowe.dundee.plugin.Plugin;
import com.obadiahpcrowe.dundee.substance.Substance;
import com.obadiahpcrowe.dundee.world.Position;
import net.arikia.dev.dundee.npclib.NpcManager;
import net.arikia.dev.dundee.npclib.PlayerNPC;

@Plugin(
        name = "NPC Library",
        authors = {"Vatuu"},
        desc = "Allows to easily create fake player NPCs, with functionality and behaviour."
)
public class NpcLib extends DundeePlugin {

    private static final NpcManager NPC_MANAGER = new NpcManager();

    public NpcLib() {
        //Update Check
    }

    public void onLoad() {
        PlayerNPC npc = NPC_MANAGER.createNpc();
        npc.setSkinValue("eyJ0aW1lc3RhbXAiOjE1MjMzODk0NjkwMTIsInByb2ZpbGVJZCI6IjdjZjc2MTFkYmY2YjQxOWRiNjlkMmQzY2Q4NzUxZjRjIiwicHJvZmlsZU5hbWUiOiJrYXJldGg5OTkiLCJzaWduYXR1cmVSZXF1aXJlZCI6dHJ1ZSwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Q1NDg5NzJlZDJiYWY1OTQxZjk2OWNmMzIyNzdmNTJkOWJjOWJkOGU3MTZmNTMyMjhiODVhN2JiOCJ9fX0=");
        npc.setSignature("oeLEGvpUDIN8D4BTNpIoFUACXbd6TVGRTBysuIiMrhdOhYlOsjBPyReMGU+dPJrCyYJmdJIXakwH2Jyz1EaGe5oB6flT7I8I9nwwIc4xECB/uLhS1RBgUQMdVuswXJU+E2MM0tj1W+aOpu9G4BNz7iBeYP8zTaj3eRIagMQHwMWraCUUF00YUjOBT13rGFOGmq7GNUhVreXK3TWLmCk7GKYQ5PWDySRtIZXhm8IDme6CHjPq/CTervZ3H2SDjQCIS6Md8f7vOVHccg1hiiJJamObN4yaxTLZOqROoIauLne6QraFnBhUPGN/8JFv2lhzscp8aNk/4kRRpVOw2tP+2Bu9P+VXffjY+E17PUj23zqqyZY575DT6Mrea8GoEQtp3Q5XBuSNzfwKZF1AszCtLePOUvASKrymQunpst9pExnTmsMo3p5fgruN35CmOgQ37n3FzYBl6K+/++hXzP0yK8yuaJb/I3bOf0xuSTPDcS9Ti863TYUrtp3Bryj5UCz/ntye21HBIWKG4e+r15K2DCRHhjsqPA9QliVvVV/pf8l2/WYunMLL7GfQfMqkHVWsyHnet6Ad92DvUyyeqVJVvNlZLG7Ht8kjlQV1HW/ZALUGOviZVyU0IF627Peggjiy3unWlTdcE/kQ0AqMQUFU0etFlEh9SNiNNwmwMCSjWZ0=");
        npc.setTitle("§2§lHarold the Fish Salesman");
        npc.setSubtitle("§4§iFresh, fresh fish!");
        npc.setMainHandItemRaw(Substance.RAW_FISH, (byte)0);
        npc.setOffHandItemRaw(Substance.RAW_FISH, (byte)0);
        npc.setHeadItem(new DundeeItem(Substance.LEATHER_HELMET, 1, (byte)0, new LeatherArmourMeta("0080FF")));
        npc.spawn(new Position(Dundee.WORLD_LOADER.getDefaultWorld(), 0, 64, 0));
    }

    public void onUnload() {
    }

    public static NpcManager getManager() {
        return NPC_MANAGER;
    }
}
