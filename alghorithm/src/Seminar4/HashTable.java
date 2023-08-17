package Seminar4;

public class HashTable<K, V> {
    private static final double load_size = 0.75;
    private int size;
    private Bucket<K, V>[] buckets;

    HashTable() {
        buckets = new Bucket[8];
    }

    private int calculateIndex(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }

    public boolean add(K key, V value) {
        if (buckets.length * load_size <= size)
            resize();
        int index = calculateIndex(key);
        Bucket<K, V> bucket = buckets[index];
        if (bucket == null) {
            bucket = new Bucket<>();
            buckets[index] = bucket;
        }
        boolean result = bucket.add(key, value);
        if (result)
            size++;
        return result;
    }

    public boolean remove(K key, V value) {
        int index = calculateIndex(key);
        Bucket<K, V> bucket = buckets[index];
        if (bucket == null)
            return false;
        boolean result = bucket.remove(key);
        if (result)
            size--;
        return result;
    }

    public void print() {
        for (var item : buckets) {
            if (item != null) {
                item.print();
                System.out.println();
            }
            else
                System.out.println("----");
        }
    }

    private void resize() {
        Bucket<K, V>[] old = buckets;
        buckets = new Bucket[old.length * 2];
        for (int i = 0; i < old.length; i++) {
            Bucket<K, V> bucket = old[i];
            if (bucket == null)
                continue;
            Bucket.Node currentNode = bucket.root;
            while (currentNode != null) {
                this.add((K) currentNode.pair.key, (V) currentNode.pair.value);
                currentNode = currentNode.next;
            }
            old[i] = null;
        }
        old = null;
    }

    private class Bucket<K, V> {
        Node root;

        public boolean add(K key, V value) {
            if (root == null) {
                root = new Node(key, value);
                return true;
            }
            Node currentNode = root;
            while (currentNode.next != null)
                if (currentNode.pair.key == key)
                    return false;
                else
                    currentNode = currentNode.next;
            currentNode.next = new Node(key, value);
            return true;
        }

        public boolean remove(K key)  {
            if (root == null)
                return false;
            if (root.pair.key == key) {
                root = root.next;
                return true;
            }
            Node currentNode = root;
            while (currentNode.next != null) {
                if (currentNode.next.pair.key == key) {
                    currentNode.next = currentNode.next.next;
                    return true;
                }
                currentNode = currentNode.next;
            }
            return false;
        }

        public V getValue(K key) {
            if (root == null)
                return null;
            Node currentNode = root;
            while (currentNode != null)
                if (currentNode.pair.key == key)
                    return currentNode.pair.value;
                else
                    currentNode = currentNode.next;
            return null;
        }

        public void print() {
            Node currentNode = root;
            while (currentNode != null) {
                System.out.print("[" + currentNode.pair.key + ";" + currentNode.pair.value + "]");
                currentNode = currentNode.next;
            }
        }
        private class Node {
            Pair pair;
            Node next;

            Node() {
            }
            Node(Pair pair) {
                this.pair = pair;
            }
            Node (K key, V value) {
                pair = new Pair(key, value);
            }
        }

        private class Pair {
            K key;
            V value;

            Pair() {
            }

            Pair(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    }


}
