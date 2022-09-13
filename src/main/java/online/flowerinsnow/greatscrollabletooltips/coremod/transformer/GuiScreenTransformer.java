package online.flowerinsnow.greatscrollabletooltips.coremod.transformer;

import net.minecraft.launchwrapper.IClassTransformer;
import online.flowerinsnow.greatscrollabletooltips.C;
import online.flowerinsnow.greatscrollabletooltips.coremod.GSCorePlugin;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.List;

public class GuiScreenTransformer implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (C.classGuiScreen.equals(name)) {
            System.out.println("Transforming " + C.classGuiScreen);
            ClassReader cr = new ClassReader(basicClass);
            ClassNode cn = new ClassNode();
            cr.accept(cn, 0);

            List<FieldNode> fields = cn.fields;
            List<MethodNode> methods = cn.methods;

            //noinspection OptionalGetWithoutIsPresent
            MethodNode renderToolTip = methods.stream().filter(mn -> GSCorePlugin.equalsAnyOne(mn.name, "func_146285_a", "renderToolTip")
                && "(Lnet/minecraft/item/ItemStack;II)V".equals(mn.desc)).findAny().get();
            transformRenderToolTip(renderToolTip);

            ClassWriter cw = new ClassWriter(0);
            cn.accept(cw);
            return cw.toByteArray();
        }
        return basicClass;
    }

    private void transformRenderToolTip(MethodNode mn) {
        InsnList addList = new InsnList();
        LabelNode l1 = new LabelNode();
        // if (this instanceof GuiContainer)
        addList.add(new VarInsnNode(Opcodes.ALOAD, 0));
        addList.add(new TypeInsnNode(Opcodes.INSTANCEOF, C.bClassGuiContainer));
        addList.add(new JumpInsnNode(Opcodes.IFEQ, l1));

        // var3 += gsToolTipY
        addList.add(new VarInsnNode(Opcodes.ILOAD, 3));
        addList.add(new FieldInsnNode(Opcodes.GETFIELD, C.bClassGuiContainer, "gsToolTipY", "I"));
        addList.add(new InsnNode(Opcodes.IADD));
        addList.add(new VarInsnNode(Opcodes.ISTORE, 3));

        addList.add(l1);

        mn.instructions.insertBefore(mn.instructions.getFirst(), addList);
    }
}
