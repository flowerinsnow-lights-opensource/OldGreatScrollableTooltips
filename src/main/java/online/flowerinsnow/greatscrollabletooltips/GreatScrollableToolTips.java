package online.flowerinsnow.greatscrollabletooltips;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = GreatScrollableToolTips.MODID, name = GreatScrollableToolTips.NAME, version = GreatScrollableToolTips.VERSION)
public class GreatScrollableToolTips
{
    public static final String MODID = "g_scrollable_tt";
    public static final String NAME = "GreatScrollableToolTips";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    }

    public static Logger getLogger() {
        return logger;
    }
}
