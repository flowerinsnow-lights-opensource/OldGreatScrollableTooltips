package online.flowerinsnow.greatscrollabletooltips.coremod.transformer;

import net.minecraft.launchwrapper.IClassTransformer;

public class TransformerGuiUtils implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if ("net.minecraftforge.fml.client.config.GuiUtils".equals(transformedName)) {
            return transformGuiUtils(basicClass);
        }
        return basicClass;
    }

    private byte[] transformGuiUtils(byte[] basicClass) {
        return basicClass;
    }
}
