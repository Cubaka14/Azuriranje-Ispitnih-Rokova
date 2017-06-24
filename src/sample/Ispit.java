package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by db2admin on 6/23/2017.
 */
public class Ispit {
    String indeks;
    String idPredmeta;
    String godinaRoka;
    String oznakaRoka;
    String godina;
    String semestar;
    String datumPrijave;
    String nacinPrijave;
    String brojPolaganja;
    String statusPrijave;
    String datumPismenog;
    String bodoviPismenog;
    String datumUsmenog;
    String bodoviUsmenog;
    String bodovi;
    String ocena;
    String nastavnik;
    String napomena;

    public Ispit(
            String indeks,
            String idPredmeta,
            String godinaRoka,
            String oznakaRoka,
            String godina,
            String semestar,
            String datumPrijave,
            String nacinPrijave,
            String brojPolaganja,
            String statusPrijave,
            String datumPismenog,
            String bodoviPismenog,
            String datumUsmenog,
            String bodoviUsmenog,
            String bodovi,
            String ocena,
            String nastavnik,
            String napomena){
        this.indeks=indeks;
        this.idPredmeta = idPredmeta;
        this.godinaRoka=godinaRoka;
        this.oznakaRoka=oznakaRoka;
        this.godina=godina;
        this.semestar=semestar;
        this.datumPrijave=datumPrijave;
        this.nacinPrijave=nacinPrijave;
        this.brojPolaganja=brojPolaganja;
        this.statusPrijave=statusPrijave;
        this.datumPismenog=datumPismenog;
        this.bodoviPismenog=bodoviPismenog;
        this.datumUsmenog=datumUsmenog;
        this.bodoviUsmenog=bodoviUsmenog;
        this.bodovi=bodovi;
        this.ocena=ocena;
        this.nastavnik=nastavnik;
        this.napomena=napomena;
    }

    public String getIndeks() {
        return indeks;
    }

    public String getIdPredmeta() {
        return idPredmeta;
    }

    public String getGodinaRoka() {
        return godinaRoka;
    }

    public String getOznakaRoka() {
        return oznakaRoka;
    }

    public String getGodina() {
        return godina;
    }

    public String getSemestar() {
        return semestar;
    }

    public String getDatumPrijave() {
        return datumPrijave;
    }

    public String getNacinPrijave() {
        return nacinPrijave;
    }

    public String getBrojPolaganja() {
        return brojPolaganja;
    }

    public String getStatusPrijave() {
        return statusPrijave;
    }

    public String getDatumPismenog() {
        return datumPismenog;
    }

    public String getBodoviPismenog() {
        return bodoviPismenog;
    }

    public String getDatumUsmenog() {
        return datumUsmenog;
    }

    public String getBodoviUsmenog() {
        return bodoviUsmenog;
    }

    public String getBodovi() {
        return bodovi;
    }

    public String getOcena() {
        return ocena;
    }

    public String getNastavnik() {
        return nastavnik;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setIndeks(String indeks) {
        this.indeks = indeks;
    }

    public void setIdPredmeta(String idPredmeta) {
        this.idPredmeta = idPredmeta;
    }

    public void setGodinaRoka(String godinaRoka) {
        this.godinaRoka = godinaRoka;
    }

    public void setOznakaRoka(String oznakaRoka) {
        this.oznakaRoka = oznakaRoka;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

    public void setSemestar(String semestar) {
        this.semestar = semestar;
    }

    public void setDatumPrijave(String datumPrijave) {
        this.datumPrijave = datumPrijave;
    }

    public void setNacinPrijave(String nacinPrijave) {
        this.nacinPrijave = nacinPrijave;
    }

    public void setBrojPolaganja(String brojPolaganja) {
        this.brojPolaganja = brojPolaganja;
    }

    public void setStatusPrijave(String statusPrijave) {
        this.statusPrijave = statusPrijave;
    }

    public void setDatumPismenog(String datumPismenog) {
        this.datumPismenog = datumPismenog;
    }

    public void setBodoviPismenog(String bodoviPismenog) {
        this.bodoviPismenog = bodoviPismenog;
    }

    public void setDatumUsmenog(String datumUsmenog) {
        this.datumUsmenog = datumUsmenog;
    }

    public void setBodoviUsmenog(String bodoviUsmenog) {
        this.bodoviUsmenog = bodoviUsmenog;
    }

    public void setBodovi(String bodovi) {
        this.bodovi = bodovi;
    }

    public void setOcena(String ocena) {
        this.ocena = ocena;
    }

    public void setNastavnik(String nastavnik) {
        this.nastavnik = nastavnik;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }
}
