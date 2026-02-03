package com.jordansbored.quadtannery;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.inventory.Inventory;
import com.hypixel.hytale.server.core.inventory.ItemStack;
import com.hypixel.hytale.server.core.inventory.container.ItemContainer;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public class FixTanneriesCommand extends AbstractPlayerCommand {

    private static final String TANNERY_ID = "Bench_Tannery";
    private static final String DOUBLE_TANNERY_ID = "Bench_DoubleTannery";

    public FixTanneriesCommand() {
        super("fixtanneries", "Remove metadata from tanneries so they work in crafting recipes");
        this.setPermissionGroup(GameMode.Adventure);
    }

    @Override
    protected void execute(@Nonnull CommandContext context, @Nonnull Store<EntityStore> store, 
                          @Nonnull Ref<EntityStore> ref, @Nonnull PlayerRef playerRef, @Nonnull World world) {
        
        Player player = store.getComponent(ref, Player.getComponentType());
        if (player == null) {
            context.sendMessage(Message.raw("Could not get player!"));
            return;
        }

        Inventory inventory = player.getInventory();
        int fixedCount = 0;

        fixedCount += fixContainer(inventory.getHotbar());
        fixedCount += fixContainer(inventory.getCombinedBackpackStorageHotbar());

        if (fixedCount > 0) {
            context.sendMessage(Message.raw("Fixed " + fixedCount + " tannery(ies)! They can now be used for crafting."));
        } else {
            context.sendMessage(Message.raw("No tanneries with metadata found. Your tanneries are already good to go!"));
        }
    }

    private int fixContainer(ItemContainer container) {
        int fixed = 0;
        
        for (short i = 0; i < container.getCapacity(); i++) {
            ItemStack itemStack = container.getItemStack(i);
            
            if (itemStack == null || itemStack.isEmpty()) {
                continue;
            }
            
            String itemId = itemStack.getItemId();
            
            if ((TANNERY_ID.equals(itemId) || DOUBLE_TANNERY_ID.equals(itemId)) 
                    && itemStack.getMetadata() != null) {
                
                ItemStack fixedStack = itemStack.withMetadata(null);
                container.setItemStackForSlot(i, fixedStack);
                fixed++;
            }
        }
        
        return fixed;
    }
}
