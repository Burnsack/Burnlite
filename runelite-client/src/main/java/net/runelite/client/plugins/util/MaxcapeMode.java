package net.runelite.client.plugins.util;


public enum MaxcapeMode
{
    WARRIORS_GUILD("Warriors' Guild"),
    FISHING_TELEPORT("Fishing Teleport"),
    CRAFTING_GUILD("Crafting Guild"),
    TELE_TO_POH("Tele to POH"),
    POH_PORTALS("POH Portals"),
    OTHER_TELEPORTS("Other Teleports"),
    SPELLBOOK("Spellbook"),
    FEATURES("Features");

    private final String name;

    MaxcapeMode(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}