// package edu.curtin.addressbook;

import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and Jack Davis
 */

public class AddressBookApp 
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);

    private static Map<Integer, Option> options;
    
    public static void main(String[] args)
    {
        //Initialises the menu options
        initalizeOptions();
        
        String fileName;
        System.out.print("Enter address book filename: ");
        fileName = input.nextLine();
        
        try
        {
            AddressBook addressBook = readAddressBook(fileName);
            showMenu(addressBook);
        }
        catch(IOException e)
        {
            System.out.println("Could not read from " + fileName + ": " + e.getMessage());
        }
    }

    /**
     * Initalizes the menu options by adding a key, an int, and an option to 
     * 'options'.
     */

    public static void initalizeOptions()
    {
        options = new HashMap<>();
        options.put(1, new SearchByName());
        options.put(2, new SearchByEmail());
    }

    /**
     * Read the address book file, containing all the names and email addresses.
     *
     * @param fileName The name of the address book file.
     * @return A new AddressBook object containing all the information.
     * @throws IOException If the file cannot be read.
     */
    private static AddressBook readAddressBook(String fileName) throws IOException
    {
        AddressBook addressBook = new AddressBook();
        
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line = reader.readLine();
            while(line != null)
            {
                //Splits the parts into name and email1:email2:...:emailN
                String[] parts = line.split(":",2);
                
                if( 1 < parts.length)
                {
                    List<String> inEmails = 
                        new LinkedList<>(Arrays.asList(parts[1].split(":")));

                                          //name    ,emails
                    addressBook.addNewEntry(parts[0],inEmails);
                }
                
                line = reader.readLine();
            }
        }
        
        return addressBook;
    }
    
    /**
     * Show the main menu, offering the user options to (1) search entries by 
     * name, (2) search entries by email, or (3) quit.
     *
     * @param addressBook The AddressBook object to search.
     */

    private static void showMenu(AddressBook addressBook)
    {
        boolean done = false;
        while(!done)
        {
            printMenuOptions();
            System.out.println("Enter a number");

            try
            {
                int chosenOption = Integer.parseInt(input.nextLine());

                //if the user quits
                if(chosenOption == 0)
                {
                    done = true;
                }
                else
                {
                    Entry foundEntry = 
                        options.get(chosenOption).doOption(addressBook);
                    System.out.println("ENTRY:");
                    System.out.println("    " + foundEntry.toString());
                }

            }
            catch(NumberFormatException e)
            {
                System.out.println("Error: Please enter a digit");
            }
            catch (NoSuchElementException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void printMenuOptions()
    {
        //0 is hardcoded to quit
        System.out.println("0: Quit");

        for(Integer op: options.keySet())
        {
            System.out.printf("%d: %s\n",
                op,options.get(op).getOptionDesc());
        }
    }
}
