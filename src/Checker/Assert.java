package Checker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import DBconection.*;
/**
 * Created by db2admin on 5/23/2017.
 */
public class Assert {
    public static String retVal = null;

    public static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    public static String CheckForm1(String indeks ,String sifra) {
        if(indeks.length() == 0)
            return "Obavezno polje: Indeks!";
        if(sifra.length() == 0)
            return "Obavezno polje: Sifra predmeta!";
        if (!isNumeric(indeks) || indeks.length() != 8) {
            return "Pogresnan format indeksa! (Primer:ggggiiii)";
        }
        return "";
    }

}
