package online.flowerinsnow.greatscrollabletooltips.coremod.transformer;

import net.minecraft.launchwrapper.IClassTransformer;
import online.flowerinsnow.greatscrollabletooltips.C;
import online.flowerinsnow.greatscrollabletooltips.coremod.GSCorePlugin;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.List;

public class GuiContainerTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (C.classGuiContainer.equals(name)) {
            System.out.println("Transforming " + C.classGuiContainer);
            ClassReader cr = new ClassReader(basicClass);
            ClassNode cn = new ClassNode();
            cr.accept(cn, 0);

            List<FieldNode> fields = cn.fields;
            List<MethodNode> methods = cn.methods;

            // private int gsToolTipY = 0
            fields.add(new FieldNode(
                    Opcodes.ACC_PRIVATE,
                    "gsToolTipY",
                    "I", null, 0
                    ));

            // public void handleMouseInput() throws IOException
            MethodNode handleMouseInput = methods.stream().filter(mn -> GSCorePlugin.equalsAnyOne(mn.name, "func_146274_d", "handleMouseInput")
                && mn.desc.equals("()V")).findAny().orElseGet(() -> {
                MethodNode var0 = new MethodNode(Opcodes.ACC_PUBLIC,
                        "func_146274_d", "()V", null,
                        new String[]{C.bClassIOException});
                methods.add(var0);
                return var0;
            });

            overrideHandleMouseInput(handleMouseInput);

            ClassWriter cw = new ClassWriter(0);
            cn.accept(cw);
            return cw.toByteArray();
        }
        return basicClass;
    }

    private void overrideHandleMouseInput(MethodNode mn) {
        InsnList addList = new InsnList();
        // super.handleMouseInput()
        addList.add(new VarInsnNode(Opcodes.ALOAD, 0));
        addList.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "net/minecraft/client/gui/GuiScreen", "func_146274_d", "()V", false));

        // if (Mouse.getEventDWheel() != 0
        LabelNode l1 = new LabelNode();
        addList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "org/lwjgl/input", "getEventDWheel", "()I", false));
        addList.add(new JumpInsnNode(Opcodes.IFEQ, l1));

        // && this.hoveredSlot != null
        addList.add(new VarInsnNode(Opcodes.ALOAD, 0));
        addList.add(new FieldInsnNode(Opcodes.GETFIELD, C.bClassGuiContainer, "field_147006_u", C.descSlot));
        addList.add(new JumpInsnNode(Opcodes.IFNULL, l1));

        // && this.hoveredSlot.getHasStack()) {
        addList.add(new VarInsnNode(Opcodes.ALOAD, 0));
        addList.add(new FieldInsnNode(Opcodes.GETFIELD, C.bClassGuiContainer, "field_147006_u", C.descSlot));
        addList.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, C.bClassSlot, "func_75216_d", "()Z", false));
        addList.add(new JumpInsnNode(Opcodes.IFEQ, l1));

        // this.gsToolTipY += Mouse.getEventDWheel()
        addList.add(new VarInsnNode(Opcodes.ALOAD, 0)); // this
        addList.add(new InsnNode(Opcodes.DUP)); // this this
        addList.add(new FieldInsnNode(Opcodes.GETSTATIC, C.bClassGuiContainer, "gsToolTipY", "I")); // gsToolTipY this
        addList.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "org/lwjgl/input", "getEventDWheel", "()I", false)); // mouse gsToolTipY this
        addList.add(new InsnNode(Opcodes.IADD)); // newGsToolTipY this
        addList.add(new MethodInsnNode(Opcodes.PUTSTATIC, "org/lwjgl/input", "getEventDWheel", "()I", false));
        // }
        addList.add(l1);

        AbstractInsnNode first = mn.instructions.getFirst();
        if (first == null) {
            mn.instructions.insert(addList);
        } else {
            mn.instructions.insertBefore(mn.instructions.getFirst(), addList);
        }
    }

    private void transformRenderHoveredToolTip(MethodNode mn) {

    }
}
