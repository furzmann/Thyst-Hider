package com.example.examplemod;


import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ThystHider extends CommandBase {
    public static boolean hideThysts = false;

    @Override
    public String getCommandName() {
        return "hide";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/hide";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        hideThysts = !hideThysts;
        if(hideThysts) {
            sender.addChatMessage(new ChatComponentText("Hiding Thysts!"));
        } else
            sender.addChatMessage(new ChatComponentText("Showing Thysts!"));
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @SubscribeEvent
    public void entityRender(RenderLivingEvent.Pre event) {
        if(hideThysts && event.entity instanceof EntityEndermite) {
            event.setCanceled(true);
            event.entity.setDead();
        }
    }
}