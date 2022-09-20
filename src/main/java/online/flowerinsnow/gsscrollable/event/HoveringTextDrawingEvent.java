package online.flowerinsnow.gsscrollable.event;

import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * 渲染物品信息时触发
 * 需要考虑多个方面
 * 初次位置决定取决于鼠标位置
 * 随后会判断是否超出了屏幕外
 *
 * 修改nature表示是否干涉判断屏幕以内
 * 修改mouseX和mouseY表示修改首次
 */
@SideOnly(Side.CLIENT)
public class HoveringTextDrawingEvent extends Event {
    /**
     * 需要渲染的文字
     */
    protected List<String> textLines;
    /**
     * 鼠标位置，决定了渲染位置
     */
    protected int mouseX;
    /**
     * 鼠标位置，决定了渲染位置
     */
    protected int mouseY;
    /**
     * 屏幕大小，决定了渲染位置
     */
    protected int screenWidth;
    /**
     * 屏幕大小，决定了渲染位置
     */
    protected int screenHeight;
    /**
     * 我也不知道这是干什么的
     */
    protected int maxTextWidth;
    /**
     * 字体渲染器
     */
    protected FontRenderer font;
    /**
     * 若最终渲染结果
     */
    protected int finalModify;
    protected boolean nature;

    protected HoveringTextDrawingEvent(List<String> textLines, int mouseX, int mouseY,
                                       int screenWidth, int screenHeight,
                                       int maxTextWidth, FontRenderer font,
                                       int finalModify, boolean nature) {
        this.textLines = textLines;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.maxTextWidth = maxTextWidth;
        this.font = font;
        this.finalModify = finalModify;
        this.nature = nature;
    }

    @Cancelable
    @SideOnly(Side.CLIENT)
    public static class Pre extends HoveringTextDrawingEvent {
        public Pre(List<String> textLines, int mouseX, int mouseY,
                   int screenWidth, int screenHeight,
                   int maxTextWidth, FontRenderer font,
                   int finalModify, boolean nature) {
            super(textLines, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font, finalModify, nature);
        }

        public List<String> getTextLines() {
            return textLines;
        }
        
        public int getMouseX() {
            return mouseX;
        }
        
        public int getMouseY() {
            return mouseY;
        }
        
        public int getScreenWidth() {
            return screenWidth;
        }
        
        public int getScreenHeight() {
            return screenHeight;
        }
        
        public int getMaxTextWidth() {
            return maxTextWidth;
        }
        
        public FontRenderer getFontRenderer() {
            return font;
        }

        public void setTextLines(List<String> textLines) {
            this.textLines = textLines;
        }

        public void setMouseX(int mouseX) {
            this.mouseX = mouseX;
        }

        public void setMouseY(int mouseY) {
            this.mouseY = mouseY;
        }

        public void setScreenWidth(int screenWidth) {
            this.screenWidth = screenWidth;
        }

        public void setScreenHeight(int screenHeight) {
            this.screenHeight = screenHeight;
        }

        public void setMaxTextWidth(int maxTextWidth) {
            this.maxTextWidth = maxTextWidth;
        }

        public void setFontRenderer(FontRenderer font) {
            this.font = font;
        }

        @Override
        public boolean isCancelable() {
            return true;
        }

        public int getFinalModify() {
            return this.finalModify;
        }

        public void setFinalModify(int finalModify) {
            this.finalModify = finalModify;
        }

        public boolean isNature() {
            return this.nature;
        }

        public void setNature(boolean nature) {
            this.nature = nature;
        }
    }

    @SideOnly(Side.CLIENT)
    public static class Post extends HoveringTextDrawingEvent {
        public Post(List<String> textLines, int mouseX, int mouseY,
                    int screenWidth, int screenHeight,
                    int maxTextWidth, FontRenderer font,
                    int finalModify, boolean nature) {
            super(textLines, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font, finalModify, nature);
        }

        public List<String> getTextLines() {
            return textLines;
        }

        public int getMouseX() {
            return mouseX;
        }

        public int getMouseY() {
            return mouseY;
        }

        public int getScreenWidth() {
            return screenWidth;
        }

        public int getScreenHeight() {
            return screenHeight;
        }

        public int getMaxTextWidth() {
            return maxTextWidth;
        }

        public FontRenderer getFontRenderer() {
            return font;
        }

        public int getFinalModify() {
            return this.finalModify;
        }

        public boolean isNature() {
            return this.nature;
        }
    }
}
