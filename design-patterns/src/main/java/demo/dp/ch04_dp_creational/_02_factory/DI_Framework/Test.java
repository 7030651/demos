package demo.dp.ch04_dp_creational._02_factory.DI_Framework;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // File configFile = new File(Test.class.getResource("").getPath() + File.separator + "config.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        InputStream in = Test.class.getResourceAsStream("config.xml");
        Document doc = dBuilder.parse(in);
        doc.normalize();

        NodeList beans = doc.getElementsByTagName("bean");
        for (int i = 0; i < beans.getLength(); i ++) {
            Node bean = beans.item(i);
            NamedNodeMap attrs = bean.getAttributes();
            for (int j = 0; j < attrs.getLength(); j ++) {
                Node attr = attrs.item(j);
                /**
                 *  name = class, value = com.xzg.RateLimiter
                    name = id, value = rateLimiter
                    name = class, value = com.xzg.redisCounter
                    name = id, value = redisCounter
                    name = lazy-init, value = true
                    name = scope, value = singleton
                 */
                // System.out.println("name = " + attr.getNodeName() + ", value = " + attr.getNodeValue());
            }
            NodeList argNodes = bean.getChildNodes();
            for (int j = 0; j < argNodes.getLength(); j ++) {
                Node node = argNodes.item(j);
                System.out.println(node.getNodeName() + ", value = " + node.getNodeValue());
            }
        }
        
    }
}
