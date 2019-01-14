package com.zygadlo;

import javafx.scene.paint.Stop;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int StopienFunkcji;
        //Scanner scanner= new Scanner(System.in);
        //System.out.println("Którego stopnia będzie funkcja?");
        //StopienFunkcji=scanner.nextInt();


        ArrayList<Integer> listaWspolczynnikow=new ArrayList<>();
        //for (int i=0;i<=(StopienFunkcji);i++)
        //listaWspolczynnikow.add(scanner.nextInt());

        listaWspolczynnikow.add(-1);
        listaWspolczynnikow.add(32);
        listaWspolczynnikow.add(0);
        listaWspolczynnikow.add(5);

        int[] tab= new int[10];
        int licznnik=0;
        int licznik=0;
        int suma=0;
        int sumaWyniku=0;
        ArrayList<Chromosom> wynik=new ArrayList<>();


        ArrayList<Chromosom> populacja= new ArrayList<>();
        populacja.add(new Chromosom(5));
        populacja.add(new Chromosom(5));
        populacja.add(new Chromosom(5));
        populacja.add(new Chromosom(5));
        populacja.add(new Chromosom(5));
        populacja.add(new Chromosom(5));

        for (Chromosom osobnik:populacja) {
            for (Integer liczba:osobnik.getOsobnik()) {
                System.out.print(liczba);
            }
            System.out.println();
        }

        Ruletka ruletka= new Ruletka(populacja,new Funkcja(listaWspolczynnikow),0.8,0.2);

        ruletka.PoliczWartosci();
        sumaWyniku=ruletka.SumaWartości();
        System.out.println();


        while (licznnik<5) {
            licznnik=0;
            licznik++;
            //System.out.println("przed operacjami: "+ populacja);
            ruletka.stworzKoloRuletki();
            ruletka.UpdateRuletkaList(ruletka.wyborChromosomowDoGenetyki());
            //System.out.println("po wyborze do genetyki: "+ populacja);
            ruletka.CzyMajaSieKrzyzowac();
            //System.out.println("po krzyzowaniu: "+ populacja);
            ruletka.CzyMajaSieMutowac();
            //System.out.println("po mutacji: "+ populacja);

//            for (Chromosom osobnik : populacja) {
//                for (Integer liczba : osobnik.getOsobnik()) {
//                    System.out.print(liczba);
//                }
//                System.out.print("  ");
//            }
            System.out.println();

            ruletka.PoliczWartosci();

            suma=ruletka.SumaWartości();
            System.out.println(ruletka.SumaWartości());
            for (int i=0;i<tab.length;i++){
                if (tab[i]==0) {
                    tab[i] = suma;
                    break;
                }
                else if(tab[i]<suma) {
                    tab[i] = suma;
                    break;
                }
                    licznnik++;
            }

            if (suma>sumaWyniku){
                wynik.clear();
                sumaWyniku=suma;
                for (Chromosom chrom:populacja) {
                    wynik.add(chrom);
                }

            }

        }
        ruletka.MaxZWyniku(wynik);
        System.out.println(licznik);
        //System.out.println();
        //System.out.println(sumaWyniku);
        //System.out.println(wynik);
    }

}
