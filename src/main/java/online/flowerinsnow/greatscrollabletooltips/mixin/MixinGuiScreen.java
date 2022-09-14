package online.flowerinsnow.greatscrollabletooltips.mixin;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.GreatScrollableToolTips;
import online.flowerinsnow.greatscrollabletooltips.reflect.Reflects;
import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiScreen.class)
@SideOnly(Side.CLIENT)
public class MixinGuiScreen {
    private final GuiScreen this_ = (GuiScreen) (Object) this;
    @Inject(method = {"handleMouseInput"}, at = @At("RETURN"))
    public void handleMouseInput(CallbackInfo ci) {
        if (this_ instanceof GuiContainer) {
            GuiContainer container = (GuiContainer) this_;
            int i = Mouse.getEventDWheel();
            Slot hoveredSlot = (Slot) Reflects.getFieldValue(Reflects.hoveredSlot, container);
            if (hoveredSlot != null && hoveredSlot.getHasStack()) {
                GreatScrollableToolTips.gsScrollY += i;
            }
        }
    }

    /**
     * @author flowerinsnow
     * @reason Modify needed
     */
    @Inject(method = {"renderToolTip"}, at = @At("HEAD"))
    protected void renderToolTip(ItemStack stack, int x, int y, CallbackInfo ci) {
        y += GreatScrollableToolTips.gsScrollY;
    }

    @Inject(method = {"onGuiClosed"}, at = @At("HEAD"))
    public void onGuiClosed(CallbackInfo ci) {
        GreatScrollableToolTips.gsScrollY = 0;
    }
}
