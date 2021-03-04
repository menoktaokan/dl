package com.okan.csp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSPWithMap {

    public static Map<Integer, List<Integer>> kenarlar;
    public static Map<Integer, Integer> cozum;
    public static List<Integer> dugumler;
    public static List<Integer> renkler;
    public static Map<Integer, Integer> uretilenCozum;

    public static void main(String[] args) {
        kenarlar = Map.of(
                1, List.of(2, 3),
                2, List.of(1, 3, 4),
                3, List.of(1, 2, 4),
                4, List.of(2, 3)
        );

        cozum = Map.of(
                1, 1,
                2, 2,
                3, 3,
                4, 1
        );

        dugumler = List.of(1, 2, 3, 4);

        renkler = List.of(1, 2, 3);

        System.out.println();

        System.out.println("1'in komşuları: " + komsulariGetir(1, kenarlar));
        System.out.println("2'in komşuları: " + komsulariGetir(2, kenarlar));
        System.out.println("3'in komşuları: " + komsulariGetir(3, kenarlar));
        System.out.println("4'in komşuları: " + komsulariGetir(4, kenarlar));

        System.out.println();

        System.out.println("1'in rengi: " + rengiGetir(1, cozum));

        System.out.println();

        System.out.println("1 dugumuKontrolEt : " + dugumuKontrolEt(1, kenarlar, cozum));
        System.out.println("2 dugumuKontrolEt : " + dugumuKontrolEt(2, kenarlar, cozum));
        System.out.println("3 dugumuKontrolEt : " + dugumuKontrolEt(3, kenarlar, cozum));
        System.out.println("4 dugumuKontrolEt : " + dugumuKontrolEt(4, kenarlar, cozum));

        System.out.println();

        System.out.println("csp: " + csp(kenarlar, dugumler, cozum));

        System.out.println();

        cozumUret(kenarlar, dugumler, renkler);
        System.out.println(uretilenCozum);

    }
    static List<Integer> komsulariGetir(Integer dugum, Map<Integer, List<Integer>> kenarListesi){
        return kenarListesi.get(dugum);
    }

    static Integer rengiGetir(Integer dugum, Map<Integer, Integer> cozum){
        if(cozum == null || cozum.isEmpty())
            return 0;
        return cozum.get(dugum) == null ? 0 : cozum.get(dugum);
    }

    static Boolean dugumuKontrolEt(Integer dugum, Map<Integer, List<Integer>> kenarListesi, Map<Integer, Integer> cozum) {
        return komsulariGetir(dugum, kenarListesi).stream()
                .noneMatch(komsu -> rengiGetir(komsu, cozum).equals(rengiGetir(dugum, cozum)));
    }

    static Boolean csp(Map<Integer, List<Integer>> kenarListesi, List<Integer> dugumListesi, Map<Integer, Integer> cozumListesi) {

        return dugumListesi.stream()
                .allMatch(dugum -> dugumuKontrolEt(dugum, kenarListesi, cozumListesi));
    }

    static void cozumUret(Map<Integer, List<Integer>> kenarlar, List<Integer> dugumler, List<Integer> renkler){

        uretilenCozum = new HashMap<>();

        for(Integer dugum : dugumler){
            if(rengiGetir(dugum, uretilenCozum) == 0){
                int i = 0;
                uretilenCozum.put(dugum, renkler.get(i));
                while (!dugumuKontrolEt(dugum, kenarlar, uretilenCozum)){
                    uretilenCozum.remove(dugum);
                    i++;
                    if(i >= renkler.size())
                        break;
                    uretilenCozum.put(dugum, renkler.get(i));
                }
            }
        }
    }
}