package online.flowerinsnow.greatscrollabletooltips.config;

import online.flowerinsnow.greatscrollabletooltips.C;
import online.flowerinsnow.greatscrollabletooltips.util.IDHashMap;
import online.flowerinsnow.xmlreader.api.node.XMLNode;
import online.flowerinsnow.xmlreader.core.XMLReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * 保存所有的语言内容
 */
public enum LanguageMessage {
    TEST("test");

    public final String id;

    LanguageMessage(String id) {
        this.id = id;
    }

    public String get() {
        return this.get(selectedLanguage);
    }

    public String get(String language) {
        IDHashMap lang = LANGUAGES.get(language);
        if (lang == null) {
            lang = new IDHashMap();
        }
        return lang.get(this.id);
    }

    /**
     * 选择的语言
     */
    public static String selectedLanguage;
    public static Throwable exception = null;
    /**
     * (K=语言ID V=(K=消息ID V=消息内容))
     */
    private static final HashMap<String, IDHashMap> LANGUAGES = new HashMap<>();

    /**
     * 加载语言配置文件
     */
    public static void load() {
        try (InputStream in = LanguageMessage.class.getClassLoader().getResourceAsStream(C.langConfig)) {
            XMLNode node = XMLReader.readAsNode(in);
            XMLNode sl = node.getChildNode(C.selectedLanguage);
            if (sl != null) {
                selectedLanguage = sl.getValue();
            } else {
                selectedLanguage = "none";
            }

            XMLNode languages = node.getChildNode(C.nodeLanguages);
            if (languages == null) {
                return;
            }
            languages.forEachAllChildNodes(true, ch -> {
                if (ch.getName().equals(C.nodeLanguage) && ch.getChildNode(C.nodeId) != null && ch.getChildNode(C.nodeFile) != null) {
                    loadLanguageFile(ch);
                }
            });
        } catch (NullPointerException | IOException | SAXException | ParserConfigurationException e) {
            exception = e;
        }
    }

    private static void loadLanguageFile(XMLNode data) {
        String id = data.getChild(C.nodeId);
        String file = data.getChild(C.nodeFile);
        IDHashMap messages = new IDHashMap();
        LANGUAGES.put(id, messages);
        try (InputStream in = LanguageMessage.class.getClassLoader().getResourceAsStream(file)) {
            XMLNode node = XMLReader.readAsNode(in);
            XMLNode language = node.getChildNode(C.nodeLanguage);
            if (language == null) {
                return;
            }
            language.forEachAllChildNodes(false, n -> {
                if (n.getName().equals(C.nodeMessage)) {
                    String var0 = n.getAttribute(C.nodeId); // id
                    if (var0 != null) {
                        messages.put(var0, n.getValue());
                    }
                }
            });
        } catch (IOException | SAXException | ParserConfigurationException ex) {
            System.err.println(C.errorLanguageFile + file);
            ex.printStackTrace();
        }
    }
}
