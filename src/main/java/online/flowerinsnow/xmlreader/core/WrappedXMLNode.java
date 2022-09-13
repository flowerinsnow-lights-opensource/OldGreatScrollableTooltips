package online.flowerinsnow.xmlreader.core;

import online.flowerinsnow.xmlreader.api.node.XMLNode;
import online.flowerinsnow.xmlreader.api.node.XMLNodeType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;
import java.util.function.Consumer;

public class WrappedXMLNode implements XMLNode {
    private final Node wrapped;

    public WrappedXMLNode(Node wrapped) {
        this.wrapped = wrapped;
        init();
    }

    private void init() {
        this.name = wrapped.getNodeName();
        this.type = XMLNodeType.getByID(wrapped.getNodeType());

        if (this.getType() == XMLNodeType.ELEMENT_NODE) { // 只有元素有属性
            NamedNodeMap map = wrapped.getAttributes();
            for (int i = 0; i < map.getLength(); i++) {
                Node inNode = map.item(i);
                this.attributes.put(inNode.getNodeName(), inNode.getNodeValue());
            }
        }

        NodeList childs = wrapped.getChildNodes();
        for (int i = 0; i < childs.getLength(); i++) {
            Node childNode = childs.item(i);
            if (this.value != null && childNode.getNodeType() == XMLNodeType.TEXT_NODE.id) {
                this.value = childNode.getNodeValue();
            }
            if (childNode.getNodeType() == XMLNodeType.ELEMENT_NODE.id) {
                this.childNodes.add(new WrappedXMLNode(childNode));
            }
        }
        if (this.value == null) {
            this.value = "";
        }
    }

    public Node getWrapped() {
        return wrapped;
    }

    private final Map<String, @NotNull String> attributes = new HashMap<>();
    private final List<XMLNode> childNodes = new ArrayList<>();
    private String name;
    private String value;
    private XMLNodeType type;

    @Override
    public @NotNull String getName() {
        return this.name;
    }

    @Override
    public @NotNull String getValue() {
        return this.value;
    }

    @Override
    public byte getValueAsByte() throws NumberFormatException {
        return Byte.parseByte(getValue());
    }

    @Override
    public short getValueAsShort() throws NumberFormatException {
        return Short.parseShort(getValue());
    }

    @Override
    public int getValueAsInt() throws NumberFormatException {
        return Integer.parseInt(getValue());
    }

    @Override
    public long getValueAsLong() throws NumberFormatException {
        return Long.parseLong(getValue());
    }

    @Override
    public boolean getValueAsBool() {
        return Boolean.parseBoolean(getValue());
    }

    @Override
    public float getValueAsFloat() throws NumberFormatException {
        return Float.parseFloat(getValue());
    }

    @Override
    public double getValueAsDouble() throws NumberFormatException {
        return Double.parseDouble(getValue());
    }

    @Override
    public @NotNull XMLNodeType getType() {
        return this.type;
    }

    @Override
    public @Nullable String getAttribute(@NotNull String key) {
        return this.attributes.get(key);
    }

    @Override
    public @NotNull Map<String, @NotNull String> getAttributes() {
        return new HashMap<>(attributes);
    }

    @Override
    public @Nullable XMLNode getChildNode(@NotNull String key) {
        return this.childNodes.stream()
                .filter(n -> n.getName().equals(key))
                .findFirst().orElse(null);
    }

    @Override
    public @NotNull List<XMLNode> getChildNodes() {
        return new ArrayList<>(childNodes);
    }

    @Override
    public byte getChildByte(@NotNull String key) throws NumberFormatException {
        String value = getChild(key);
        return value == null ? (byte) 0 : Byte.parseByte(value);
    }

    @Override
    public short getChildShort(@NotNull String key) throws NumberFormatException {
        String value = getChild(key);
        return value == null ? (short) 0 : Short.parseShort(value);
    }

    @Override
    public int getChildInt(@NotNull String key) throws NumberFormatException {
        String value = getChild(key);
        return value == null ? 0 : Integer.parseInt(value);
    }

    @Override
    public long getChildLong(@NotNull String key) throws NumberFormatException {
        String value = getChild(key);
        return value == null ? 0L : Long.parseLong(value);
    }

    @Override
    public float getChildFloat(@NotNull String key) throws NumberFormatException {
        String value = getChild(key);
        return value == null ? 0.0F : Float.parseFloat(value);
    }

    @Override
    public double getChildDouble(@NotNull String key) throws NumberFormatException {
        String value = getChild(key);
        return value == null ? 0.0 : Double.parseDouble(value);
    }

    @Override
    public boolean getChildBool(@NotNull String key) {
        String value = getChild(key);
        return Boolean.parseBoolean(value);
    }

    @Override
    public @Nullable String getChild(@NotNull String key) {
        XMLNode node = getChildNode(key);
        return node == null ? null : node.getValue();
    }

    @Override
    public void forEachAllChildNodes(boolean deep, Consumer<XMLNode> action) {
        if (hasChildNodes()) {
            this.childNodes.forEach(ch -> {
                action.accept(ch);
                if (deep) {
                    ch.forEachAllChildNodes(true, action);
                }
            });
        }
    }

    @Override
    public boolean hasChildNodes() {
        return this.childNodes.size() > 0;
    }
}
