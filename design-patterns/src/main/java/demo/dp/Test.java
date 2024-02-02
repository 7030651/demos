package demo.dp;

public class Test {
    private String key;
    private int value;

    public Test(String key, int value) {
        this.key = key;
        this.value = value;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        result = prime * result + value;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Test other = (Test) obj;
        if (key == null) {
            if (other.key != null)
                return false;
        } else if (!key.equals(other.key))
            return false;
        if (value != other.value)
            return false;
        return true;
    }



    @Override
    public String toString() {
        return "Test [key=" + key + ", value=" + value + "]";
    }
}
