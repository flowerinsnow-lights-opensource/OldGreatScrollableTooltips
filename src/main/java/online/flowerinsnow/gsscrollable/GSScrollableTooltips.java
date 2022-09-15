package online.flowerinsnow.gsscrollable;

import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.gsscrollable.command.GSCommand;
import online.flowerinsnow.gsscrollable.config.GSConfig;
import online.flowerinsnow.gsscrollable.listener.GuiListener;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;

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
    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new GSConfig(event.getSuggestedConfigurationFile());
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new GuiListener());
        ClientCommandHandler.instance.registerCommand(new GSCommand());
        for (Method method : GuiUtils.class.getDeclaredMethods()) {
            logger.info(method.getName());
            for (Class<?> type : method.getParameterTypes()) {
                logger.info("\t" + type.getName());
            }
            logger.info("");
        }
    }

    public static GSConfig getConfig() {
        return config;
    }
}
