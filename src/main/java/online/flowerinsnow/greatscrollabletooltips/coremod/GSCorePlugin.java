package online.flowerinsnow.greatscrollabletooltips.coremod;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import online.flowerinsnow.greatscrollabletooltips.coremod.transformer.TransformerGuiContainer;
import online.flowerinsnow.greatscrollabletooltips.coremod.transformer.TransformerGuiUtils;

import javax.annotation.Nullable;
import java.util.Map;

public class GSCorePlugin implements IFMLLoadingPlugin {
    public GSCorePlugin() {
        System.out.println("Loading language.xml");
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{TransformerGuiUtils.class.getName(),
                TransformerGuiContainer.class.getName()};
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
}
