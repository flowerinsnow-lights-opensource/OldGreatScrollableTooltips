package online.flowerinsnow.greatscrollabletooltips.coremod.transformer;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

public class TransformerGuiUtils implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if ("net.minecraftforge.fml.client.config.GuiUtils".equals(transformedName)) {
            return transformGuiUtils(basicClass);
        }
        return basicClass;
    }

    private byte[] transformGuiUtils(byte[] basicClass) {
//        ClassReader cr = new ClassReader(basicClass);
//        ClassNode cn = new ClassNode();
//        cr.accept(cn, 0);
//
//        List<FieldNode> fields = cn.fields;
//        List<MethodNode> methods = cn.methods;
//
//        // private static int gsToolTipY = 0
//        fields.add(new FieldNode(
//                Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC,
//                "gsToolTipY",
//                "I", null, 0
//                ));
//        // private static int gsTooltipHeight = 0;
//        fields.add(new FieldNode(
//                Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC,
//                "gsTooltipHeight",
//                "I", null, 0
//        ));
//        // private static UMatrixStack gsMatrixStack = null
//        fields.add(new FieldNode(
//                Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC,
//                "gsMatrixStack",
//                "I", null, 0));
//
//        ClassWriter cw = new ClassWriter(0);
//        cn.accept(cw);
//        return cw.toByteArray();
        return basicClass;
    }
}
