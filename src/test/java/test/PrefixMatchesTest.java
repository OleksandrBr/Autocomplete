/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;
import com.autocomplete.PrefixMatches;
import com.autocomplete.Trie;
import com.autocomplete.Tuple;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Oleksandr_Briukhovych
 */

public class PrefixMatchesTest {
    
    private PrefixMatches prefix;
    private Trie trie;
    
    public PrefixMatchesTest() {
    }
    
    @Before
    public void setUp() {
        trie = mock(Trie.class);
        prefix = new PrefixMatches(trie);
    }
    
    /**
     * testAdd()
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        String[] strings = {"all fingal", "all fin will be fine"};
        Tuple tuple = new Tuple("fin");
        Tuple tuple2 = new Tuple("fingal");
        Tuple tuple3 = new Tuple("be");
        int expResult = 6;
        int result = prefix.add(strings);
        
        verify(trie).add(tuple);
        verify(trie).add(tuple2);
        verify(trie, never()).add(tuple3);
        assertEquals(expResult, result);
    }

    /**
     * testContains()
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        String trueWord = "true";
        String falseWord = "false";
        
        when(trie.contains(trueWord)).thenReturn(true);
        when(trie.contains(falseWord)).thenReturn(false);

        assertEquals(true, prefix.contains(trueWord));
        assertEquals(false, prefix.contains(falseWord));
    }

    /**
     * testDelete()
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String trueWord = "true";
        String falseWord = "false";
        
        when(trie.delete(trueWord)).thenReturn(true);
        when(trie.delete(falseWord)).thenReturn(false);

        assertEquals(true, prefix.delete(trueWord));
        assertEquals(false, prefix.delete(falseWord));
    }

    /**
     * testSize()
     */
    @Test
    public void testSize() {
        System.out.println("size");
        when(trie.size()).thenReturn(8);
        int result = prefix.size();
        assertEquals(8, result);
    }

    /**
     * testWordsWithPrefixStringint()
     */
    @Test
    public void testWordsWithPrefixStringint() {
        System.out.println("wordsWithPrefix");
        String pref = "fin";
        int k = 1;
        List<String> trieReturn = new ArrayList<>();
        trieReturn.add("fin");
        trieReturn.add("fine");
        trieReturn.add("finest");
        trieReturn.add("finestly");
        when(trie.wordsWithPrefix(pref)).thenReturn(trieReturn);
        
        List<String> expResult = new ArrayList<>();
        expResult.add("fin");
        
        assertEquals(expResult, prefix.wordsWithPrefix(pref, k));

    }

    /**
     * testWordsWithPrefixString()
     */
    @Test
    public void testWordsWithPrefixString() {
        System.out.println("wordsWithPrefix");
        String pref = "fin";
        
        List<String> trieReturn = new ArrayList<>();
        trieReturn.add("fin");
        trieReturn.add("fine");
        trieReturn.add("finest");
        trieReturn.add("finestly");
        
        when(trie.wordsWithPrefix(pref)).thenReturn(trieReturn);
        
        List<String> expResult = new ArrayList<>();
        
        expResult.add("fin");
        expResult.add("fine");
        
        assertEquals(expResult, prefix.wordsWithPrefix(pref, 3));

    }

    /**
     * main method test.
     */
    @Test
    public void testMain() {
        System.out.println("main");
    }
    
}
