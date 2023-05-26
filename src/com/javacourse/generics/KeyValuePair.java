package com.javacourse.generics;

public class KeyValuePair<K,V> {

    //también se vale declarar generics de tipos múltiples
    //en la declaración de una clase

    //---------------------------------------------------------------------------------
    //fields

    private K key;
    private V value;

    //---------------------------------------------------------------------------------
    //constructors

    public KeyValuePair(K _key, V _value) {
        this.key = _key;
        this.value = _value;
    }
    //KeyValuePair

    //---------------------------------------------------------------------------------
    //getters

    public K getKey() {

        return key;
    }
    //getKey

    public V getValue() {

        return value;
    }
    //getValue
}
//KeyValuePair

//eof
