package online.flowerinsnow.greatscrollabletooltips;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.command.GSCommand;
import online.flowerinsnow.greatscrollabletooltips.config.GSConfig;
import online.flowerinsnow.greatscrollabletooltips.listener.GuiListener;

@Mod(
        modid = GreatScrollableToolTips.MODID,
        name = GreatScrollableToolTips.NAME,
        version = GreatScrollableToolTips.VERSION,
        clientSideOnly = true
)
@SideOnly(Side.CLIENT)
public class GreatScrollableToolTips {
    public static final String MODID = "g_scrollable_tt";
    public static final String NAME = "GreatScrollableToolTips";
    public static final String VERSION = "1.0";
    private static GSConfig config;
    public static int gsScrollY = 0;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new GSConfig(event.getSuggestedConfigurationFile());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new GuiListener());
        ClientCommandHandler.instance.registerCommand(new GSCommand());
    }

    public static GSConfig getConfig() {
        return config;
    }
}
