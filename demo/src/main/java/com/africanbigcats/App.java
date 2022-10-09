package com.africanbigcats;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Menu appMenu = new Menu();

        Character command = '_';
        LinkedList<Panthera> catList = new LinkedList<>(); 

        // loop until user quits
        while (command != 'q') {

            // print the menu
            appMenu.print();

            // get a command
            System.out.print("Enter a command: ");
            command = appMenu.getCommand();

            // execute a commmand
            appMenu.executeCommand(command, catList);

            // move the cats to new positions in africa
            appMenu.update(catList);

        }

    }
}
