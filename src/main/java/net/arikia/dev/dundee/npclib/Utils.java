package net.arikia.dev.dundee.npclib;

import com.obadiahpcrowe.dundee.packet.PacketOut;
import com.obadiahpcrowe.dundee.player.DundeePlayer;
import com.obadiahpcrowe.dundee.tablist.TabListEntry;
import com.obadiahpcrowe.dundee.util.RecipientSelector;
import com.obadiahpcrowe.dundee.world.DundeeWorld;
import com.obadiahpcrowe.dundee.world.Position;
import sun.security.krb5.internal.NetClient;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.UUID;

public final class Utils {

    public static DundeePlayer createPlayerObject(DundeeWorld world, String name, UUID id, String skinValue, String signature){
        try {
            Constructor<DundeePlayer> constructor = DundeePlayer.class.getDeclaredConstructor(NetClient.class, DundeeWorld.class, String.class, UUID.class, TabListEntry.PlayerProperty.class);
            constructor.setAccessible(true);
            return constructor.newInstance(null, world, name, id, new TabListEntry.PlayerProperty("textures", skinValue, signature));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void packetQueneWorld(DundeeWorld w, PacketOut... packets){
        Arrays.asList(packets).forEach(packet -> {
            RecipientSelector.allInWorld(w, packet);
        });
    }

    public static boolean isInProximity(Position origin, double r, Position point){
        double distance_squared = Math.pow((origin.getX() - point.getX()), 2) + Math.pow((origin.getZ() - point.getZ()), 2);
        return distance_squared < Math.pow(r, 2);
    }
}
