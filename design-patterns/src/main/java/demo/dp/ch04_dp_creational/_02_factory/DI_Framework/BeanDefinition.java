package demo.dp.ch04_dp_creational._02_factory.DI_Framework;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinition {
    private String id;
    private String className;
    private List<ConstructorArg> constructorArgs = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ConstructorArg> getConstructorArgs() {
        return constructorArgs;
    }

    public void setConstructorArgs(List<ConstructorArg> constructorArgs) {
        this.constructorArgs = constructorArgs;
    }


    @Override
    public String toString() {
        return "BeanDefinition [id=" + id + ", className=" + className + ", constructorArgs=" + constructorArgs + "]";
    }

    public static class ConstructorArg {
        private String ref;
        private Class type;
        private String value;
        public String getRef() {
            return ref;
        }
        public void setRef(String ref) {
            this.ref = ref;
        }
        public Class getType() {
            return type;
        }
        public void setType(Class type) {
            this.type = type;
        }
        public String getValue() {
            return value;
        }
        public void setValue(String value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return "ConstructorArg [ref=" + ref + ", type=" + type + ", value=" + value + "]";
        }
    }


}
