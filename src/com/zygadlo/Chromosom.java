package com.zygadlo;

public class Chromosom {
    private int[] osobnik;
    private int[] krzyz;
    private int iloscBitow;
    private int fenotyp=0;

    public Chromosom(int liczbaBitów){
        this.iloscBitow=liczbaBitów;
        osobnik= new int[iloscBitow];
        krzyz= new int[iloscBitow];
        for (int i=0;i<iloscBitow;i++){
            osobnik[i]=(Math.random()<0.5)?0:1;
        }
    }

    public Chromosom(int[] tab){
        this.iloscBitow=tab.length;
        osobnik= new int[iloscBitow];
        krzyz= new int[iloscBitow];
        for (int i=0;i<iloscBitow;i++){
            osobnik[i]=tab[i];
        }
    }

    public int[] getOsobnik() {
        return osobnik;
    }

    public void Mutacja(int index) {
        if (osobnik[index - 1] == 1) {
            osobnik[index - 1] = 0;
        } else {
            osobnik[index - 1] = 1;
        }
    }

    public void Krzyzowanie(Chromosom chromosom,int index){
        for (int i=0;i<osobnik.length;i++){
            if (i>=index-1){
                krzyz[i]=osobnik[i];
                osobnik[i]=chromosom.getOsobnik()[i];
                chromosom.getOsobnik()[i]=krzyz[i];
            }
        }
    }

    public int ZamianaDwojkowyNaDziesietny(){
        fenotyp=0;
        int a=0;
        for(int i=iloscBitow-1;i>=0;i--){
            fenotyp+=osobnik[i]*Math.pow(2,a);
            a++;
        }
        return fenotyp;
    }

    @Override
    public String toString(){
        return String.valueOf(osobnik[0])+String.valueOf(osobnik[1])+String.valueOf(osobnik[2])
                +String.valueOf(osobnik[3])+String.valueOf(osobnik[4]);
    }

    public void setOsobnik(int[] tab){
        for(int i=0;i<tab.length;i++){

        }
    }
}
