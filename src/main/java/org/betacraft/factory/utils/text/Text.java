package org.betacraft.factory.utils.text;

import org.betacraft.factory.utils.text.enums.DefaultFontInfo;
import org.bukkit.ChatColor;

public class Text {


    /**
     * It parses long numbers.
     * @param value It's a long numbers.
     * @return Decomposes string.
     */
    public static String decompose(Object value) {
        return value != null ? value.toString().replaceAll("(?<=\\d)(?=(\\d\\d\\d)+(?!\\d))", ",") : "0";
    }


    /**
     *
     * @param character Progress bar's character.
     * @param percent From 0 to 100.
     * @param width Determines how wide the progress bar will be.
     * @return Sorts the specified characters.
     */
    public static String progress(char character, double percent, int width) {

        int loop = (int) Math.floor(percent / 2.5);
        StringBuilder list = new StringBuilder();

        for (int i = 0; i < loop && i < width; i++) {
            list.append(character);
        }

        for (int i = 0; i < (width - loop); i++) {
            list.append("§7").append(character);
        }

        System.out.println("merhaba barýþ");
        return list.toString();


    }

    /**
     *
     * @param character Progress bar's character.
     * @param percent From 0 to 100.
     * Width default by 40.
     * @return Sorts the specified characters.
     */
    public static String progress(char character, double percent) {
        return progress(character, percent, 40);
    }

    /**
     *
     * Character default by '?'
     * @param percent From 0 to 100.
     * Width default by 40.
     * @return Sorts the specified characters.
     */
    public static String progress(double percent) {
        return progress('?', percent, 40);
    }


    /**
     *
     * @param text The text you want to convert.
     * @return Text you want to convereted but uncolored.
     */
    public static String uncolored(String text) {
        return text.replace("§", "&")
                .replace("&0", "")
                .replace("&1", "")
                .replace("&2", "")
                .replace("&3", "")
                .replace("&4", "")
                .replace("&5", "")
                .replace("&6", "")
                .replace("&7", "")
                .replace("&8", "")
                .replace("&9", "")
                .replace("&a", "")
                .replace("&b", "")
                .replace("&c", "")
                .replace("&d", "")
                .replace("&e", "")
                .replace("&l", "")
                .replace("&k", "")
                .replace("&n", "")
                .replace("&m", "")
                .replace("&o", "")
                .replace("&f", "")
                .replace("&A", "")
                .replace("&B", "")
                .replace("&C", "")
                .replace("&D", "")
                .replace("&E", "")
                .replace("&F", "")
                .replace("&L", "")
                .replace("&N", "")
                .replace("&M", "")
                .replace("&K", "")
                .replace("&O", "");
    }


    /**
     *
     * @param message Message you want to centered
     * @return Centered text.
     */
    public static String sendCenteredMessage(String message) {

        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for (char c : message.toCharArray()) {
            if (c == '§') {
                previousCode = true;
            } else if (previousCode) {
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            } else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }


        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = 154 - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while (compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        return sb.toString() + message;
    }

}
