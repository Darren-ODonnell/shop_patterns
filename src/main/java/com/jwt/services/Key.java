package com.jwt.services;

public class Key<K, V>{

    K key;
    V count;

    public Key() {

    }

    public Key(K key, V  count) {
        this.key = key;
        this.count = count;
    }

    public void setCount(V count) {
        this.count = count;
    }
    public V getCount() {
        return count;
    }

    public void setKey(K key) {
        this.key = key;
    }
    public K getKey() {
        return key;
    }

}