package com.example.quadtannery;

import com.hypixel.hytale.protocol.GameMode;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.CommandBase;

import javax.annotation.Nonnull;

/**
 * Info command for the Double/Quad Tannery plugin.
 */
public class QuadTanneryCommand extends CommandBase {

    public QuadTanneryCommand() {
        super("tanneries", "Info about Double/Quad Tannery plugin");
        this.setPermissionGroup(GameMode.Adventure);
    }

    @Override
    protected void executeSync(@Nonnull CommandContext ctx) {
        ctx.sendMessage(Message.raw("=== Double/Quad Tannery Plugin ==="));
        ctx.sendMessage(Message.raw("Double Tannery: 2x speed, 4 input, 4 output slots"));
        ctx.sendMessage(Message.raw("Quad Tannery: 4x speed, 8 input, 8 output slots"));
        ctx.sendMessage(Message.raw("Craft: 2x Tannery -> Double, 2x Double -> Quad"));
        ctx.sendMessage(Message.raw(""));
        ctx.sendMessage(Message.raw("Note: Quad Tannery was designed for more output slots, but the"));
        ctx.sendMessage(Message.raw("vanilla UI has a slot limit. We've maxed it out at 8 slots."));
    }
}
