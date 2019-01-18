package at.htl.bank.business;

import at.htl.bank.model.BankKonto;
import at.htl.bank.model.GiroKonto;
import at.htl.bank.model.SparKonto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Legen Sie eine statische Liste "konten" an, in der Sie die einzelnen Konten speichern
 *
 */
public class Main {

  // die Konstanten sind package-scoped wegen der Unit-Tests
  static final double GEBUEHR = 0.02;
  static final double ZINSSATZ = 3.0;

  static final String KONTENDATEI = "erstellung.csv";
  static final String BUCHUNGSDATEI = "buchungen.csv";
  static final String ERGEBNISDATEI = "ergebnis.csv";

  static  ArrayList<BankKonto> konten =  new ArrayList<>();
  
  /**
   * Führen Sie die drei Methoden erstelleKonten, fuehreBuchungenDurch und
   * findKontoPerName aus
   *
   * @param args
   */
  public static void main(String[] args) {
    erstelleKonten(KONTENDATEI);
    fuehreBuchungenDurch(BUCHUNGSDATEI);
  }

  /**
   * Lesen Sie aus der Datei (erstellung.csv) die Konten ein.
   * Je nach Kontentyp erstellen Sie ein Spar- oder Girokonto.
   * Gebühr und Zinsen sind als Konstanten angegeben.
   *
   * Nach dem Anlegen der Konten wird auf der Konsole folgendes ausgegeben:
   * Erstellung der Konten beendet
   *
   * @param datei KONTENDATEI
   */
  private static void erstelleKonten(String datei) {
    String lines;
    String[] linessplit;
    BankKonto kontos;

    try(Scanner sc = new Scanner(new FileReader(datei))) {
    sc.nextLine();
    while(sc.hasNextLine()){
      lines = sc.nextLine();
      linessplit = lines.split(";");

      if (linessplit[0].equals("Sparkonto")){
        kontos = new SparKonto(linessplit[1],Double.parseDouble(linessplit[2]), ZINSSATZ);
        konten.add(kontos);
      }

      if (linessplit[0].equals("Girokonto")){
        kontos = new SparKonto(linessplit[1],Double.parseDouble(linessplit[2]), GEBUEHR);
        konten.add(kontos);
      }
    }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

  }

  /**
   * Die einzelnen Buchungen werden aus der Datei eingelesen.
   * Es wird aus der Liste "konten" jeweils das Bankkonto für
   * kontoVon und kontoNach gesucht.
   * Anschließend wird der Betrag vom kontoVon abgebucht und
   * der Betrag auf das kontoNach eingezahlt
   *
   * Nach dem Durchführen der Buchungen wird auf der Konsole folgendes ausgegeben:
   * Buchung der Beträge beendet
   *
   * Tipp: Verwenden Sie hier die Methode 'findeKontoPerName(String name)'
   *
   * @param datei BUCHUNGSDATEI
   */
  private static void fuehreBuchungenDurch(String datei) {
    String lines;
    String[] linessplit;

    try(Scanner sc = new Scanner(new FileReader(datei))) {
        sc.nextLine();

        while(sc.hasNextLine()){
          lines = sc.nextLine();
          linessplit = lines.split(";");

          findeKontoPerName(linessplit[0]).abheben(Double.parseDouble(linessplit[2]));
          findeKontoPerName(linessplit[1]).einzahlen(Double.parseDouble(linessplit[2]));
        }

      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
  }

  /**
   * Es werden die Kontostände sämtlicher Konten in die ERGEBNISDATEI
   * geschrieben. Davor werden bei Sparkonten noch die Zinsen dem Konto
   * gutgeschrieben
   *
   * Die Datei sieht so aus:
   *
   * name;kontotyp;kontostand
   * Susi;SparKonto;875.5
   * Mimi;GiroKonto;949.96
   * Hans;GiroKonto;1199.96
   *
   * Vergessen Sie nicht die Überschriftenzeile
   *
   * Nach dem Schreiben der Datei wird auf der Konsole folgendes ausgegeben:
   * Ausgabe in Ergebnisdatei beendet
   *
   * @param datei ERGEBNISDATEI
   */
  private static void schreibeKontostandInDatei(String datei) {
        System.out.println("schreibeKontostandInDatei noch nicht implementiert");
  }

  /**
   */
  /**
   * Durchsuchen Sie die Liste "konten" nach dem ersten Konto mit dem als Parameter
   * übergebenen Namen
   * @param name
   * @return Bankkonto mit dem gewünschten Namen oder NULL, falls der Namen
   *         nicht gefunden wird
   */
  public static BankKonto findeKontoPerName(String name) {

    for(int i = 0; i < konten.size(); i++){
      if(konten.get(i).getName().equals(name)){
        return konten.get(i);
      }
    }

    return null;
  }

}
