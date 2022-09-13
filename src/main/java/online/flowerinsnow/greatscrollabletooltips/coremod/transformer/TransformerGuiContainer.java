package online.flowerinsnow.greatscrollabletooltips.coremod.transformer;

import net.minecraft.launchwrapper.IClassTransformer;

public class TransformerGuiContainer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        // TODO 修改GuiContainer类
        return basicClass;
    }
}
