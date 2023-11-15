package io.github.koufu193.core.game.entities.handlers;

import io.github.koufu193.core.game.data.inventory.InventoryView;
import io.github.koufu193.core.game.entities.interfaces.IPlayer;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InventoryHandler {
    private final IPlayer player;
    private final Map<Byte,InventoryView> views=new HashMap<>();

    public InventoryHandler(@NotNull IPlayer player) {
        this.player = player;
        views.put((byte) 0,new InventoryView(player.inventory()));
    }
    public void open(@NotNull InventoryView view){
        Optional<Map.Entry<Byte, InventoryView>> currentView=this.views.entrySet().stream().filter(entry->entry.getValue()==view).findFirst();
        if(currentView.isPresent()){
            openById(currentView.get().getKey());
        }else {
            createAndOpenInventory(view);
        }
    }
    public void onClose(byte windowId){
        if(windowId==0) return;
        if(!views.containsKey(windowId)) throw new IllegalArgumentException(String.format("Requested windowId %d not found",windowId));
        views.remove(windowId).remove(this.player);
    }
    private void createAndOpenInventory(@NotNull InventoryView view){
        byte windowId=makeWindowId();
        this.player.packetHandler().openInventory(windowId,view);
        this.views.put(windowId,view);
        this.player.packetHandler().sendContainerContents(windowId, (byte) 0,view,null);
    }
    private byte makeWindowId(){
        for(int b=0;b<0x100;b++){
            if(!views.containsKey((byte)b)) return (byte)b;
        }
        throw new RuntimeException("Usable ID not found");
    }
    private void removeById(byte windowId){
        if(windowId==0) throw new IllegalArgumentException("windowId must not be zero");
        if(!views.containsKey(windowId)) return;
        this.views.remove(windowId).remove(this.player);
    }
    private void openById(byte windowId){
        if(!views.containsKey(windowId)) throw new IllegalArgumentException(String.format("Requested windowId %d not found",windowId));
        this.player.packetHandler().openInventory(windowId,views.get(windowId));
    }
}
