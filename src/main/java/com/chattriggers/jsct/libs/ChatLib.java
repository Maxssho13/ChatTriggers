package com.chattriggers.jsct.libs;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ChatLib {
    /**
     * Add a chat message to chat
     * @param message to add
     */
    public static void chat(String message) {
        if (Minecraft.getMinecraft().thePlayer == null) return;

        ChatComponentText cct = new ChatComponentText(addColor(message));
        Minecraft.getMinecraft().thePlayer.addChatMessage(cct);
    }

    /**
     * Say chat message
     * @param message to say
     */
    public static void say(String message) {
        //TODO: Add checking for creator
        Minecraft.getMinecraft().thePlayer.sendChatMessage(message);
    }

    /**
     * Run command
     * @param command to run
     */
    public static void command(String command) {
        //TODO: Add checking for creator
        if (!isAllowedCommand(command)) return;

        Minecraft.getMinecraft().thePlayer.sendChatMessage("/" + command);
    }

    /**
     * Clear chat
     */
    public static void clearChat() {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().deleteChatLine(0);
    }

    private static boolean isAllowedCommand(String command) {
        if (Minecraft.getMinecraft().isSingleplayer())
            return true;

        switch (command.toLowerCase()) {
            case("who"):
            case("whereami"):
            case("wtfmap"):
                return true;
            default:
                return false;
        }
    }

    public static String addColor(String message) {
        if (message == null) {
            message = "null";
        }

        return message.replace("&0", EnumChatFormatting.BLACK.toString())
                .replace("&1", EnumChatFormatting.DARK_BLUE.toString())
                .replace("&2", EnumChatFormatting.DARK_GREEN.toString())
                .replace("&3", EnumChatFormatting.DARK_AQUA.toString())
                .replace("&4", EnumChatFormatting.DARK_RED.toString())
                .replace("&5", EnumChatFormatting.DARK_PURPLE.toString())
                .replace("&6", EnumChatFormatting.GOLD.toString())
                .replace("&7", EnumChatFormatting.GRAY.toString())
                .replace("&8", EnumChatFormatting.DARK_GRAY.toString())
                .replace("&9", EnumChatFormatting.BLUE.toString())
                .replace("&a", EnumChatFormatting.GREEN.toString())
                .replace("&b", EnumChatFormatting.AQUA.toString())
                .replace("&c", EnumChatFormatting.RED.toString())
                .replace("&d", EnumChatFormatting.LIGHT_PURPLE.toString())
                .replace("&e", EnumChatFormatting.YELLOW.toString())
                .replace("&f", EnumChatFormatting.WHITE.toString())
                .replace("&k", EnumChatFormatting.OBFUSCATED.toString())
                .replace("&l", EnumChatFormatting.BOLD.toString())
                .replace("&m", EnumChatFormatting.STRIKETHROUGH.toString())
                .replace("&n", EnumChatFormatting.UNDERLINE.toString())
                .replace("&o", EnumChatFormatting.ITALIC.toString())
                .replace("&r", EnumChatFormatting.RESET.toString());
    }
}
