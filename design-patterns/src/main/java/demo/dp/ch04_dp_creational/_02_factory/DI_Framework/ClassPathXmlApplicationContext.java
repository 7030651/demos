package demo.dp.ch04_dp_creational._02_factory.DI_Framework;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 执行入口
 */
public class ClassPathXmlApplicationContext implements ApplicationContext {
    private BeanConfigParser beanConfigParser;
    private BeanFactory beanFactory;
    public ClassPathXmlApplicationContext(String configLocation) {
        this.beanConfigParser = new XmlBeanConfigParser();
        this.beanFactory = new BeanFactory();
        loadBeanDefinitions(configLocation);
    }

    private void loadBeanDefinitions(String configLocation) {
        try (InputStream in = this.getClass().getResourceAsStream(configLocation)) {
            List<BeanDefinition> beanDefinitions = beanConfigParser.parse(in);
            beanFactory.addBeanDefinitions(beanDefinitions);
            System.out.println(beanDefinitions);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanId) {
        return beanFactory.getBean(beanId);
    }
    
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("config.xml");
    }
}
