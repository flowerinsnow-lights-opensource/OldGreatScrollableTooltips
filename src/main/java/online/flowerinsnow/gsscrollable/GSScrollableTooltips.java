package online.flowerinsnow.gsscrollable;

import net.minecraft.command.ICommand;
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
import online.flowerinsnow.gsscrollable.listener.Trigger;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(
        modid = GSScrollableTooltips.MODID,
        version = GSScrollableTooltips.VERSION,
        clientSideOnly = true
)
@SideOnly(Side.CLIENT)
public class GSScrollableTooltips {
    public static final String MODID = "gsscrollable";
    public static final String VERSION = "@VERSION@";
    private static GSConfig config;
    public static int gsScrollY;
    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        File file = event.getSuggestedConfigurationFile();
        logger.debug("Loading configuration " + file.getPath());
        config = new GSConfig(file);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        logger.debug("Registering listeners");
        registerListeners(new GuiListener(), new Trigger());

        logger.debug("Registering commands");
        registerCommands(new GSCommand());
    }

    public static GSConfig getConfig() {
        return config;
    }

    private void registerListeners(Object... listeners) {
        for (Object listener : listeners) {
            logger.debug("Registering listener " + listener.getClass().getName());
            MinecraftForge.EVENT_BUS.register(listener);
        }
    }

    private void registerCommands(ICommand... commands) {
        for (ICommand command : commands) {
            logger.debug("Registering command " + command.getCommandName());
            ClientCommandHandler.instance.registerCommand(command);
        }
    }
}
