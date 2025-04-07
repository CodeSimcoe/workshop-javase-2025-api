package fr.sciam.workshop.javase.classfile;

public class AccumulateurAvance {

  private int valeur = 0;

  public int ajouter(int increment) {
    int oldValue = this.valeur;
    this.valeur += increment;
    this.journaliser(oldValue);
    return this.valeur;
  }

  public int soustraire(int increment) {
    int oldValue = this.valeur;
    this.valeur -= increment;
    this.journaliser(oldValue);
    return this.valeur;
  }

  private void journaliser(int oldValue) {
    System.out.println(oldValue + " -> " + this.valeur);
  }
}
