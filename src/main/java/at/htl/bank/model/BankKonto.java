package at.htl.bank.model;

public class BankKonto {
    private String name;
    protected double kontoStand;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getKontoStand() {
        return kontoStand;
    }

    public BankKonto(String name){
        setName(name);
        this.kontoStand = 0;
    }

    public BankKonto (String name, double anfangsBestand){
        setName(name);
        this.kontoStand = anfangsBestand;
    }


    public void einzahlen(double betrag ){
        kontoStand += betrag;
    }

    public void abheben (double betrag){
        kontoStand -= betrag;
    }

  
}
