package online.flowerinsnow.gsscrollable.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.gsscrollable.event.ClientTickEndEvent;
import online.flowerinsnow.gsscrollable.event.GuiCloseEvent;

@SideOnly(Side.CLIENT)
public class Trigger {
    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            MinecraftForge.EVENT_BUS.post(new ClientTickEndEvent());
        }
    }

    private GuiScreen old;
    @SubscribeEvent
    public void onTickEnd(ClientTickEndEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if  (mc.currentScreen != old) {
            MinecraftForge.EVENT_BUS.post(new GuiCloseEvent(old));
            old = mc.currentScreen;
        }
    }
}
