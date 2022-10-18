package online.flowerinsnow.gsscrollable.mixin;

import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.gsscrollable.GSScrollableTooltips;
import online.flowerinsnow.gsscrollable.event.HoveringTextDrawingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GuiUtils.class)
@SideOnly(Side.CLIENT)
public class MixinGuiUtils {
    @Inject(
            method = "drawHoveringText",
            at = @At("HEAD"),
            cancellable = true,
            remap = false
    )
    private static void preHoveringTextDraw(List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font, CallbackInfo ci) {
        if (MinecraftForge.EVENT_BUS.post(new HoveringTextDrawingEvent.Pre(
                textLines, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font)))  {
            ci.cancel();
        }
    }

    @Inject(
            method = "drawHoveringText",
            at = @At("RETURN"),
            remap = false
    )
    private static void postHoveringTextDraw(List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new HoveringTextDrawingEvent.Post(
                textLines, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font
        ));
    }

    @ModifyVariable(
            method = "drawHoveringText",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraftforge/fml/client/config/GuiUtils;drawGradientRect(IIIIIII)V",
                    ordinal = 0
            ),
            index = 11,
            remap = false
    )
    private static int modifyTooltipY(int value) {
        return value + GSScrollableTooltips.gsScrollY;
    }
}
