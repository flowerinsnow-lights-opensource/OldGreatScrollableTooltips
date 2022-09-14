package online.flowerinsnow.greatscrollabletooltips.reflect;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.lang.reflect.Field;

@SideOnly(Side.CLIENT)
public class Reflects {
    public static final Field hoveredSlot = getFieldByPossibleName(GuiContainer.class, "field_147006_u", "hoveredSlot");

    public static Field getFieldByPossibleName(Class<?> inClass, String... names) {
        for (String name : names) {
            try {
                Field field = inClass.getDeclaredField(name);
                field.setAccessible(true);
                return field;
            } catch (NoSuchFieldException ignored) {
            }
        }
        throw new RuntimeException();
    }

    public static Object getFieldValue(Field field, Object object) {
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
