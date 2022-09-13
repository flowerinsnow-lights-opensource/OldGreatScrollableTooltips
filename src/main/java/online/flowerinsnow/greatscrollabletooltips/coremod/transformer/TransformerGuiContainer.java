package online.flowerinsnow.greatscrollabletooltips.coremod.transformer;

import net.minecraft.launchwrapper.IClassTransformer;
import online.flowerinsnow.greatscrollabletooltips.C;
import online.flowerinsnow.greatscrollabletooltips.coremod.GSCorePlugin;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.List;

public class TransformerGuiContainer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (C.classGuiContainer.equals(name)) {
            ClassReader cr = new ClassReader(basicClass);
            ClassNode cn = new ClassNode();
            cr.accept(cn, 0);

            List<FieldNode> fields = cn.fields;
            List<MethodNode> methods = cn.methods;

            // private static int gsToolTipY = 0
            fields.add(new FieldNode(
                    Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC,
                    "gsToolTipY",
                    "I", null, 0
                    ));
            // private static int gsTooltipHeight = 0;
            fields.add(new FieldNode(
                    Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC,
                    "gsTooltipHeight",
                    "I", null, 0
            ));

            methods.forEach(mn -> {
                if (GSCorePlugin.equalsAnyOne(mn.name, "keyTyped", "func_73869_a") && mn.desc.equals("(CI)V")) {
                    addDebug(mn);
                }
            });

            ClassWriter cw = new ClassWriter(0);
            cn.accept(cw);
            return cw.toByteArray();
        }
        return basicClass;
    }

    private void addDebug(MethodNode mn) {
        // GSLogger.info(Integer.toString(var2))
        InsnList addList = new InsnList();
        addList.add(new VarInsnNode(Opcodes.ILOAD, 2));
        addList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "java/lang/Integer", "toString", "(I)Ljava/lang/String;", false));
        addList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, C.bClassGSLogger, "info", "(Ljava/lang/String;)V", false));
        mn.instructions.insertBefore(mn.instructions.getFirst(), addList);
    }
}
