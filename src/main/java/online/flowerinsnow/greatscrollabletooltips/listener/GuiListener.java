package online.flowerinsnow.greatscrollabletooltips.listener;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableToolTips;
import org.lwjgl.input.Mouse;

@SideOnly(Side.CLIENT)
public class GuiListener {
    private GuiScreen old;
    private long lastEventNanoSec = -1L;
    @SubscribeEvent
    public void onRenderTooltip(RenderTooltipEvent.Pre event) {
        GuiScreen gs = Minecraft.getMinecraft().currentScreen;
        if (GreatScrollableToolTips.getConfig().disableInCreative && gs instanceof GuiContainerCreative) {
            return;
        }
        int eDWheel = Mouse.getEventDWheel();
        long eNanoSec = Mouse.getEventNanoseconds();
        if (eDWheel != 0 && eNanoSec != lastEventNanoSec) {
            lastEventNanoSec = eNanoSec;
            GreatScrollableToolTips.gsScrollY += (int) (Mouse.getEventDWheel() * 0.01 * GreatScrollableToolTips.getConfig().sensitivity);
        }
        if (GreatScrollableToolTips.gsScrollY != 0) {
            event.setY(event.getY() + GreatScrollableToolTips.gsScrollY);
        }
    }

    @SubscribeEvent
    public void onGuiClose(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            GuiScreen gs = Minecraft.getMinecraft().currentScreen;
            if (this.old != gs) {
                GreatScrollableToolTips.gsScrollY = 0;
                this.old = gs;
            }
        }
    }
}
