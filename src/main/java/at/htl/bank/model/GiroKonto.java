package at.htl.bank.model;

public class GiroKonto extends BankKonto{

    private double gebuehr;

    public GiroKonto(String name, double gebuehr){
        super(name,gebuehr);
    }

    public GiroKonto(String name, double anfangsBestand, double gebuehr){
        super(name, anfangsBestand);
        this.gebuehr = gebuehr;
    }

    @Override
    public void einzahlen (double betrag){
        super.kontoStand += (betrag - 0.02);
    }

    @Override
    public void abheben (double betrag){
        super.kontoStand -= (betrag - 0.02);
    }

}
