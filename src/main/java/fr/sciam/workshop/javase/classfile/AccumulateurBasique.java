package fr.sciam.workshop.javase.classfile;

public class AccumulateurBasique {

  private int valeur = 0;

  public int ajouter(int increment) {
    this.valeur += increment;
    return this.valeur;
  }
}
