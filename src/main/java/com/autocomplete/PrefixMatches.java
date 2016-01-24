/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autocomplete;

import com.trie.RWayTrie;
import com.trie.Trie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Oleksandr_Briukhovych
 */
public class PrefixMatches {

    private final Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    // Формирует in-memory словарь слов. Метод принимает слово, строку, массив слов/строк. Если приходит строка, то она разбивается на слова по пробелам.
    // В словарь должны добавляться слова длиннее 2х символов. Предполагается что знаки пунктуации отсутствуют.
    public int add(String... strings) {
        int count = 0;
        for (String arg : strings) {
            String mas[] = arg.split(" ");
            for (String item : mas) {
                if (item.length() > 2) {
                    count++;
                    Tuple tuple = new Tuple(item);
                    trie.add(tuple);
                }
            }
        }
        return count;
    }

    // есть ли слово в словаре
    public boolean contains(String word) {
        return trie.contains(word);
    }

    // удаляет слово из словаря
    public boolean delete(String word) {
        return trie.delete(word);
    }

    // к-во слов в словаре
    public int size() {
        return trie.size();
    }

    // если введенный pref длиннее или равен 2м символам, то возвращает набор слов k разных длин начиная с минимальной, и начинающихся с данного префикса pref.
    // Пример, даны слова следующей длины и pref='abc':
    // abc 3
    // abcd 4
    // abce 4
    // abcde 5
    // abcdef 6
    // - при k=1 возвращаются 'abc'
    // - при k=2 возвращаются 'abc', 'abcd', 'abce'
    // - при k=3 возвращаются 'abc', 'abcd', 'abce', 'abcde'
    // - при k=4 возвращаются 'abc', 'abcd', 'abce', 'abcde', 'abcdef'
    
    public Iterable<String> wordsWithPrefix(String pref, int k) {
        List<String> words = new ArrayList<>();
        if (pref.length() >= 2) {
            for (String word : trie.wordsWithPrefix(pref)) {
                if (word.length() <= (pref.length() + k - 1)) {
                    words.add(word);
                }
            }
        }
        return words;
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return wordsWithPrefix(pref, 3);
    }

    public static void main(String... args) {
        Trie trie = new RWayTrie();
        PrefixMatches m = new PrefixMatches(trie);
        m.add("fin", "fined", "all will be fine", "finest", "finesty", "finestly", "fineylts");
        System.out.println(m.contains("bbb"));
        System.out.println(m.contains("all"));
        System.out.println(m.size());
        //System.out.println(m.delete("fined"));
        System.out.println(m.contains("fine"));

        Iterable<String> list = m.wordsWithPrefix("fin", 6);

        System.out.println("\n" + " L I S T" + "\n");
        for (String str : list) {
            System.out.println(str);
        }

    }
}
