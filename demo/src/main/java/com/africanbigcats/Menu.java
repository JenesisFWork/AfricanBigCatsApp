package com.africanbigcats;

import java.util.*;

/*
 * Menu class for the african big cat app
 */
public class Menu {

    // attributes
    private Scanner input;

    // constructor
    public Menu() {

        // initialize attributes
        this.input = new Scanner(System.in);

    }

    // prints the menu
    public void print() {

        printLine();
        System.out.println("African Big Cats App");
        printLine();

        /*
            TIP:
            In this area of the code, the additional commands need to be created and added to the menu.
        */

        printCommand('c',"[C]reates a big cat");
        printCommand('d',"[D]eletes a big cat"); // the delete command
        printCommand('f', "[F]inds a big cat"); // the find command
        printCommand('l',"[L]ists all big Cats");
        printCommand('q',"[Q]uits");

        printLine();

    }

    private static void printLine() {
        System.out.println("----------------------------------------------------------" );
    }

    private static void printCommand(Character command, String desc) {
        System.out.printf("%s\t%s\n", command, desc);
    }

    // get first character from input
    public Character getCommand() {

        Character command = '_';

        String rawInput = input.nextLine();
        
        if (rawInput.length() > 0) {
            rawInput = rawInput.toLowerCase();
            command = rawInput.charAt(0);
        }

        return command;

    }

    // command switch
    public Boolean executeCommand(Character command, LinkedList<Panthera> catList) {

        Boolean success = true;

        /*
            TIP:
            In this area of the code, the additional commands need to be created and added.
        */

        switch(command) {

            case 'c':
                executeCreate(catList);
                break;

            case 'd':
                executeDelete(catList); //delete code switch-case
                break;

            case 'f':
                executeFind(catList); //find code switch-case
                break;

            case 'l':
                executeList(catList);
                break;

            case 'q':
                executeQuit();
                break;

            default:
                System.out.println("ERROR: Unknown commmand");
                success = false;
          }

        return success;
    }

    // update the position of all the cats
    public void update(LinkedList<Panthera> catList) {

        // update by moving all the cats
        for (Panthera cat: catList) {
            cat.move();
        }

    }

    // quit the app
    public void executeQuit() {

        // close the scannner
        input.close();

        System.out.println();
        printLine();
        System.out.println("Thank you for using the African Big Cats App!");
        printLine();
        System.out.println();

    }

    public Panthera getNewCat(String name) {
        
        /*
            TIP:
            In this area of the code, students need to get input from the user for the type of cat 
            and create the correct type.

            Currently, the code always create a Tiger.  But, support for Lions and Jaguars
            also needs to be added.

        */

        System.out.println();
        System.out.print("Enter 1 for tiger, 2 for lion, 3 for jaguar: ");
        String catName = input.nextLine();        
        System.out.println();
        // NOTE: Use .equals() to compare strings, not == as it's for booleans
        if (catName.equals("1")) { // 1 for tiger
            Panthera cat = new Tiger(name);
            return cat;
        } else if (catName.equals("2")) { // 2 for lion
            Panthera cat = new Lion(name);
            return cat;
        } else if (catName.equals("3")) { // 3 for jaguar
            Panthera cat = new Jaguar(name);
            return cat;
        } else {
            Panthera result = new Panthera(name);
            return result;
        }

    }

    // create a cat, if it's unique
    public void executeCreate(LinkedList<Panthera> catList) {

        // get the name
        System.out.println();
        System.out.print("Enter a name for the big cat to create: ");
        String name = input.nextLine();        
        System.out.println();

        /*
            TIP:
            In this area of the code, students would need to add in checking if the cat name
            already exists in order to prevent duplicates
        */
        /*
         * online tutor helped out with this
         */
        Panthera cat = getNewCat(name);
        boolean found = false;
        for (int i = 0; i < catList.size(); i++) { 
            if(catList.get(i).name().equals(cat.name()) && catList.get(i).species().equals(cat.species())){
                found = true;
            }
        } 
        if(found){
           System.out.println("Error! Duplicate Name! Try Another Name!");
        }
        else{
           catList.add(cat); 
        }

    }

    // deletes a big cat
    // got help from a tutor
    public void executeDelete(LinkedList<Panthera> catList){
        int listSize = catList.size();
        if (listSize == 0){
            System.out.println("Fail to delete, there is no cat in the list to delete");
            System.out.println();
        }
        else{
            // get the name
            System.out.println();
            System.out.print("Delete cat name: ");
            String name = input.nextLine();
            System.out.println();

            boolean wasRemoved = false;
            for (int i = 0; i < listSize; i++){
                if (catList.get(i).name().equals(name)){
                    catList.remove(i);
                    wasRemoved = true;
                    System.out.println(String.format("Delete sucessful, %s has been removed", name));
                    System.out.println();
                    break;
                }
            }
            if (wasRemoved == false){
                System.out.println(String.format("Fail to delete, there is no cat name %s to delete", name));
                System.out.println();
            }
        }
    }

    // finds a big cat
    //also got help from a tutor
    public void executeFind(LinkedList<Panthera> catList) {
        System.out.println();
        System.out.print("Enter a name for the big cat to find: ");
      String name = input.nextLine();
        System.out.println();

        int listSize = catList.size();
        boolean catFound = false;
        if(listSize > 0){
            for (int i = 0; i < listSize; i++){
                if(catList.get(i).name().contains(name)){
                    System.out.println(catList.get(i));
                    catFound = true;
                }
            }
            if (catFound == false){
                System.out.println(String.format("Keyword %s is not related to any cat name", name));
                System.out.println();
            }
        }
        else {
            System.out.println("Fail to find cat, there is no cat in the list to find");
            System.out.println();
        }
    }

    // list all big cats 
    public void executeList(LinkedList<Panthera> catList) {

        System.out.println();
        printLine();
        System.out.println("African Big Cat List");
        printLine();

        Panthera cat;
        if (catList.size() > 0) {
            for (Integer i = 0; i < catList.size(); i++) {
                cat = catList.get(i);
                System.out.println(cat);
            }
        } else {
            System.out.println("There are no African Big Cats. :(");
        }

        System.out.println();

    }

    /*
        TIP:
        Additional methods and functionality need to be added to this class.
    */


}
