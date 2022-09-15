package online.flowerinsnow.greatscrollabletooltips.util;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import java.util.function.Consumer;

@SideOnly(Side.CLIENT)
public final class ASMUtils {
    public static byte[] readAndTransform(byte[] basic, Consumer<ClassNode> action) {
        ClassReader cr = new ClassReader(basic);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);
        action.accept(cn);
        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw);
        return cw.toByteArray();
    }

    private ASMUtils() {
    }
}
