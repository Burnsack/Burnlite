package net.runelite.client.plugins.EasyDrop;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;


@ConfigGroup("easydrop")
public interface EasyDropConfig extends Config {
    @ConfigItem(
            keyName = "Use",
            name = "Left click Use",
            description = "",
            position = 0
    )

    default boolean getUse() {
        return true;
    }

    @ConfigItem(
            keyName = "NameUse",
            name = "Items",
            description = "",
            position = 1
    )

    default String getNameUse() {
        return "";
    }

    @ConfigItem(
            keyName = "Drop",
            name = "Left click Drop",
            description = "",
            position = 2
    )

    default boolean getDrop() {
        return true;
    }

    @ConfigItem(
            keyName = "NameDrop",
            name = "Items",
            description = "",
            position = 3
    )

    default String getNameDrop() {
        return "";
    }

}