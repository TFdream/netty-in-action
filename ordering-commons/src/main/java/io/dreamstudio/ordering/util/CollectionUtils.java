package io.dreamstudio.ordering.util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合工具类
 */
public abstract class CollectionUtils {

    public static int size(Collection col) {
        return col!=null ? col.size() : 0;
    }

    public static int size(Map map) {
        return map!=null ? map.size() : 0;
    }

    //------Collection
    public static boolean isEmpty(Collection col) {
        return col == null || col.isEmpty();
    }

    public static boolean isNotEmpty(Collection col) {
        return !isEmpty(col);
    }

    //------Map
    public static boolean isEmpty(Map map) {
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    //------List
    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }

    public static <T> List<T> newArrayList(int capacity) {
        return new ArrayList<>(capacity);
    }

    public static <E> LinkedList<E> newLinkedList() {
        return new LinkedList<>();
    }

    public static <E> CopyOnWriteArrayList<E> newCopyOnWriteArrayList() {
        return new CopyOnWriteArrayList<>();
    }

    //------Set
    public static <T> Set<T> newHashSet() {
        return new HashSet<T>();
    }

    public static <T> Set<T> newHashSet(int capacity) {
        return new HashSet<T>(calRealCap(capacity));
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet() {
        return new LinkedHashSet<E>();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet(int capacity) {
        return new LinkedHashSet<E>(calRealCap(capacity));
    }

    public static <E extends Comparable> TreeSet<E> newTreeSet() {
        return new TreeSet<E>();
    }

    public static <E> CopyOnWriteArraySet<E> newCopyOnWriteArraySet() {
        return new CopyOnWriteArraySet<E>();
    }

    //------Map
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

    public static <K, V> HashMap<K, V> newHashMap(int capacity) {
        return new HashMap<K, V>(calRealCap(capacity));
    }

    public static <K, V> LinkedHashMap<K, V> newLinkedHashMap() {
        return new LinkedHashMap<K, V>();
    }

    public static <K extends Comparable, V> TreeMap<K, V> newTreeMap() {
        return new TreeMap<K, V>();
    }

    public static <K, V> ConcurrentMap<K, V> newConcurrentMap() {
        return new ConcurrentHashMap<K, V>();
    }

    private static int calRealCap(int capacity) {
        return (int) ((float) capacity / 0.75F + 1.0F);
    }
}
