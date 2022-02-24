package practice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class hashCode_equals {
    static class E{
        String key ;
        String val;
        public E(String key, String val){
            this.key = key;
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            E e = (E) o;
            return Objects.equals(key, e.key) &&
                    Objects.equals(val, e.val);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, val);
        }
    }

    public static void main(String[] args) {
        Map<E, String> map = new HashMap<>();

        E e1 = new E("1", "abc");
        map.put(e1, "first");
        E e2 = new E("1", "abc");
        System.out.println(map.get(e1)); // first
        System.out.println(map.get(e2)); // null

        map.put(e2, "second");
        System.out.println(map.get(e2)); // second, and not first.
    }
}
