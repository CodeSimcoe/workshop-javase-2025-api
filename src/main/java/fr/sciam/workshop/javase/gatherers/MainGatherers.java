package fr.sciam.workshop.javase.gatherers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;
import java.util.stream.Gatherer;
import java.util.stream.Gatherer.Integrator;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public class MainGatherers {

  private static final List<Integer> NOMBRES = List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 1, 0);

  public static void main(String[] args) {

    afficherNombresSequentiel("Map double", definirGathererMapDouble());

    afficherNombresSequentiel("Map grands doubles", definirGathererMapGrandsDoubles());

    afficherNombresSequentiel("Court-circuit", definirGathererCourtCircuit());

    afficherNombresSequentiel("Séquentiel avec état", definirGathererSequentielAvecEtat());

    afficherNombresSequentiel("Groupe deux par deux", definirGathererGroupeDeuxParDeux());

    afficherNombresParallele("Parallèle plus grand élément", definirGathererParallelePlusGrandElement());

    System.out.println("isRejecting()");
    utiliserIsRejecting();
    System.out.println();

    afficherNombresSequentiel("Gatherers::fold", definirGathererFold());

    afficherNombresSequentiel("Gatherers::scan", definirGathererScan());

    afficherNombresSequentiel("Gatherers::windowFixed", definirGathererWindowFixed());

    afficherNombresSequentiel("Gatherers::windowSliding", definirGathererWindowSliding());

    afficherNombresSequentiel("Gatherers::mapConcurrent", definirGathererMapConcurrent());

    afficherNombresSequentiel("Composition", composerGatherers());
  }

  private static Gatherer<Integer, Void, Integer> definirGathererMapDouble() {
    return Gatherer.of(
      Integrator.ofGreedy((state, element, downstream) -> {
        if (!downstream.isRejecting()) {
          return downstream.push(element * 2);
        }
        return true;
      })
    );
  }

  private static Gatherer<Integer, Void, Integer> definirGathererMapGrandsDoubles() {
    return null;
  }

  private static Gatherer<Integer, Void, Integer> definirGathererCourtCircuit() {
    return null;
  }

  private static Gatherer<Integer, ?, Integer> definirGathererSequentielAvecEtat() {
    return null;
  }

  private static Gatherer<Integer, ?, List<Integer>> definirGathererGroupeDeuxParDeux() {
    return null;
  }

  private static Gatherer<Integer, ?, Integer> definirGathererParallelePlusGrandElement() {
    return null;
  }

  private static void utiliserIsRejecting() {
    Gatherer<Integer, ?, Integer> rejectionCheckingGatherer = Gatherer.of(
      // Integrator: processes each element
      (_, element, downstream) -> {
        // Print whether downstream is rejecting
        System.out.println("Processing element " + element + ": Is downstream rejecting? " + downstream.isRejecting());
        // Push the element downstream
        downstream.push(element);
        return true;
      }
    );

    class State {
      int count;
    }
    Gatherer<Integer, ?, Integer> limiter = Gatherer.ofSequential(
      State::new,
      (state, element, downstream) -> {
        downstream.push(element);
        return state.count++ < 5;
      }
    );

    // Apply the gatherer with a limit of 2 elements
    var s =
    NOMBRES.stream()
      .gather(rejectionCheckingGatherer)
      .gather(limiter)
      .map(String::valueOf);

    System.out.println(s);
    s.toList();
  }

  private static Gatherer<Integer, ?, String> definirGathererFold() {
    return null;
  }

  private static Gatherer<Integer, ?, String> definirGathererScan() {
    return null;
  }

  private static Gatherer<Integer, ?, List<Integer>> definirGathererWindowFixed() {
    return null;
  }

  private static Gatherer<Integer, ?, List<Integer>> definirGathererWindowSliding() {
    return null;
  }

  private static Gatherer<Integer, ?, Integer> definirGathererMapConcurrent() {
    return null;
  }

  private static Gatherer<Integer, ?, List<Integer>> composerGatherers() {
    return null;
  }

  // Fonctions utilitaires - ne pas modifier
  private static void afficherNombresSequentiel(String titre, Gatherer<Integer, ?, ?> gatherer) {
    afficherNombres(titre, NOMBRES.stream(), gatherer);
  }

  private static void afficherNombresParallele(String titre, Gatherer<Integer, ?, ?> gatherer) {
    afficherNombres(titre, NOMBRES.parallelStream(), gatherer);
  }

  private static <T> void afficherNombres(String titre, Stream<T> stream, Gatherer<T, ?, ?> gatherer) {
    if (gatherer != null) {
      System.out.println(titre);
      List<?> list = stream.gather(gatherer).toList();
      System.out.println(NOMBRES + " -> " + list);
    }
    System.out.println();
  }
}