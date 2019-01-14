package com.zygadlo;

import java.util.ArrayList;

public class Ruletka {

    public ArrayList<Chromosom> lista;
    private Funkcja funkcja;
    double[] tab;
    double[] tablicaDoRuletki;
    double sumaWartosci;
    double wspolczynnikKrzyzowki;
    double getWspolczynnikMutacji;

    public Ruletka(ArrayList<Chromosom> lista, Funkcja funkcja, double wspK, double wspM) {
        this.lista = lista;
        this.funkcja = funkcja;
        tab = new double[lista.size()];
        wspolczynnikKrzyzowki = wspK;
        getWspolczynnikMutacji = wspM;
    }

    public void UpdateRuletkaList(ArrayList<Chromosom> listaChro) {
        this.lista.clear();
        for (Chromosom chromosom : listaChro) {
            lista.add(chromosom);
        }
    }

    public void PoliczWartosci() {
        for (int j = 0; j < lista.size(); j++) {
            tab[j] = 0;
        }
        for (int i = 0; i < lista.size(); i++) {
            tab[i] = funkcja.PoliczWartoscFunkcjiDlaDanegoX(lista.get(i).ZamianaDwojkowyNaDziesietny());
        }
    }

    public int SumaWartości() {
        int suma = 0;
        for (double liczba : tab) {
            suma += liczba;
        }
        return suma;
    }

    public int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public void stworzKoloRuletki() {
        tablicaDoRuletki = new double[lista.size()];
        sumaWartosci = SumaWartości();
        for (int i = 0; i < lista.size(); i++) {
            tablicaDoRuletki[i] = (tab[i] / sumaWartosci) * 100;
        }
    }

    public ArrayList<Chromosom> wyborChromosomowDoGenetyki() {
        double b, a = 0;
        ArrayList<Chromosom> nowaLista = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            b = randomWithRange(0, 99);
            a = 0;
            for (int k = 0; k < tablicaDoRuletki.length; k++) {
                if ((b > a||a==b) && (b < (a + tablicaDoRuletki[k])||b==(a+tablicaDoRuletki[k]))) {
                    nowaLista.add(new Chromosom(lista.get(k).getOsobnik()));
                    break;
                }
                a += tablicaDoRuletki[k];
            }
        }
        return nowaLista;
    }

    public void CzyMajaSieKrzyzowac() {
        double a;
        int b;
        for (int i = 0; i < lista.size() - 1; i+=2) {
             a = Math.random();
             b = randomWithRange(2, lista.get(0).getOsobnik().length);
            if (a <= wspolczynnikKrzyzowki)
                lista.get(i).Krzyzowanie(lista.get(i + 1), b);
        }

    }

    public void CzyMajaSieMutowac() {
        double a;
        int b;
        for (int i = 0; i < lista.size(); i ++) {
   //         System.out.println("-----------------------");
   //         System.out.println(lista+"  Lista Chromosomow Przed Sprawdzeniem Warunku ");
             a = Math.random();
   //         System.out.println("współczynnik mutacji: "+a);
             b = randomWithRange(1, lista.get(0).getOsobnik().length);
   //         System.out.println("gen do mutacji: "+(b));
            if (a <= getWspolczynnikMutacji) {
                lista.get(i).Mutacja(b);
   //             System.out.println("mutuje chromosom nr: " + (i+1) );
            }
   //         System.out.println("-----------------------");
        }

    }

    public void MaxZWyniku(ArrayList<Chromosom> wynikii){
        int a=0;
        int max=funkcja.PoliczWartoscFunkcjiDlaDanegoX(wynikii.get(0).ZamianaDwojkowyNaDziesietny());
                for (int i=1;i<wynikii.size();i++){
                    if (max<funkcja.PoliczWartoscFunkcjiDlaDanegoX(wynikii.get(i).ZamianaDwojkowyNaDziesietny())){
                        max=funkcja.PoliczWartoscFunkcjiDlaDanegoX(wynikii.get(i).ZamianaDwojkowyNaDziesietny());
                        a=i;
                    }
                }
        System.out.println("Najlepszy wynik: "+max);
        System.out.println("Chromosom: "+wynikii.get(a));
    }
    }

