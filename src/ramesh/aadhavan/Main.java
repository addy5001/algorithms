package ramesh.aadhavan;

import ramesh.aadhavan.misc.LruCache;

public class Main {
    public static void main(String[] args) {
        LruCache<String> lruCache = new LruCache<>(5);
        lruCache.put(1, "hello");
        lruCache.put(100, "aadhavan");
        lruCache.put(234, "madhu");

        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(100));
        System.out.println(lruCache.get(234));
        System.out.println(lruCache.get(1));
    }
}
