package online.flowerinsnow.greatscrollabletooltips.coremod;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import online.flowerinsnow.greatscrollabletooltips.coremod.transformer.GuiContainerTransformer;
import online.flowerinsnow.greatscrollabletooltips.coremod.transformer.GuiScreenTransformer;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class GSCorePlugin implements IFMLLoadingPlugin {
    public GSCorePlugin() {
        System.out.println("Loading language.xml");
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{GuiScreenTransformer.class.getName(),
                GuiContainerTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> map) {
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    public static boolean equalsAnyOne(Object object, Object... others) {
        return Arrays.asList(others).contains(object);
    }
}
