package me.BvngeeCord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static ArrayList<Character> aminoAcidSequence;
    public static ArrayList<List<String>> proteinToDNACodon;

    public static void main(String[] args) {

        aminoAcidSequence = getSequence();

        proteinToDNACodon = getListFromFile();

        System.out.println("The amino acid sequence:\n" + aminoAcidSequence);

        System.out.println("Would you like a corresponding DNA Template strand, DNA Coding strand, or an RNA strand?\n" +
                "Respond with 'template', 'coding', or 'rna'.");

        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine().trim().toLowerCase()){

            case "template":
                System.out.println("One possible corresponding DNA Template Strand:");
                System.out.println("3'-");
                aminoAcidSequence.forEach((n) -> {
                    String coding = getCodonFromAminoAcid(n).replaceAll("U", "T");
                    System.out.println(coding
                            .replaceAll("T", "T1")
                            .replaceAll("A", "T")
                            .replaceAll("T1", "A")
                            .replaceAll("C", "C1")
                            .replaceAll("G", "C")
                            .replaceAll("C1", "G")
                    );
                });
                System.out.println("5'-");
                break;

            case "coding":
                System.out.println("One possible corresponding DNA Coding Strand:");
                System.out.println("5'-");
                aminoAcidSequence.forEach((n) -> System.out.println(getCodonFromAminoAcid(n).replaceAll("U", "T")));
                System.out.println("3'-");
                break;

            case "rna":
                System.out.println("One possible corresponding RNA Strand:");
                System.out.println("5' AAAAAAA -");
                aminoAcidSequence.forEach((n) -> System.out.println(getCodonFromAminoAcid(n)));
                System.out.println("3' cap -");
                break;
        }

    }

    public static ArrayList<Character> getSequence(){

        System.out.println("Input a sequence of Amino Acids (single letters):");

        Scanner scanner = new Scanner(System.in);

        ArrayList<Character> list = new ArrayList<>(
                scanner
                        .nextLine()
                        .replace(" ", "")
                        .chars()
                        .mapToObj(e -> (char) e)
                        .collect(
                                Collectors.toList()
                        )
        );
        return list;
    }

    public static ArrayList<List<String>> getListFromFile(){
        ArrayList<List<String>> list = new ArrayList<>();

        try{
            File file = new File("C:\\Users\\Jack\\DNAProject\\src\\me\\BvngeeCord\\ProteinToDNACodon.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                list.add((Arrays.asList(scanner.nextLine().trim().split(","))));
            }

            scanner.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return list;
    }

    public static String getCodonFromAminoAcid(Character letter){

        String codon = "";

        for(int i=0;i<proteinToDNACodon.size();i++){
            if (proteinToDNACodon.get(i).get(0).equals(letter.toString().toUpperCase())){
                int rng = 1+(int)(Math.random()*(proteinToDNACodon.get(i).size()-1));
                codon = proteinToDNACodon.get(i).get(rng);
                break;
            }

            codon = "Unknown Amino Acid!";

        }

        return codon;

    }
}
