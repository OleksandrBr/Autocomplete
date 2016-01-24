/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.autocomplete;

import java.util.Objects;

/**
 *
 * @author Oleksandr_Briukhovyc
 */
public class Tuple {
    private final String term;
    private final Integer weight;
    
    public Tuple(String term){
        this.term = term;
        this.weight = term.length();
    }
    
    public String getTerm(){
        return term;
    }
    
    public Integer getWeight(){
        return weight;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.term);
        hash = 31 * hash + Objects.hashCode(this.weight);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tuple other = (Tuple) obj;
        if (!Objects.equals(this.term, other.term)) {
            return false;
        }
        return Objects.equals(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return "Tuple{" + "term=" + term + ", weight=" + weight + '}';
    }
}
