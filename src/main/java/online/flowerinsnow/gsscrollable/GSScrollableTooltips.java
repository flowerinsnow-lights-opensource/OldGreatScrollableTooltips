package online.flowerinsnow.gsscrollable;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.gsscrollable.command.GSCommand;
import online.flowerinsnow.gsscrollable.config.GSConfig;
import online.flowerinsnow.gsscrollable.listener.GuiListener;

@Mod(
        modid = GSScrollableTooltips.MODID,
        version = GSScrollableTooltips.VERSION,
        clientSideOnly = true
)
@SideOnly(Side.CLIENT)
public class GSScrollableTooltips {
    public static final String MODID = "gsscrollable";
    public static final String VERSION = "1.8.9-1.0";
    private static GSConfig config;
    public static int gsScrollY;

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
