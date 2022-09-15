package online.flowerinsnow.gsscrollable.event;

import net.minecraft.client.gui.FontRenderer;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class HoveringTextDrawingEvent extends Event {
    protected List<String> textLines;
    protected int mouseX;
    protected int mouseY;
    protected int screenWidth;
    protected int screenHeight;
    protected int maxTextWidth;
    protected FontRenderer font;

    protected HoveringTextDrawingEvent(List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font) {
        this.textLines = textLines;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.maxTextWidth = maxTextWidth;
        this.font = font;
    }

    @Cancelable
    public static class Pre extends HoveringTextDrawingEvent {
        public Pre(List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font) {
            super(textLines, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font);
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
    }

    public static class Post extends HoveringTextDrawingEvent {
        public Post(List<String> textLines, int mouseX, int mouseY, int screenWidth, int screenHeight, int maxTextWidth, FontRenderer font) {
            super(textLines, mouseX, mouseY, screenWidth, screenHeight, maxTextWidth, font);
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
    }
}
