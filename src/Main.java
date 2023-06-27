import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        KuchenkaMikrofalowa kuchenka = new KuchenkaMikrofalowa();

        BazaDanych bazaDanych = new BazaDanych("BazaDoProjektuZPO.txt");
        List<Potrawa> potrawy = bazaDanych.wczytajPotrawyZPliku();

        Scanner scanner = new Scanner(System.in);
        int wybor;
        boolean wyborPoprawny = false;

        System.out.println("Dostępne potrawy:");
        for (int i = 0; i < potrawy.size(); i++) {
            System.out.println((i + 1) + ". " + potrawy.get(i).getNazwa());
        }

        while (!wyborPoprawny) {
            System.out.print("Wybierz numer potrawy: ");
            if (scanner.hasNextInt()) {
                wybor = scanner.nextInt();
                if (wybor >= 1 && wybor <= potrawy.size()) {
                    Potrawa wybranaPotrawa = potrawy.get(wybor - 1);
                    wyborPoprawny = true;

                    System.out.println("Wybrano potrawę: " + wybranaPotrawa.getNazwa());

                    System.out.print("Podaj temperaturę: ");
                    int temperatura = scanner.nextInt();

                    System.out.print("Podaj czas: ");
                    int czas = scanner.nextInt();

                    if (temperatura < wybranaPotrawa.getTemperaturaMin() || temperatura > wybranaPotrawa.getTemperaturaMax()
                            || czas < wybranaPotrawa.getCzasMin() || czas > wybranaPotrawa.getCzasMax()) {
                        System.out.println("Potrawa spaliła się");
                    } else {
                        System.out.println("Potrawa gotowa");
                    }

                    kuchenka.gotujPotrawe(wybranaPotrawa);
                } else {
                    System.out.println("Nieprawidłowy numer potrawy");
                }
            } else {
                System.out.println("Nieprawidłowe dane wejściowe");
                scanner.next(); // Pomija nieprawidłowe dane wejściowe
            }
        }

        scanner.close();
    }
}