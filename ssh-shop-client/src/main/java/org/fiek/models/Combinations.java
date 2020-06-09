package org.fiek.models;

import java.util.ArrayList;

public class Combinations {
    private ArrayList<String> combination;

    public Combinations(ArrayList<String> combination) {
        this.combination = combination;
    }
    public ArrayList<String> getCombinations(){
        return combination;
    }

    public void setCombinations(ArrayList<String> combinations){
        this.combination = combinations;
    }

}
