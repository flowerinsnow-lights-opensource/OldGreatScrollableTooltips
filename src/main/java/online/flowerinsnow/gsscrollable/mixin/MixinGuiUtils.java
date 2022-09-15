package online.flowerinsnow.gsscrollable.mixin;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.gsscrollable.event.HoveringTextDrawingEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.ArrayList;
import java.util.List;

//@Mixin(GuiUtils.class)
@SideOnly(Side.CLIENT)
public class MixinGuiUtils {
    /**
     * @author flowerinsnow
     * @reason Modifing
     */
//    @Overwrite
    public static void drawHoveringText(List<String> textLines, final int mouseX, final int mouseY, final int screenWidth, final int screenHeight, final int maxTextWidth, FontRenderer font) {
        if (!textLines.isEmpty()) {
            HoveringTextDrawingEvent.Pre preEvent = new HoveringTextDrawingEvent.Pre(textLines, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font);
            if (MinecraftForge.EVENT_BUS.post(preEvent)) {
                return;
            }
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            int tooltipTextWidth = 0;

            for (String textLine : preEvent.getTextLines()) {
                int textLineWidth = preEvent.getFontRenderer().getStringWidth(textLine);

                if (textLineWidth > tooltipTextWidth)
                {
                    tooltipTextWidth = textLineWidth;
                }
            }

            boolean needsWrap = false;

            int titleLinesCount = 1;
            int tooltipX = preEvent.getMouseX() + 12;
            if (tooltipX + tooltipTextWidth + 4 > preEvent.getScreenWidth()) {
                tooltipX = preEvent.getMouseX() - 16 - tooltipTextWidth;
                if (tooltipX < 4) { // if the tooltip doesn't fit on the screen
                    if (preEvent.getMouseX() > preEvent.getScreenWidth() / 2) {
                        tooltipTextWidth = preEvent.getMouseX() - 12 - 8;
                    } else {
                        tooltipTextWidth = preEvent.getScreenWidth() - 16 - preEvent.getMouseX();
                    }
                    needsWrap = true;
                }
            }

            if (preEvent.getMaxTextWidth() > 0 && tooltipTextWidth > preEvent.getMaxTextWidth()) {
                tooltipTextWidth = preEvent.getMaxTextWidth();
                needsWrap = true;
            }

            if (needsWrap) {
                int wrappedTooltipWidth = 0;
                List<String> wrappedTextLines = new ArrayList<String>();
                for (int i = 0; i < preEvent.getTextLines().size(); i++) {
                    String textLine = preEvent.getTextLines().get(i);
                    List<String> wrappedLine = preEvent.getFontRenderer().listFormattedStringToWidth(textLine, tooltipTextWidth);
                    if (i == 0) {
                        titleLinesCount = wrappedLine.size();
                    }

                    for (String line : wrappedLine) {
                        int lineWidth = preEvent.getFontRenderer().getStringWidth(line);
                        if (lineWidth > wrappedTooltipWidth) {
                            wrappedTooltipWidth = lineWidth;
                        }
                        wrappedTextLines.add(line);
                    }
                }
                tooltipTextWidth = wrappedTooltipWidth;
                preEvent.setTextLines(wrappedTextLines);

                if (preEvent.getMouseX() > preEvent.getScreenWidth() / 2) {
                    tooltipX = preEvent.getMouseX() - 16 - tooltipTextWidth;
                } else {
                    tooltipX = preEvent.getMouseX() + 12;
                }
            }

//            int tooltipY = mouseY - 12;
            int tooltipY = preEvent.getMouseY() - 12;
            int tooltipHeight = 8;

            if (preEvent.getTextLines().size() > 1) {
                tooltipHeight += (preEvent.getTextLines().size() - 1) * 10;
                if (preEvent.getTextLines().size() > titleLinesCount) {
                    tooltipHeight += 2; // gap between title lines and next lines
                }
            }

//            if (tooltipY + tooltipHeight + 6 > preEvent.screenHeight) {
//                tooltipY = preEvent.screenHeight - tooltipHeight - 6;
//            }

            final int zLevel = 300;
            final int backgroundColor = 0xF0100010;
            GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 4, tooltipX + tooltipTextWidth + 3, tooltipY - 3, backgroundColor, backgroundColor);
            GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 4, backgroundColor, backgroundColor);
            GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
            GuiUtils.drawGradientRect(zLevel, tooltipX - 4, tooltipY - 3, tooltipX - 3, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
            GuiUtils.drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 3, tooltipY - 3, tooltipX + tooltipTextWidth + 4, tooltipY + tooltipHeight + 3, backgroundColor, backgroundColor);
            final int borderColorStart = 0x505000FF;
            final int borderColorEnd = (borderColorStart & 0xFEFEFE) >> 1 | borderColorStart & 0xFF000000;
            GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3 + 1, tooltipX - 3 + 1, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
            GuiUtils.drawGradientRect(zLevel, tooltipX + tooltipTextWidth + 2, tooltipY - 3 + 1, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3 - 1, borderColorStart, borderColorEnd);
            GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY - 3, tooltipX + tooltipTextWidth + 3, tooltipY - 3 + 1, borderColorStart, borderColorStart);
            GuiUtils.drawGradientRect(zLevel, tooltipX - 3, tooltipY + tooltipHeight + 2, tooltipX + tooltipTextWidth + 3, tooltipY + tooltipHeight + 3, borderColorEnd, borderColorEnd);

            for (int lineNumber = 0; lineNumber < preEvent.getTextLines().size(); ++lineNumber) {
                String line = preEvent.getTextLines().get(lineNumber);
                preEvent.getFontRenderer().drawStringWithShadow(line, (float)tooltipX, (float)tooltipY, -1);

                if (lineNumber + 1 == titleLinesCount) {
                    tooltipY += 2;
                }

                tooltipY += 10;
            }

            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
            RenderHelper.enableStandardItemLighting();
            GlStateManager.enableRescaleNormal();
        }
    }
}
