package demo.dp.ch04_dp_creational._02_factory.DI_Framework;

import java.io.InputStream;
import java.util.List;

public interface BeanConfigParser {
    List<BeanDefinition> parse(InputStream inputStream);
}
