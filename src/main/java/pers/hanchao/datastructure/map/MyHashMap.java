package pers.hanchao.datastructure.map;

import java.util.*;

/**
 * <p>用数组+链地址法(还有开放地址法)实现散列表</P>
 * 1.HashMap = nodes[] + 链地址法，capacity = nodes[].length = 数组容量 = 2的幂次方，size = 实际元素个数，loadFactor 负载因子
 * 2.threshold = 二倍扩容的阈值，当size >= threshold = capacity * loadFactor时，二倍扩容。
 * 3.containsKey(key): 先按hash()=下标查找数组，然后查找链表；复杂度O(1+m)，m为链表平均长度，约等于负载因子，O(1)
 * 4.containsValue(key); 遍历数组，然后遍历每个链表；复杂度O(N)
 * 5.get(key):  先按hash()=下标查找数组，然后查找链表；复杂度O(1+m)，m为链表平均长度，约等于负载因子，O(1)
 * 6.put(key,value): 先按hash()=下标查找数组，然后查找链表末尾；复杂度O(1+m)，m为链表平均长度，约等于负载因子，O(1)
 * 7.remove(key): 先按hash()=下标查找数组，然后查找链表末尾；复杂度O(1+m)，m为链表平均长度，约等于负载因子，O(1)
 * 8.keySet()、valueSet()、entrySet()：在put和remove过程中顺便增删Set，所以这里只是获取；复杂度O(1)
 *
 * @author hanchao
 */
public class MyHashMap<K, V> implements Map<K, V> {

    /**
     * 数据节点(链地址法，其实就是增强版的链表节点)
     */
    class Node<K, V> {
        /**
         * 散列结果
         */
        int hash;
        K key;
        V value;
        Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Node)) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return hash == node.hash &&
                    Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value) &&
                    Objects.equals(next, node.next);
        }

        /**
         * hashCode
         */
        @Override
        public int hashCode() {
            return Objects.hash(hash, key, value, next);
        }
    }

    /**
     * 数组
     */
    private Node<K, V>[] nodeArrays;

    /**
     * 数组容量,默认为16
     */
    private int capacity = 4;

    /**
     * 填充因子，默认为0.75f
     */
    private float loadFactor = 0.75f;

    /**
     * resize阈值,当hashmap中元素数量size到底threshold时，2倍扩容
     */
    private int threshold = (int) (capacity * loadFactor);

    /**
     * 实际元素个数
     */
    private int size;

    private Set<K> keySet = new HashSet<>();

    private Set<V> valueSet = new HashSet<>();

    private Set<Entry<K,V>> entrySet = new HashSet<>();

    public MyHashMap() {
        nodeArrays = new Node[capacity];
    }

    /**
     * 哈希函数
     */
    private int hash(int hashCode) {
        return Math.abs(hashCode) % capacity;
    }

    /**
     * 链表中是否包含指定元素
     */
    private boolean containsKey(Node<K, V> root, Object key) {
        Node<K, V> node = root;
        while (node != null) {
            if (node.key == key) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * 链表中是否包含指定元素
     */
    private boolean containsValue(Node<K, V> root, Object value) {
        Node<K, V> node = root;
        while (node != null) {
            if (node.value == value) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    /**
     * 链表中是否包含指定值
     */
    private V getValue(Node<K, V> root, Object key) {
        Node<K, V> node = root;
        while (node != null) {
            if (node.key == key) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }


    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * key.  More formally, returns <tt>true</tt> if and only if
     * this map contains a mapping for a key <tt>k</tt> such that
     * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     * key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean containsKey(Object key) {
        //获取根节点
        Node<K, V> rootNode = nodeArrays[hash(key.hashCode())];
        //查找链表
        if (null != rootNode) {
            return containsKey(rootNode, key);
        }
        return false;
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this map contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
     * will probably require time linear in the map size for most
     * implementations of the <tt>Map</tt> interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     * specified value
     * @throws ClassCastException   if the value is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified value is null and this
     *                              map does not permit null values
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean containsValue(Object value) {
        for (Node<K, V> nodeArray : nodeArrays) {
            //遍历每个根节点
            if (null != nodeArray) {
                //查找链表
                return containsValue(nodeArray, value);
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map
     * contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to {@code null}.  The {@link #containsKey
     * containsKey} operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public V get(Object key) {
        //获取根节点
        Node<K, V> rootNode = nodeArrays[hash(key.hashCode())];
        //查找链表
        if (null != rootNode) {
            return getValue(rootNode, key);
        }
        return null;
    }

    /**
     * Associates the specified value with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A map
     * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only
     * if {@link #containsKey(Object) m.containsKey(k)} would return
     * <tt>true</tt>.)
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * (A <tt>null</tt> return can also indicate that the map
     * previously associated <tt>null</tt> with <tt>key</tt>,
     * if the implementation supports <tt>null</tt> values.)
     * @throws UnsupportedOperationException if the <tt>put</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of the specified key or value
     *                                       prevents it from being stored in this map
     * @throws NullPointerException          if the specified key or value is null
     *                                       and this map does not permit null keys or values
     * @throws IllegalArgumentException      if some property of the specified key
     *                                       or value prevents it from being stored in this map
     */
    @Override
    public V put(K key, V value) {
        return put(nodeArrays, key, value);
    }

    private V put(Node<K, V>[] nodes, K key, V value) {
        //获取根节点
        Node<K, V> rootNode = nodes[hash(key.hashCode())];
        if (rootNode == null) {
            //数组无根节点则创建
            nodes[hash(key.hashCode())] = new Node<>(hash(key.hashCode()), key, value, null);
        } else {
            //有根节点则追加
            addNode(rootNode, new Node<>(hash(key.hashCode()), key, value, null));
        }
        System.out.println("------put key = " + key + ",value = " + value);
        keySet.add(key);
        valueSet.add(value);
        entrySet.add(new AbstractMap.SimpleEntry<>(key,value));
        size++;
        //如果元素数量超出阈值，则2倍扩容
        if (size >= threshold) {
            resize();
        }
        return value;
    }

    /**
     * 二倍扩容,全部元素重新hash
     * 可以优化，但是没必要纠结
     */
    private synchronized void resize() {
        if (this.size >= this.threshold){
            size = 0;
            System.out.println("------resize------");
            //二倍容量
            capacity = Math.min(capacity << 1, Integer.MAX_VALUE);
            //二倍阈值
            threshold = (int) (capacity * loadFactor);
            //全部重新hash
            Node<K, V>[] newArrays = (Node<K, V>[]) new Node[capacity];
            for (Node<K, V> root : nodeArrays) {
                Node<K, V> node = root;
                Node<K, V> current = node;
                while (node != null) {
                    //重新hash
                    put(newArrays, node.key, node.value);
                    //下一个节点
                    node = node.next;
                    //清除当前节点
                    current.value = null;
                    current.key = null;
                    current.next = null;
                }
            }
            //移除旧数组
            nodeArrays = null;
            nodeArrays = newArrays;
        }
    }

    /**
     * 添加新节点（root非空)
     */
    private void addNode(Node<K, V> root, Node<K, V> newNode) {
        Node node = root;
        Node parent = node;
        while (node != null) {
            parent = node;
            node = node.next;
        }
        parent.next = newNode;
    }

    /**
     * Removes the mapping for a key from this map if it is present
     * (optional operation).   More formally, if this map contains a mapping
     * from key <tt>k</tt> to value <tt>v</tt> such that
     * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping
     * is removed.  (The map can contain at most one such mapping.)
     *
     * <p>Returns the value to which this map previously associated the key,
     * or <tt>null</tt> if the map contained no mapping for the key.
     *
     * <p>If this map permits null values, then a return value of
     * <tt>null</tt> does not <i>necessarily</i> indicate that the map
     * contained no mapping for the key; it's also possible that the map
     * explicitly mapped the key to <tt>null</tt>.
     *
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the key is of an inappropriate type for
     *                                       this map
     *                                       (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified key is null and this
     *                                       map does not permit null keys
     *                                       (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public V remove(Object key) {
        return remove(nodeArrays,key);
    }

    private V remove(Node<K,V>[] nodes,Object key) {
        boolean find = false;
        V value = null;
        Node<K, V> root, node, parent;
        parent = node = root = nodes[hash(key.hashCode())];
        if (root != null) {
            //找到节点
            while (node != null) {
                //找到节点
                if (node.key == key) {
                    value = node.value;
                    find = true;
                    break;
                } else {
                    parent = node;
                    node = node.next;
                }
            }
            if (find) {
                //1.如果目标节点为根节点，则：1.清除节点，2.数组元素置node.next
                if (root == node) {
                    nodes[hash(key.hashCode())] = node.next;
                } else if (node.next != null) {
                    //2.如果目标节点为非根非末节点，则：1.清除节点，2.parent.next=node.next
                    parent.next = node.next;
                } else{
                    //3.如果目标节点为末节点，则：1.清除节点，2.parent.next=null
                    parent.next = null;

                }
                node.key = null;
                node.value = null;
                node.next = null;
                size -- ;
                keySet.remove(key);
                valueSet.remove(value);
                entrySet.remove(new AbstractMap.SimpleEntry<>(key,value));
            }
        }

        return value;
    }



    /**
     * Copies all of the mappings from the specified map to this map
     * (optional operation).  The effect of this call is equivalent to that
     * of calling {@link #put(Object, Object) put(k, v)} on this map once
     * for each mapping from key <tt>k</tt> to value <tt>v</tt> in the
     * specified map.  The behavior of this operation is undefined if the
     * specified map is modified while the operation is in progress.
     *
     * @param m mappings to be stored in this map
     * @throws UnsupportedOperationException if the <tt>putAll</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of a key or value in the
     *                                       specified map prevents it from being stored in this map
     * @throws NullPointerException          if the specified map is null, or if
     *                                       this map does not permit null keys or values, and the
     *                                       specified map contains null keys or values
     * @throws IllegalArgumentException      if some property of a key or value in
     *                                       the specified map prevents it from being stored in this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *                                       is not supported by this map
     */
    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < nodeArrays.length; i++) {
            nodeArrays[i] = null;
        }
    }

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     *
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own <tt>remove</tt> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a collection view of the values contained in this map
     */
    @Override
    public Collection<V> values() {
        return valueSet;
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return entrySet;
    }

    public static void main(String[] args) {
        MyHashMap<String ,String> map = new MyHashMap<>();

        System.out.println(map.size());
        System.out.println(map.isEmpty());

        map.put("code","1");
        map.put("reason","param is not right");
        map.put("data",null);
        map.put("size","10");

        System.out.println(map.containsKey("code"));
        System.out.println(map.containsKey("size"));
        System.out.println(map.containsKey("java"));
        System.out.println(map.get("reason"));
        System.out.println(map.keySet());
        System.out.println(map.values());

        System.out.println(map.remove("size"));
        System.out.println(map.containsKey("size"));
        System.out.println(map.keySet());
        System.out.println(map.values());
        System.out.println(map.entrySet());
    }
}
