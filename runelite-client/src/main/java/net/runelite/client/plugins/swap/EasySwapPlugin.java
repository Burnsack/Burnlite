package net.runelite.client.plugins.swap;

import com.google.inject.Provides;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.FocusChanged;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.util.Swapper;
import net.runelite.client.util.Text;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameStateChanged;
import static net.runelite.api.MenuAction.MENU_ACTION_DEPRIORITIZE_OFFSET;
import static net.runelite.api.MenuAction.WALK;
import net.runelite.api.MenuEntry;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.runelite.api.ObjectID.PORTAL_4525;

@PluginDescriptor(
        name="EasySwap",
        description = "EasySwap.",
        tags = {"EasySwap", "easy"},
        enabledByDefault = false
)

@Slf4j
public class EasySwapPlugin extends Plugin {

    private static final int PURO_PURO_REGION_ID = 10307;

    private Swapper swapper = new Swapper();
    private boolean inHouse = false;
    @Inject
    private ShiftClickInputListener inputListener;
    @Inject
    private KeyManager keyManager;
    @Setter
    private boolean shiftModifier = false;
    @Inject
    private Client client;

    @Inject
    private EasySwapConfig config;

    private boolean shiftToggle = false;

    @Provides
    EasySwapConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(EasySwapConfig.class);
    }

    @Override
    public void startUp() {
        this.keyManager.registerKeyListener(this.inputListener);
        log.debug("EasySwap Started.");
    }

    @Override
    public void shutDown() {
        log.debug("EasySwap Stopped.");
        this.keyManager.unregisterKeyListener(this.inputListener);
    }
    @Subscribe
    public void onFocusChanged(FocusChanged event) {
        if (!event.isFocused()) {
            this.shiftModifier = false;
        }

    }
    public void toggleShift ( ) {
        shiftToggle = !shiftToggle;
    }
    @Subscribe
    public void onMenuEntryAdded(MenuEntryAdded event) {

        if (client.getGameState() != GameState.LOGGED_IN) {
            return;
        }

        Widget loginScreenOne = client.getWidget(WidgetInfo.LOGIN_CLICK_TO_PLAY_SCREEN);
        Widget loginScreenTwo = client.getWidget(WidgetInfo.LOGIN_CLICK_TO_PLAY_SCREEN_MESSAGE_OF_THE_DAY);

        if (loginScreenOne != null || loginScreenTwo != null) {
            return;
        }

        final String option = Text.removeTags(event.getOption()).toLowerCase();
        final String target = Text.removeTags(event.getTarget()).toLowerCase();

        swapper.setEntries(client.getMenuEntries());

        if (config.getSwapPuro() && isPuroPuro()) {
            if (event.getType() == WALK.getId()) {
                swapper.deprioritizeWalk();
            } else if (option.equalsIgnoreCase("examine")) {
                swapper.markForSwap("push-through", option, target);
            } else if (option.equalsIgnoreCase("use")) {
                swapper.markForSwap("escape", option, target);
            }
        }

        if (config.getSwapSmithing()) {
            if (option.equalsIgnoreCase("Smith 1")) {
                swapper.markForSwap("Smith All", option, target);
            } else if (option.equalsIgnoreCase("Smith 1 Set")) {
                swapper.markForSwap("Smith All Sets", option, target);
            }
        }

        if (config.getSwapTanning() && option.equalsIgnoreCase("Tan 1")) {
            swapper.markForSwap("Tan All", option, target);
        }

        if (config.getSwapCrafting()) {
            switch (option) {
                case "Make-1":
                    swapper.markForSwap("Make-All", option, target);
                    break;
                case "Craft 1":
                    swapper.markForSwap("Craft All", option, target);
                    break;
                default:
                    break;
            }
        }

        if (config.getBindingNeck()) {
            if (target.equalsIgnoreCase("Binding necklace") && option.equalsIgnoreCase("Wear")) {
                swapper.markForSwap("Use",option,target);
            }
        }

        if (config.getSwapSawmill() && target.equalsIgnoreCase("Sawmill operator")) {
            swapper.markForSwap("Buy-plank", option, target);
        }

        if (config.getSwapSawmillPlanks() && option.equalsIgnoreCase("Buy 1")) {
            swapper.markForSwap("Buy All", option, target);
        }

        if (option.equalsIgnoreCase("Clear-All") && target.equalsIgnoreCase("bank Filler")) {
            swapper.markForSwap("Clear", option, target);
        }

        if (target.toLowerCase().contains("ardougne cloak") && config.getSwapArdougneCape()) {
            swapper.markForSwap("Kandarin Monastery", option, target);
            swapper.markForSwap("Monastery Teleport", option, target);
        }

        if (config.getSwapCake()) {
            if (target.equalsIgnoreCase("Dwarven rock cake") && option.equalsIgnoreCase("Eat")) {
                swapper.markForSwap("Guzzle",option,target);
            }
        }
        if (config.getswapDarkmage()) {
            if (option.equalsIgnoreCase("cast") && target.equalsIgnoreCase("npc contact")){
                swapper.markForSwap("dark mage",option, target);
            }
        }

        if (config.getSwapFish()) {
            if (!shiftModifier) {
                if (isBarbFish(target) && option.equalsIgnoreCase("Use")) {
                    swapper.markForSwap("Drop",option,target);
                }
            }
        }

        if (config.getSwapIron()) {
            if ((target.equalsIgnoreCase("iron ore") && option.equalsIgnoreCase("use"))) {
                swapper.markForSwap("drop",option,target);
            }
        }

        if (config.getSwapIron()) {
            if ((target.toLowerCase().contains("granite") && option.equalsIgnoreCase("use"))) {
                swapper.markForSwap("drop",option,target);
            }
        }

        if (config.getTreeLogs()) {
            if ((target.equalsIgnoreCase("Teak logs") ||  target.equalsIgnoreCase("Redwood logs")) && option.equalsIgnoreCase("use")) {
                swapper.markForSwap("drop",option,target);
            }
        }

        if (config.getSwapConCape()) {
            if ((target.equalsIgnoreCase("Construct. cape") ||  target.equalsIgnoreCase("Construct. cape(t)")) && option.equalsIgnoreCase("Remove")) {
                swapper.markForSwap("Tele to POH",option,target);
            }
        }

        if (config.getSwapCraftCape()) {
            if ((target.equalsIgnoreCase("Crafting cape") ||  target.equalsIgnoreCase("Crafting cape(t)")) && option.equalsIgnoreCase("remove")) {
                swapper.markForSwap("Teleport",option,target);
            }
        }

        if (config.getSwapEss()) {
            if (option.equalsIgnoreCase("Offer") && target.equalsIgnoreCase("pure essence")){
                swapper.markForSwap("Offer-All",option, target);
            }
        }
            if (config.getSwapEss()) {
                if (option.equalsIgnoreCase("Offer") && target.equalsIgnoreCase("earth talisman")){
                    swapper.markForSwap("Offer-All",option, target);
                }
        }
        if (config.disableCraftAltar()) {
            if (target.equalsIgnoreCase("Altar")) {
                swapper.setEntries(new MenuEntry[] {});
            }
        }

        if (config.getSwapEssencePouch()) {
            if (option.equals("deposit-all") && target.contains("pouch")){
                swapper.markForSwap("fill",option, target);
            }
        }
        //--------------------------------------------------------------------------------------------------------//

        if (config.getGamesNecklace()) {
            if (target.toLowerCase().contains("games necklace") ) {
                if(shiftModifier){
                    swapper.markForSwap(config.getSGamesNecklaceMode().toString(), option, target);
                }else{
                    swapper.markForSwap(config.getGamesNecklaceMode().toString(), option, target);
                }

            }
        }

        if (config.getDuelingRing()) {
            if (target.toLowerCase().contains("ring of dueling")) {
                if(shiftModifier){
                    swapper.markForSwap(config.getSDuelingRingMode().toString(), option, target);
                }else{
                    swapper.markForSwap(config.getDuelingRingMode().toString(), option, target);
                }

            }
        }

        if (config.getGlory()) {
            if (target.toLowerCase().contains("amulet of glory") || target.toLowerCase().contains("amulet of eternal glory")) {
                if(shiftModifier){
                    swapper.markForSwap(config.getSGloryMode().toString(), option, target);
                }else{
                    swapper.markForSwap(config.getGloryMode().toString(), option, target);
                }

            }
        }
        if(shiftModifier){
            if (config.getSwapBoxTrap()) {
                if (event.getType() < WALK.getId())
                {
                    MenuEntry[] menuEntries = client.getMenuEntries();
                    MenuEntry menuEntry = menuEntries[menuEntries.length - 1];
                    menuEntry.setType(event.getType() + MENU_ACTION_DEPRIORITIZE_OFFSET);
                    client.setMenuEntries(menuEntries);
                }
                return;
            }
        }

        if(shiftModifier){

            if (config.getTradewith() && option.equalsIgnoreCase("follow")) {
                List<MenuEntry> tradeFix = new ArrayList<>();
                MenuEntry[] menuEntries = swapper.getEntries();
                int i = 0;
                for (MenuEntry m : menuEntries) {
                    if (m.getOption().contains("Trade")) {
                        tradeFix.add(m);
                    }
                }
                swapper.setEntries(tradeFix.toArray(new MenuEntry[] {}));
            }}
        if (config.getDigsite()) {
            if (target.toLowerCase().contains("Digsite pendant") || target.toLowerCase().contains("digsite pendant")) {
                if(shiftModifier){
                    swapper.markForSwap(config.getSDigsitemode().toString(), option, target);
                }else{
                    swapper.markForSwap(config.getDigsitemode().toString(), option, target);
                }

            }
        }
        if (config.getMaxcapee()) {
            if (target.toLowerCase().contains("max capet") || target.toLowerCase().contains("max hood")) {
                if(shiftModifier){
                    swapper.markForSwap(config.getSMaxcapeMode().toString(), option, target);
                }else{
                    swapper.markForSwap(config.getMaxcapeMode().toString(), option, target);
                }

            }
        }

        swapper.startSwap();
        client.setMenuEntries(swapper.getEntries());
    }

    private boolean isEssencePouch(String target) {
        return (target.equalsIgnoreCase("Small Pouch") || target.equalsIgnoreCase("Medium Pouch") || target.equalsIgnoreCase("Large Pouch") || target.equalsIgnoreCase("Giant Pouch"));
    }

    private boolean isBarbFish(String target) {
        return (target.equalsIgnoreCase("Leaping trout") || target.equalsIgnoreCase("Leaping salmon") || target.equalsIgnoreCase("Leaping sturgeon"));
    }

    @Subscribe
    public void onGameObjectSpawned(GameObjectSpawned event)
    {
        final GameObject gameObject = event.getGameObject();
        if (PORTAL_4525 == gameObject.getId())
        {
            this.inHouse = true;
        }
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event)
    {
        if (event.getGameState() == GameState.LOADING)
        {
            this.inHouse = false;
        }
    }

    private boolean isHouse() {
        return this.inHouse;
    }

    private boolean isPuroPuro() {
        Player player = client.getLocalPlayer();

        if (player == null) {
            return false;
        } else {
            WorldPoint location = player.getWorldLocation();
            return location.getRegionID() == PURO_PURO_REGION_ID;
        }
    }

}