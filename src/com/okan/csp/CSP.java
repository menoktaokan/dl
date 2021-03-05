package com.okan.csp;


import java.util.List;
import java.util.stream.Collectors;

public class CSP {

    static List<List<Integer>> kenarlar;
    static List<List<Integer>> cozum;
    static List<Integer> dugumler;
    static List<Integer> renkler;

    public static void main(String[] args) {

        kenarlar = List.of(
                List.of(1, 3),
                List.of(1, 2),
                List.of(2, 4),
                List.of(3, 2),
                List.of(3, 4)
        );

        cozum = List.of(
                List.of(1, 1),
                List.of(2, 2),
                List.of(3, 3),
                List.of(4, 1)
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


    }

    static List<Integer> komsulariGetir(Integer dugum, List<List<Integer>> kenarListesi) {
        return kenarListesi.stream().filter(kenarlar -> kenarlar.get(0).equals(dugum) || kenarlar.get(1).equals(dugum))
                .map(kenarlar -> {
                            if (kenarlar.get(0).equals(dugum))
                                return kenarlar.get(1);
                            return kenarlar.get(0);
                        }
                ).collect(Collectors.toList());
    }

    static Integer rengiGetir(Integer dugum, List<List<Integer>> cozum) {
        return cozum.stream().filter(dugumler -> dugumler.get(0).equals(dugum))
                .findFirst().orElse(List.of(0, 0)).get(1);
    }

    static Boolean dugumuKontrolEt(Integer dugum, List<List<Integer>> kenarListesi, List<List<Integer>> cozum) {

        return komsulariGetir(dugum, kenarListesi).stream()
                .noneMatch(komsu -> rengiGetir(komsu, cozum).equals(rengiGetir(dugum, cozum)));
    }

    static Boolean csp(List<List<Integer>> kenarListesi, List<Integer> dugumListesi, List<List<Integer>> cozumListesi) {

        return dugumListesi.stream()
                .allMatch(dugum -> dugumuKontrolEt(dugum, kenarListesi, cozumListesi));
    }

}