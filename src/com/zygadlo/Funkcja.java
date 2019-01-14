package com.zygadlo;

import java.util.ArrayList;

public class Funkcja {

    private ArrayList<Integer> listaWspolczynnikow;     //2,1  >>  x^1+2
    private int wartosc;

    public Funkcja(ArrayList<Integer>list){
        listaWspolczynnikow=list;
    }

    public int PoliczWartoscFunkcjiDlaDanegoX(int x){
        wartosc=0;
        for (int i=0;i<listaWspolczynnikow.size()-1;i++){
            wartosc=(wartosc+listaWspolczynnikow.get(i))*x;
        }
        wartosc+=listaWspolczynnikow.get(listaWspolczynnikow.size()-1);
        return wartosc;
    }
}
