import java.util.HashMap;
import java.util.Map;

/**
 * 题目：https://leetcode.com/problems/lru-cache/#/description
 * 实现LRU：哈希表+自己实现的双向链表
 */
public class LRUCache {
    class Node {
        Node pre = null;
        Node next = null;
        int key;
        int value;
    }

    private Map<Integer, Node> map;
    private Node head;
    private int capacity;
    private int size;

    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        head = new Node();
        head.pre = head;
        head.next = head;
        size = 0;
    }
    
    public int get(int key) {
        Node n = map.get(key);
        if (n == null) {
            return -1;
        }
        // adjust n to the head of the list
        moveNodeToHead(n);
        return n.value;
    }

    private void moveNodeToHead(Node n) {
        if (n != head.next) {
            // remove n from the original position
            removeNode(n);
            // add n to the head of list
            addNodeToHead(n);
        }
    }

    private void addNodeToHead(Node n) {
        Node first = head.next;
        head.next = n;
        first.pre = n;
        n.next = first;
        n.pre = head;
    }

    private void removeNode(Node n) {
        Node next = n.next;
        Node pre = n.pre;
        pre.next = next;
        next.pre = pre;
        n.pre = null;
        n.next = null;
    }

    public void put(int key, int value) {
        Node n = map.get(key);
        if (n == null) {
            Node newN = new Node();
            newN.key = key;
            newN.value = value;
            // 插入n到头部
            addNodeToHead(newN);
            map.put(key, newN);
            if (size == capacity) {
                // remove the last node
                Node lastNode = head.pre;
                map.remove(lastNode.key);
                removeNode(lastNode);
            } else {
                ++size;
            }
        } else {
            n.value = value;
            moveNodeToHead(n);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */