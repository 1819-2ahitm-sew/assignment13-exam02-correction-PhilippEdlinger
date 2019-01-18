package at.htl.bank.model;

public class SparKonto extends BankKonto {

    private double zinsSatz;

    public SparKonto(String name, double zinsSatz){
        super(name , zinsSatz);
    }

    public SparKonto(String name, double anfangsbestand, double zinsSatz){
        super(name, anfangsbestand);
        this.zinsSatz = zinsSatz;
    }

    public void zinsenAnrechnen(){
        kontoStand *=  (1 + (zinsSatz / 100));
    }
}
