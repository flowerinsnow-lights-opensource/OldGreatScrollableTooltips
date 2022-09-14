package online.flowerinsnow.greatscrollabletooltips;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public interface C {
    String selectedLanguage = "selectedLanguage";
    String langConfig = "lang/language.xml";
    String nodeLanguages = "languages";
    String nodeLanguage = "language";
    String nodeMessage = "message";
    String nodeId = "id";
    String nodeFile = "file";

    String errorLanguageFile = "Cannot load language file ";
}
