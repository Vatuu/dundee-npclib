package net.arikia.dev.dundee.npclib;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.obadiahpcrowe.dundee.Dundee;
import com.obadiahpcrowe.dundee.entity.DundeeEntity;
import com.obadiahpcrowe.dundee.entity.types.DundeeArmourStand;
import com.obadiahpcrowe.dundee.inventory.item.DundeeItem;
import com.obadiahpcrowe.dundee.inventory.item.ItemMeta;
import com.obadiahpcrowe.dundee.packet.play.PlayOutPlayerListItem;
import com.obadiahpcrowe.dundee.packet.play.PlayOutSpawnPlayer;
import com.obadiahpcrowe.dundee.player.DundeePlayer;
import com.obadiahpcrowe.dundee.substance.Substance;
import com.obadiahpcrowe.dundee.world.DundeeWorld;
import com.obadiahpcrowe.dundee.world.Position;
import lombok.Data;

import java.util.*;

@Data
public class PlayerNPC {

    private final UUID id = UUID.randomUUID();
    private String title;
    private String subtitle;

    private String skinValue = "";
    private String signature = "";
    private final List<SkinOverlay> overlays;

    private boolean crouched = false;
    private boolean glowing = false;

    private DundeeItem mainHandItem;
    private DundeeItem offHandItem;
    private DundeeItem headItem;
    private DundeeItem chestItem;
    private DundeeItem pantsItem;
    private DundeeItem bootsItems;

    private final Map<UUID, DundeeEntity> entities;

    PlayerNPC(){
        entities = Maps.newConcurrentMap();
        overlays = Lists.newArrayList(SkinOverlay.values());
        mainHandItem = new DundeeItem(Substance.AIR, 1, (byte)0, new ItemMeta());
        offHandItem = new DundeeItem(Substance.AIR, 1, (byte)0, new ItemMeta());
        headItem = new DundeeItem(Substance.AIR, 1, (byte)0, new ItemMeta());
        chestItem = new DundeeItem(Substance.AIR, 1, (byte)0, new ItemMeta());
        pantsItem = new DundeeItem(Substance.AIR, 1, (byte)0, new ItemMeta());
        bootsItems = new DundeeItem(Substance.AIR, 1, (byte)0, new ItemMeta());
    }

    public void spawn(Position pos){
        entities.clear();
        DundeeWorld world = (DundeeWorld)pos.getWorld();
        DundeePlayer playerEntity = Utils.createPlayerObject(world, "NPC-" + id.toString().replaceAll("-", ""), id, skinValue, signature);
        playerEntity.getMetadata().setUseCustomName(false);
        playerEntity.getMetadata().setCrouched(crouched);
        playerEntity.getMetadata().setGlowing(glowing);
        byte flag = 0x00;
        for(SkinOverlay o : overlays)
            flag = (byte)(flag + o.getFlag());
        playerEntity.getMetadata().setSkinFlags(flag);
        playerEntity.getMetadata().setNoGravity(true);
        playerEntity.getInventory().setSelectedSlot(36);

        playerEntity.getInventory().addItem(mainHandItem, 36);
        playerEntity.getInventory().addItem(offHandItem, 45);
        playerEntity.getInventory().addItem(headItem, 5);
        playerEntity.getInventory().addItem(chestItem, 6);
        playerEntity.getInventory().addItem(pantsItem, 7);
        playerEntity.getInventory().addItem(bootsItems, 8);

        PlayOutPlayerListItem.AddPlayer addPacket = new PlayOutPlayerListItem.AddPlayer();
        addPacket.addPlayer(playerEntity);
        PlayOutSpawnPlayer spawnPacket = new PlayOutSpawnPlayer(playerEntity);
        PlayOutPlayerListItem.RemovePlayer removePacket = new PlayOutPlayerListItem.RemovePlayer();
        removePacket.removePlayer(playerEntity);
        Utils.packetQueneWorld(world, addPacket, spawnPacket, removePacket);
        Dundee.PLAYERS.remove(playerEntity);
        playerEntity.setPosition(pos, true);
        entities.put(id, playerEntity);

        if(title != null){
            DundeeArmourStand titleStand = new DundeeArmourStand(pos);
            titleStand.getMetadata().setInvisible(true);
            titleStand.getMetadata().setUseCustomName(true);
            titleStand.getMetadata().setCustomName(title);
            titleStand.getMetadata().setNoGravity(true);
            Utils.packetQueneWorld(world, titleStand.getSpawnPacket());
            entities.put(titleStand.getUuid(), titleStand);
        }

        if(subtitle != null){
            DundeeArmourStand subtitleStand = new DundeeArmourStand(pos.add(0.0, -0.3, 0.0));
            subtitleStand.getMetadata().setInvisible(true);
            subtitleStand.getMetadata().setUseCustomName(true);
            subtitleStand.getMetadata().setCustomName(title);
            subtitleStand.getMetadata().setNoGravity(true);
            Utils.packetQueneWorld(world, subtitleStand.getSpawnPacket());
            entities.put(subtitleStand.getUuid(), subtitleStand);
        }
    }

    public void despawn(){
        entities.forEach((u, e) -> e.onRemove());
    }

    public void update(){
        Position p = entities.get(id).getPosition();
        despawn();
        spawn(p);
    }

    public void update(Position p){
        despawn();
        spawn(p);
    }

    public void setTitle(String s){
        if(s.contains("&"))
            title = s.replaceAll("&", "§");
        else
            title = s;
    }

    public void setSubtitle(String s){
        if(s.contains("&"))
            subtitle = s.replaceAll("&", "§");
        else
            subtitle = s;
    }

    public void setOverlays(SkinOverlay... flags){
        overlays.clear();
        overlays.addAll(Arrays.asList(flags));
    }

    //Item Setter, sadly I can't use Lombok here :/

    public void setMainHandItemRaw(Substance s, byte damage){
        this.mainHandItem = new DundeeItem(s, 1, damage, new ItemMeta());
    }

    public void setOffHandItemRaw(Substance s, byte damage){
        this.offHandItem = new DundeeItem(s, 1, damage, new ItemMeta());
    }

    public void setHeadItemRaw(Substance s, byte damage){
        this.headItem = new DundeeItem(s, 1, damage, new ItemMeta());
    }

    public void setChestItemRaw(Substance s, byte damage){
        this.chestItem = new DundeeItem(s, 1, damage, new ItemMeta());
    }

    public void setPantsItemRaw(Substance s, byte damage){
        this.pantsItem = new DundeeItem(s, 1, damage, new ItemMeta());
    }

    public void setBootsItemRaw(Substance s, byte damage){
        this.bootsItems = new DundeeItem(s, 1, damage, new ItemMeta());
    }
}
