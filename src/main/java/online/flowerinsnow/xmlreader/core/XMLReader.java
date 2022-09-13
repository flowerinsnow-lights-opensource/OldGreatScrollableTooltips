package online.flowerinsnow.xmlreader.core;

import online.flowerinsnow.xmlreader.api.node.XMLNode;
import org.jetbrains.annotations.NotNull;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public final class XMLReader {
    private XMLReader() {
    }

    public static @NotNull XMLNode readAsNode(File file) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return new WrappedXMLNode(builder.parse(file));
    }

    public static @NotNull XMLNode readAsNode(String uri) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return new WrappedXMLNode(builder.parse(uri));
    }

    public static @NotNull XMLNode readAsNode(InputStream in) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return new WrappedXMLNode(builder.parse(in));
    }

    public static @NotNull XMLNode readAsNode(InputStream in, String systemId) throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return new WrappedXMLNode(builder.parse(in, systemId));
    }
}
