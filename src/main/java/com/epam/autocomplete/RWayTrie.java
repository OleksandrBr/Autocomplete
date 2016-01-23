/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.autocomplete;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Oleksandr_Briukhovych
 */
public class RWayTrie implements Trie {
//    // Добавляет в Trie пару слово - term, и его вес - weight.
//    // В качестве веса используйте длину слова
//

    private final Node root;          // root of trie
    private static int size;    

    public RWayTrie() {
        root = new Node(' ', 0);
    }

    @Override
    public void add(Tuple tuple) {
        char arr[] = tuple.getTerm().toCharArray();
        Node node = root;
        int i = 0;
        while (i < arr.length) {
            Node child = node.getChild(arr[i]);
            if (child != null) {
                node = child;
            } else {
                Node newChild = new Node(arr[i], 0);
                node.setChild(newChild);
                node = newChild;
            }
            i++;
        }
        node.setValue(tuple.getWeight());
    }

    @Override
    public boolean contains(String word) {
        char arr[] = word.toCharArray();
        Node node = root;
        int i = 0;
        while (i < arr.length) {
            Node child = node.getChild(arr[i]);
            if (child == null) {
                return false;
            }
            node = child;
            i++;
        }
        return !node.getValue().equals(0);
    }

    @Override
    public boolean delete(String word) {

        Node currentNode = root;
        List<Node> nodes = new ArrayList<>();
        nodes.add(root);

        char arr[] = word.toCharArray();
        int i = 0;
        while (i < arr.length - 1) {
            Node child = currentNode.getChild(arr[i]);
            System.out.println(child);
            if (child != null) {
                currentNode = child;
                nodes.add(currentNode);

            } else {
                return false;
            }
            i++;
        }
        Node child = currentNode.getChild(arr[i]);

        if (child.getChildenSize() == 0 && child.getValue() > 0) {
            child.setValue(0);
            return true;
        }
        if (currentNode.getChildenSize() > 0 && currentNode.getValue() > 0) {
            currentNode.setValue(0);
            return true;
        }
        Collections.reverse(nodes);
        Iterator<Node> iter = nodes.iterator();

        while (iter.hasNext()) {
            Node node = iter.next();
            if (node.getChildenSize() > 0) {

            }
        }
        return true;
    }

    @Override
    public Iterable<String> words() {
        return subTrieWords(root);
    }

    private Iterable<String> subTrieWords(Node subTrieRoot) {
        boolean flag = true;
        Queue<Node> nodes = new LinkedList<>();
        Queue<String> keys = new LinkedList<>();

        for (Node node : subTrieRoot.getChildren()) {
            nodes.add(node);
            keys.add(String.valueOf(node.getKey()));
        }

        List<String> words = new ArrayList<>();
        if (subTrieRoot.getValue() > 0) {
            words.add("");
        }

        while (flag) {
            Queue<Node> tempNodes = new LinkedList<>();
            Queue<String> tempKeys = new LinkedList<>();
            Iterator<Node> nodesIter = nodes.iterator();
            Iterator<String> keysIter = keys.iterator();

            while (nodesIter.hasNext()) {
                Node node = nodesIter.next();
                String key = keysIter.next();
                if (node.getValue() > 0) {
                    words.add(key);
                }
                node.getChildren().stream().map((Node child) -> {
                    tempNodes.add(child);
                    return child;
                }).forEach((Node child) -> {
                    tempKeys.add(key + String.valueOf(child.getKey()));
                });
            }
            nodes = tempNodes;
            keys = tempKeys;
            if (nodes.isEmpty()) {
                flag = false;
            }
        }
        return words;
    }

    @Override
    public Iterable<String> wordsWithPrefix(String pref) {
        char arr[] = pref.toCharArray();

        List<String> output = new ArrayList<>();

        Node node = root;
        for (char letter : arr) {
            if (node == null) {
                break;
            }
            node = node.getChild(letter);
        }

        if (node != null) {
            Iterable<String> words = subTrieWords(node);

            for (String word : words) {
                output.add(pref + word);
            }
        }
        return output;
    }

    @Override
    public int size() {
        size = 0;
        countSize(root);
        return size;
    }

    private void countSize(Node node) {
        if (node.getChildenSize() > 0) {
            node.getChildren().stream().map((child) -> {
                if (child.getValue() > 0) {
                    size++;
                }
                return child;
            }).forEach((child) -> {
                countSize(child);
            });
        }
    }

    private static class Node {

        private final List<Node> children = new ArrayList<>();
        private final char key;
        private Integer value;

        public Node(char key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public char getKey() {
            return key;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setChild(Node node) {
            children.add(node);
        }

        public void deleteChild(char deletedKey) {
            Node deleted = null;
            for (Node node : children) {
                if (node.getKey() == deletedKey) {
                    deleted = node;
                }
            }
            children.remove(deleted);
        }

        public Node getChild(char childKey) {

            for (Node node : children) {
                if (node.getKey() == childKey) {
                    return node;
                }
            }
            return null;
        }

        public int getChildenSize() {
            return children.size();
        }

        public List<Node> getChildren() {
            return children;
        }

        @Override
        public String toString() {
            return "Node{" + "key=" + key + ", value=" + value + '}';
        }

    }
}
