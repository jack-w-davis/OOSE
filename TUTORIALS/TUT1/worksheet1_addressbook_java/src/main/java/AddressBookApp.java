// package edu.curtin.addressbook;

import java.io.*;
import java.util.*;

/**
 * A simple address book application.
 * @author Dave and ...
 */

public class AddressBookApp 
{
    /** Used to obtain user input. */
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        String fileName;
        String entryName;
        
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
                String[] parts = line.split(":");
                
                if( 1 < parts.length)
                {
                    String[] inEmails = Arrays.copyOfRange(parts, 1, parts.length);
                    addressBook.addNewEntry(parts[0],inEmails);
                }
                else
                {
                    //TODO: Throw exception here
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
            int option;
            System.out.println("(1) Search by name, (2) Search by email, (3) Quit");
            
            try
            {
                switch(Integer.parseInt(input.nextLine()))
                {
                    case 1:

                        SearchByName(addressBook);

                        break;
                    case 2:
                    {
                        System.out.print("Enter email address: ");
                        String email = input.nextLine();
                        
                        Entry e = addressBook.getEntryFromEmail(email);
                        
                        System.out.println(e.toString());
                        
                        // FIXME: Insert your code here to find an entry by email and display it.
                        break;
                    }
                    case 3:
                        done = true;
                        break;
                        
                    default:
                        System.out.println("Enter a valid number");
                        break;
                }
            }
            catch(NumberFormatException e)
            {
                // The user entered something non-numerical.
                System.out.println("Enter a number");
            }
        }
    }

    public static void SearchByName(AddressBook adbook)
    {
        System.out.print("Enter name: ");
        String name = null;
        
        try
        {
            name = input.nextLine();
            Entry e = adbook.getEntryFromName(name);
            System.out.println(e.toString());
        }
        catch (NoSuchElementException e) 
        {
            System.out.println( "Name '" + name + "' not found!");
        }
    }
}
