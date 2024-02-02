package demo.dp.ch04_dp_creational._02_factory.DI_Framework;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import demo.dp.ch04_dp_creational._02_factory.DI_Framework.BeanDefinition.ConstructorArg;

public class XmlBeanConfigParser implements BeanConfigParser {

    @Override
    public List<BeanDefinition> parse(InputStream inputStream) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        Document doc;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("can't parsed.");
        }

        NodeList beans = doc.getElementsByTagName("bean");
        List<BeanDefinition> beanDefinitions = new ArrayList<>();
        // 仅验证原理，校验不做了。
        for (int i = 0; i < beans.getLength(); i ++) {
            BeanDefinition beanDefinition = new BeanDefinition();
            Node bean = beans.item(i);
            NamedNodeMap attrs = bean.getAttributes();
            for (int j = 0; j < attrs.getLength(); j ++) {
                parseAttr(attrs.item(j), beanDefinition);
            }
            NodeList construactorArgs = bean.getChildNodes();
            parseConstruactorArgs(construactorArgs, beanDefinition);
            beanDefinitions.add(beanDefinition);
        }
        return beanDefinitions;

    }

    private void parseConstruactorArgs(NodeList construactorArgs, BeanDefinition beanDefinition) {
        for (int i = 0; i < construactorArgs.getLength(); i ++) {
            Node argNode = construactorArgs.item(i);
            if (!"constructor-arg".equals(argNode.getNodeName())) 
                continue;

            NamedNodeMap attrs = argNode.getAttributes();
            for (int j = 0; j < attrs.getLength(); j ++) {
                BeanDefinition.ConstructorArg arg = parseAttr(attrs.item(j));
                beanDefinition.getConstructorArgs().add(arg);
            }
        }         
    }

  private ConstructorArg parseAttr(Node attr) {
        BeanDefinition.ConstructorArg arg = new BeanDefinition.ConstructorArg();
        String name = attr.getNodeName();
        String value = attr.getNodeValue();
        switch (name) {
            case "ref" -> arg.setRef(value);
            // 仅验证思路，不解析复杂的 type.
            // case "type" -> arg.setType(Class.forName(attr.getNodeValue()));
            case "value" -> arg.setValue(value);
        }
        return arg;
    }

  private void parseAttr(Node attr, BeanDefinition beanDefinition) {
        String name = attr.getNodeName();
        switch (name) {
            case "class" -> beanDefinition.setClassName(attr.getNodeValue());
            case "id"    -> beanDefinition.setId(attr.getNodeValue());
        } 
    }
}
