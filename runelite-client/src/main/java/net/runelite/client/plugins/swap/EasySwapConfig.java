package net.runelite.client.plugins.swap;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.plugins.util.*;

@ConfigGroup("easyswap")
public interface EasySwapConfig extends Config {
//test

    @ConfigItem(
            keyName = "swapSmithing",
            name = "Swap Smithing",
            description = "Enables swapping of smith-1 and smith-all options.",
            position = 1
    )

    default boolean getSwapSmithing() {
        return false;
    }

    @ConfigItem(
            keyName = "swapTanning",
            name = "Swap Tanning",
            description = "Enables swapping of tan-1 and tan-all options.",
            position = 2
    )

    default boolean getSwapTanning() {
        return false;
    }

    @ConfigItem(
            keyName = "swapCrafting",
            name = "Swap Crafting",
            description = "Enables swapping of Make-1 and Make-all options.",
            position = 3
    )

    default boolean getSwapCrafting() {
        return false;
    }

    @ConfigItem(
            keyName = "swapArdougneCape",
            name = "Swap Ardougne Cape",
            description = "Enables swapping of teleport and wear.",
            position = 4
    )

    default boolean getSwapArdougneCape() {
        return false;
    }

    @ConfigItem(
            keyName = "swapSawmill",
            name = "Swap Sawmill Operator",
            description = "Makes Buy-plank the default option on the sawmill operator.",
            position = 5
    )

    default boolean getSwapSawmill() {
        return false;
    }

    @ConfigItem(
            keyName = "swapSawmillPlanks",
            name = "Swap Buy Planks",
            description = "Makes Buy All the default option in buy planks.",
            position = 6
    )

    default boolean getSwapSawmillPlanks() {
        return false;
    }

    @ConfigItem(
            keyName = "swapPuroPuro",
            name = "Swap Puro Puro Wheat",
            description = "",
            position = 7
    )

    default boolean getSwapPuro() {
        return false;
    }

    @ConfigItem(
            keyName = "swapCake",
            name = "Swap Dwarf Rock Cake",
            description = "Swaps the left click option on rock cakes from eat to guzzle",
            position = 8
    )

    default boolean getSwapCake() {
        return false;
    }

    @ConfigItem(
            keyName = "swapFish",
            name = "Drop Barbarian Fish",
            description = "Left click option to drop fish",
            position = 9
    )

    default boolean getSwapFish() {
        return false;
    }

    @ConfigItem(
            keyName = "swapIron",
            name = "Drop Iron/granite ore",
            description = "Left click option to drop Iron ore or granite",
            position = 10
    )

    default boolean getSwapIron() {
        return false;
    }
    @ConfigItem(
            keyName = "swapLogs",
            name = "Drop Teak/Redwood logs",
            description = "Left click option to drop teak/redwood logs",
            position = 11
    )

    default boolean getTreeLogs() {
        return false;
    }

    @ConfigItem(
            keyName = "swapConCape",
            name = "Swap Construction Cape",
            description = "Sets left click to teleport to POH",
            position = 12
    )

    default boolean getSwapConCape() {
        return false;
    }

    @ConfigItem(
            keyName = "swapCraftCape",
            name = "Swap Crafting Cape",
            description = "Sets left click to teleport to Crafting Guild",
            position = 13
    )

    default boolean getSwapCraftCape() {
        return false;
    }

    @ConfigItem(
            keyName = "swapOfferEss",
            name = "Swap Offer Essence",
            description = "Swaps the left click option for offering pure essence in a trade",
            position = 14
    )

    default boolean getSwapEss() {
        return false;
    }

    @ConfigItem(
            keyName = "BindingNecklaces",
            name = "Examine Binding Necklace",
            description = "Examine Binding Necklace on Left click",
            position = 15
    )

    default boolean getBindingNeck() {
        return false;
    }

    @ConfigItem(
            keyName = "Dark mage",
            name = "Swap Dark mage",
            description = "Swap Cast on npc contact to Dark mage",
            position = 16
    )

    default boolean getswapDarkmage() {
        return false;
    }

    @ConfigItem(
            keyName = "disableCraftAltar",
            name = "Disable Crafting Altar",
            description = "Prevents accidental crafting of fire runes",
            position = 17
    )

    default boolean disableCraftAltar(){
        return false;
    }

    @ConfigItem(
            keyName = "swapBoxTrap",
            name = "Hold shift to walk here",
            description = "Hold shift to walk here,example box trap or bosses.",
            position = 18
    )

    default boolean getSwapBoxTrap() {
        return false;
    }


    @ConfigItem(
            keyName = "Trade with",
            name = "Hold shift to trade with players",
            description = "Hold down shift to send a trade request to other players",
            position = 19
    )

    default boolean getTradewith() {
        return false;
    }

    @ConfigItem(
            keyName = "swapEssencePounch",
            name = "Fill pouches inside the bank",
            description = "Fill Pouches inside bank interface",
            position = 20
    )

    default boolean getSwapEssencePouch() {
        return false;
    }

    //----------------------------------------------Teleports----------------------------------------------------------//


    @ConfigItem(
            keyName = "swapGamesNecklace",
            name = "Swap Games Necklace",
            description = "",
            position = 21
    )
    default boolean getGamesNecklace() {
        return false;
    }

    @ConfigItem(
            keyName = "gamesNecklaceMode",
            name = "Mode",
            description = "",
            position = 22
    )

    default GamesNecklaceMode getGamesNecklaceMode() {
        return GamesNecklaceMode.BURTHORPE;
    }
    @ConfigItem(
            keyName = "sgamesNecklaceMode",
            name = "Shift Mode",
            description = "",
            position = 23
    )

    default GamesNecklaceMode getSGamesNecklaceMode() {
        return GamesNecklaceMode.BARBARIAN_OUTPOST;
    }

    @ConfigItem(
            keyName = "swapDuelingRing",
            name = "Swap Dueling Ring",
            description = "",
            position = 24
    )

    default boolean getDuelingRing() {
        return false;
    }

    @ConfigItem(
            keyName = "duelingRingMode",
            name = "Mode",
            description = "",
            position = 25
    )

    default DuelingRingMode getDuelingRingMode() {
        return DuelingRingMode.DUEL_ARENA;
    }

    @ConfigItem(
            keyName = "shiftduelingRingMode",
            name = "Shift Mode",
            description = "",
            position = 26
    )

    default DuelingRingMode getSDuelingRingMode() {
        return DuelingRingMode.CASTLE_WARS;
    }

    @ConfigItem(
            keyName = "swapGlory",
            name = "Swap Glory",
            description = "",
            position = 27
    )

    default boolean getGlory() {
        return false;
    }

    @ConfigItem(
            keyName = "gloryMode",
            name = "Mode",
            description = "",
            position = 28
    )

    default GloryMode getGloryMode() {
        return GloryMode.EDGEVILLE;
    }
    @ConfigItem(
            keyName = "sgloryMode",
            name = "Shift Mode",
            description = "",
            position = 29
    )

    default GloryMode getSGloryMode() {
        return GloryMode.KARAMJA;
    }

    @ConfigItem(
            keyName = "swapDigsite",
            name = "Swap Digsite pendant",
            description = "",
            position = 30
    )

    default boolean getDigsite() {
        return false;
    }

    @ConfigItem(
            keyName = "digsiteMode",
            name = "Mode",
            description = "",
            position = 31
    )

    default DigsiteMode getDigsitemode() {
        return DigsiteMode.DIGSITE;
    }
    @ConfigItem(
            keyName = "sdigsiteMode",
            name = "Shift Mode",
            description = "",
            position = 32
    )

    default DigsiteMode getSDigsitemode() {
        return DigsiteMode.FOSSIL_ISLAND;
    }

    @ConfigItem(
            keyName = "Max capee",
            name = "Max cape",
            description = "",
            position = 33
    )

    default boolean getMaxcapee() {
        return false;
    }

    @ConfigItem(
            keyName = "MaxcapeMode",
            name = "Mode",
            description = "",
            position = 34
    )

    default MaxcapeMode getMaxcapeMode() {
        return MaxcapeMode.WARRIORS_GUILD;
    }

    @ConfigItem(
            keyName = "SMaxcapeMode",
            name = "Shift Mode",
            description = "",
            position = 35
    )

    default MaxcapeMode getSMaxcapeMode() {
        return MaxcapeMode.FISHING_TELEPORT;
    }
}
