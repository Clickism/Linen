/*
 * Copyright 2026 Clickism
 * Released under the GNU General Public License 3.0.
 * See LICENSE.md for details.
 */

package de.clickism.linen.core.message.colorizer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A subset of MiniMessage.
 * <p>
 * Supports basic color and formatting tags.
 */
public class MiniMiniMessage {
    private MiniMiniMessage() {
        // Utility class
    }

    private static final Pattern TAG_PATTERN = Pattern.compile("<(/?)(\\w+)>");

    private static final Map<String, String> TAGS = new ConcurrentHashMap<>(Map.ofEntries(
            Map.entry("black", "§0"),
            Map.entry("dark_blue", "§1"),
            Map.entry("dark_green", "§2"),
            Map.entry("dark_aqua", "§3"),
            Map.entry("dark_red", "§4"),
            Map.entry("dark_purple", "§5"),
            Map.entry("gold", "§6"),
            Map.entry("gray", "§7"),
            Map.entry("dark_gray", "§8"),
            Map.entry("blue", "§9"),
            Map.entry("green", "§a"),
            Map.entry("aqua", "§b"),
            Map.entry("red", "§c"),
            Map.entry("light_purple", "§d"),
            Map.entry("yellow", "§e"),
            Map.entry("white", "§f"),

            Map.entry("bold", "§l"),
            Map.entry("italic", "§o"),
            Map.entry("underlined", "§n"),
            Map.entry("strikethrough", "§m"),
            Map.entry("obfuscated", "§k"),
            Map.entry("reset", "§r")
    ));

    /**
     * Parses a MiniMiniMessage formatted string into a legacy formatted string.
     *
     * @param input the MiniMiniMessage formatted string
     * @return the legacy formatted string
     */
    public static String parse(String input) {
        StringBuilder result = new StringBuilder();
        Matcher matcher = TAG_PATTERN.matcher(input);

        Deque<String> styleStack = new ArrayDeque<>();
        int lastEnd = 0;

        while (matcher.find()) {
            // Add text before the tag
            if (matcher.start() > lastEnd) {
                result.append(input, lastEnd, matcher.start());
            }

            boolean isClosing = !matcher.group(1).isEmpty();
            String tag = matcher.group(2).toLowerCase();

            if (!isClosing) {
                // Opening tag
                String code = TAGS.get(tag);
                if (code != null) {
                    result.append(code);
                    styleStack.push(tag);
                }
            } else {
                // Closing tag
                if (!styleStack.isEmpty() && styleStack.peek().equals(tag)) {
                    styleStack.pop();
                    result.append("§r"); // Reset formatting
                    // Reapply remaining styles in stack
                    for (String s : styleStack) {
                        String code = TAGS.getOrDefault(s, "");
                        if (!code.isEmpty()) result.append(code);
                    }
                }
            }

            lastEnd = matcher.end();
        }

        // Append remaining text
        if (lastEnd < input.length()) {
            result.append(input.substring(lastEnd));
        }

        return result.toString();
    }
}
