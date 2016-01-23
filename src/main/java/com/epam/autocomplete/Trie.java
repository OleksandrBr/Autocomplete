/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.autocomplete;

/**
 *
 * @author Oleksandr_Briukhovych
 */
public interface Trie {

    // Добавляет в Trie пару слово - term, и его вес - weight.
    // В качестве веса используйте длину слова
    public void add(Tuple tuple);

    // есть ли слово в Trie
    public boolean contains(String word);

    // удаляет слово из Trie
    public boolean delete(String word);

    // итератор по всем словам, обход в ширину
    public Iterable<String> words();

    // итератор по всем словам, начинающимся с pref, обход в ширину
    public Iterable<String> wordsWithPrefix(String pref);

    // к-во слов в Trie
    public int size();

}

