package online.flowerinsnow.xmlreader.api.node;

import org.jetbrains.annotations.NotNull;

public enum XMLNodeType {
    ELEMENT_NODE(1),
    ATTRIBUTE_NODE(2),
    TEXT_NODE(3),
    CDATA_SECTION_NODE(4),
    ENTITY_REFERENCE_NODE(5),
    ENTITY_NODE(6),
    PROCESSING_INSTRUCTION_NODE(7),
    COMMENT_NODE(8),
    DOCUMENT_NODE(9),
    DOCUMENT_TYPE_NODE(10),
    DOCUMENT_FRAGMENT_NODE(11),
    NOTATION_NODE(12);
    public final short id;

    XMLNodeType(int id) {
        this((short) id);
    }

    XMLNodeType(short id) {
        this.id = id;
    }

    public static @NotNull XMLNodeType getByID(short id) throws IllegalArgumentException {
        for (XMLNodeType value : values()) {
            if (value.id == id) {
                return value;
            }
        }
        throw new IllegalArgumentException("No enum constant " + XMLNodeType.class.getCanonicalName() + ".id(" + id + ")");
    }
}
