package com.okan;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UCSApp {

    static List<List<Integer>> waysFollowed = new ArrayList<>();
    static List<List<Integer>> harita;

    public static void main(String[] args) {

        /* Haritay� doldur */
        harita = List.of(
                List.of(1, 2, 1),
                List.of(1, 3, 3),
                List.of(2, 3, 2),
                List.of(3, 2, 2),
                List.of(2, 4, 14),
                List.of(2, 7, 8),
                List.of(3, 5, 1),
                List.of(5, 6, 1)
        );

        Integer dugum = 2;

        System.out.println("harita: " + harita);

        System.out.println(dugum + "'nin alt yollar�: " + alternatives(harita, dugum));

        System.out.println("[2, 3, 2] ile [2, 7, 8]'den ucuz olan�: " + compare(harita.get(2), harita.get(5)));

        System.out.println("**");

        ucs(harita, 1, 5);
        System.out.println("izlenen yol: " + waysFollowed);
    }

    /**
     * Verilen ba�lang�� noktas�ndan biti� noktas�na kadar en ucuz maliyetli yollardan ge�er ve listeye ekler.
     *
     * @param harita        Kullan�lacak harita
     * @param baslangic     Ba�lang�� noktas�
     * @param bitis         Biti� noktas�
     */
    public static void ucs(List<List<Integer>> harita, Integer baslangic, Integer bitis) {

        if (baslangic.equals(bitis) || minAlt(alternatives(harita, baslangic)) == null) {
            if (!alternatives(harita, baslangic).isEmpty())
                waysFollowed.add(alternatives(harita, baslangic).get(0));
            return;
        }

        waysFollowed.add(minAlt(alternatives(harita, baslangic)));
        ucs(harita, minAlt(alternatives(harita, baslangic)).get(1), bitis);
    }

    /**
     * Haritadaki verilen yol i�in mevcut alternatifleri d�ner
     *
     * @param harita    Kullan�lacak harita
     * @param dugum     Alternatifleri al�nacak d���m
     * @return          Alternatif yollar
     */
    public static List<List<Integer>> alternatives(List<List<Integer>> harita, Integer dugum) {
        return harita.stream().filter(l -> l.get(0).equals(dugum)).collect(Collectors.toList());
    }
    
    /**
     * Verilen iki yoldan ucuz olan�n� d�ner
     *
     * @param yol1  Kar��la�t�r�lacak birinci yol
     * @param yol2  Kar��la�t�r�lacak ikinci yol
     * @return      Ucuz maliyetli yol
     */
    public static List<Integer> compare(List<Integer> yol1, List<Integer> yol2) {
        if (yol2.isEmpty())
            return yol1;
        return (yol1.get(2) > yol2.get(2)) ? yol2 : yol1;
    }

    /**
     * Verilen alternatif yollardan en ucuz maliyetli olan� d�ner
     * @param list  Alternatif yollar
     * @return      Alternarifler aras�nda en ucuz olan�
     */
    public static List<Integer> minAlt(List<List<Integer>> list) {
        if (list == null || list.isEmpty())
            return null;
        else if (list.size() == 1)
            return list.get(0);
        else return compare(list.get(0), minAlt(list.subList(1, list.size())));
    }
}
