package io.github.koufu193.core.game.entities.player;

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
    public void close(byte windowId){
        onClose(windowId);
        this.player.packetHandler().closeInventory(windowId);
    }
    public void close(@NotNull InventoryView view){
        Optional<Map.Entry<Byte, InventoryView>> currentView=this.views.entrySet().stream().filter(entry->entry.getValue()==view).findFirst();
        currentView.ifPresent(byteInventoryViewEntry -> close(byteInventoryViewEntry.getKey()));
    }
    public void onClose(byte windowId){
        if(windowId==0) return;
        if(isUnregisteredWindowId(windowId)) throw new IllegalArgumentException(String.format("Requested windowId %d not found",windowId));
        views.remove(windowId).remove(this.player);
    }
    public void onClose(@NotNull InventoryView view){
        Optional<Map.Entry<Byte, InventoryView>> currentView=this.views.entrySet().stream().filter(entry->entry.getValue()==view).findFirst();
        currentView.ifPresent(byteInventoryViewEntry -> onClose(byteInventoryViewEntry.getKey()));
    }
    public void remove(@NotNull InventoryView view){
        Optional<Map.Entry<Byte, InventoryView>> currentView=this.views.entrySet().stream().filter(entry->entry.getValue()==view).findFirst();
        currentView.ifPresent(byteInventoryViewEntry -> removeById(byteInventoryViewEntry.getKey()));
    }
    private void createAndOpenInventory(@NotNull InventoryView view){
        byte windowId= generateWindowId();
        this.player.packetHandler().openInventory(windowId,view);
        this.views.put(windowId,view);
        this.player.packetHandler().sendContainerContents(windowId, (byte) 0,view,null);
    }
    private byte generateWindowId(){
        for(int windowId=0;windowId<0x100;windowId++){
            if(isUnregisteredWindowId((byte) windowId)) return (byte)windowId;
        }
        throw new RuntimeException("Usable ID not found");
    }
    private void removeById(byte windowId){
        if(windowId==0) throw new IllegalArgumentException("windowId must not be zero");
        if(isUnregisteredWindowId(windowId)) return;
        this.views.remove(windowId).remove(this.player);
    }
    private void openById(byte windowId){
        if(isUnregisteredWindowId(windowId)) throw new IllegalArgumentException(String.format("Requested windowId %d not found",windowId));
        this.player.packetHandler().openInventory(windowId,views.get(windowId));
    }
    private boolean isUnregisteredWindowId(byte windowId){
        return !views.containsKey(windowId);
    }
}
