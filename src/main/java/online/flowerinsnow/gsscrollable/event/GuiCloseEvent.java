package online.flowerinsnow.gsscrollable.event;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCloseEvent extends Event {
    public final GuiScreen old;

    public GuiCloseEvent(GuiScreen old) {
        this.old = old;
    }
}
