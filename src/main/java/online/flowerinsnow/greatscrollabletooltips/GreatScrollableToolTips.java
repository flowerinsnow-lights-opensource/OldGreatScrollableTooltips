package online.flowerinsnow.greatscrollabletooltips;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

@Mod(modid = GreatScrollableToolTips.MODID, name = GreatScrollableToolTips.NAME, version = GreatScrollableToolTips.VERSION, clientSideOnly = true)
@SideOnly(Side.CLIENT)
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
