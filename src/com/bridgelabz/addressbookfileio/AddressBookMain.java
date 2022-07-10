package com.bridgelabz.addressbookfileio;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AddressBookMain {
	private static List<AddressBook> addressBooks = new LinkedList<AddressBook>();
	private static String[] addressBookName = new String[10];
	private static int numOfBooks = 0;
	public static AddressBookFileIO addressBookFileIO = new AddressBookFileIO();

	private boolean checkName(String name) {
		for (int i = 0; i < addressBooks.size(); i++) {
			if (addressBookName[i].equals(name))
				return true;
		}
		return false;
	}

	private static void addressMenu(AddressBook addressBook) {
		Scanner sc = new Scanner(System.in);
		int option = 0;
		boolean exit = true;
		while (exit) {
			System.out.println("Select option 1: Add user  2: Edit existing user "
					+ "3: Display all users 4: Delete contact 5: Search user by city "
					+ "6: Search user by state 7: View by city 8: View by state "
					+ "9: Sort by name 10: Sort by zip 11: Sort by city " + "12: Sort by state " + "13: Write to file "
					+ "14: Read from file ");
			option = sc.nextInt();
			switch (option) {
			case 1:
				addressBook.addContacts();
				break;
			case 2:
				System.out.println("Enter the first name to edit");
				addressBook.edit();
				break;
			case 3:
				System.out.println("Display");
				addressBook.display();
				break;
			case 4:
				System.out.println("Enter name");
				addressBook.delete();
				break;
			case 5:
				System.out.println("Enter the name of the city");
				String cityName = sc.next();
				System.out.println("Enter the first name to search for city");
				String firstName = sc.next();

				addressBook.searchByCity(cityName, firstName);
			case 6:
				System.out.println("Enter the name of the city");
				String stateName = sc.next();
				System.out.println("Enter the first name to search for city");
				String firstName1 = sc.next();
				addressBook.searchByState(stateName, firstName1);
				break;
			case 7:
				System.out.println("Enter the city name");
				String city = sc.next();
				addressBook.contactsInCity(city);
				break;
			case 8:
				System.out.println("Enter the state name");
				String state = sc.next();
				addressBook.contactsInState(state);
				break;
			case 9:
				addressBook.sortByFirstName();
				break;
			case 10:
				addressBook.sortByZip();
				break;
			case 11:
				addressBook.sortByCity();
				break;
			case 12:
				addressBook.sortByState();
				break;
			case 13:
				addressBook.write();
				break;
			case 14:
				List<String> listaddressBook = addressBook.read();
				break;

			default:
				exit = false;

			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		System.out.println("--------Welcome to address book program----------");
		Scanner sc = new Scanner(System.in);
		AddressBook currentBook;
		int choice = 0;
		boolean exit1 = true;
		while (exit1) {
			System.out.println("Select option 1: Add address Book 2: Open Address Book 3: Exit");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter the address book name");
				String name = sc.next();
				currentBook = new AddressBook();
				currentBook.setAdressBookName(name);
				addressBooks.add(currentBook);

				addressBookName[numOfBooks] = name;
				numOfBooks++;
				break;
			case 2:
				System.out.println("The Address books available are :");
				for (int i = 0; i < numOfBooks; i++) {
					System.out.println(addressBookName[i]);
				}
				System.out.println("Enter the address book name for operations");
				String bookName = sc.next();
				int i = 0;
				for (i = 0; i < numOfBooks; i++) {
					if (addressBookName[i].equals(bookName))
						break;
				}

				if (i == numOfBooks) {
					System.out.println("Name Not Found");
					break;
				}
				currentBook = addressBooks.get(i);
				addressMenu(currentBook);
				break;
			default:
				exit1 = false;
			}
		}

		sc.close();
	}
}