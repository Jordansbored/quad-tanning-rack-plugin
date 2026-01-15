package com.example.quadtannery;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;

/**
 * Double/Quad Tannery Plugin
 * 
 * Adds two new processing benches:
 * - Double Tannery: 4 input slots, 4 output slots, 2x speed
 * - Quad Tannery: 8 input slots, 8 output slots, 4x speed
 * 
 * This plugin bundles JSON assets that use Hytale's built-in ProcessingBench system.
 * 
 * Crafting recipes:
 * - 2x Tannery -> Double Tannery (at Workbench)
 * - 2x Double Tannery -> Quad Tannery (at Workbench)
 */
public class QuadTanneryPlugin extends JavaPlugin {

    private static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();

    public QuadTanneryPlugin(@Nonnull JavaPluginInit init) {
        super(init);
        LOGGER.atInfo().log("Double/Quad Tannery plugin loaded - version " + this.getManifest().getVersion().toString());
    }

    @Override
    protected void setup() {
        LOGGER.atInfo().log("Setting up Double/Quad Tannery plugin...");
        
        // Register the command for testing/info
        this.getCommandRegistry().registerCommand(new QuadTanneryCommand());
        
        // The item/block definitions are loaded automatically from the resources folder
        // They use Hytale's built-in ProcessingBench system, so no custom Java code needed
        
        LOGGER.atInfo().log("Double/Quad Tannery plugin setup complete!");
        LOGGER.atInfo().log("Craft: 2x Tannery -> Double Tannery, 2x Double Tannery -> Quad Tannery");
    }
}
