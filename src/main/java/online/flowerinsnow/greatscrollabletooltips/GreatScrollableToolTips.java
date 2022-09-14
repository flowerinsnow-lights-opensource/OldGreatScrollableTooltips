package online.flowerinsnow.greatscrollabletooltips;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = GreatScrollableToolTips.MODID, name = GreatScrollableToolTips.NAME, version = GreatScrollableToolTips.VERSION, clientSideOnly = true)
@SideOnly(Side.CLIENT)
public class GreatScrollableToolTips {
    public static final String MODID = "g_scrollable_tt";
    public static final String NAME = "GreatScrollableToolTips";
    public static final String VERSION = "1.0";
    public static int gsScrollY = 0;
}
