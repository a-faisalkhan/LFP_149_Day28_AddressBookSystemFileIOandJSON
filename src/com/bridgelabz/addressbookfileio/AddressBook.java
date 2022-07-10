package com.bridgelabz.addressbookfileio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class AddressBook {
	private ArrayList<Contact> contactBook = new ArrayList<Contact>();
	public HashMap<String, ArrayList<Contact>> contactByCity = new HashMap<String, ArrayList<Contact>>();
	public HashMap<String, ArrayList<Contact>> contactByState = new HashMap<String, ArrayList<Contact>>();

	Scanner sc = new Scanner(System.in);
	private static int numberOfConatcts = 0;
	private String adressBookName;

	@Override
	public String toString() {
		return "AddressBook [adressBookName=" + adressBookName + "]";
	}

	public String getAdressBookName() {
		return adressBookName;
	}

	public void setAdressBookName(String adressBookName) {
		this.adressBookName = adressBookName;
	}

	public String nameString = this.adressBookName + ".txt";

	public void write() {
		AddressBookFileIO.writeData(contactBook, this.adressBookName + ".txt");
	}

	public List<String> read() {
		return AddressBookFileIO.readDataFromFile(this.adressBookName);
	}

	public void addContacts() {
		System.out.println("Enter Contact details:");

		Contact contact = details();
		boolean isDuplicate = contactBook.stream().anyMatch(contacts -> contact.equals(contact));
		if (isDuplicate) {
			System.out.println("Duplicate data entry. discarded");
		} else {
			contactBook.add(contact);
			if (contactByCity.get(contact.getCity()) == null)
				contactByCity.put(contact.getCity(), new ArrayList<>());
			contactByCity.get(contact.getCity()).add(contact);
			if (contactByState.get(contact.getState()) == null)
				contactByState.put(contact.getState(), new ArrayList<>());
			contactByState.get(contact.getState()).add(contact);
		}

	}

	public void edit() {
		System.out.println("Enter the name to edit contact details");
		String name = sc.next();
		System.out.println("Enter the choice to edit details");
		System.out.println("1. First Name\\n2. Last Name\\n3. City\\n4. State\\n5. Zip Code\\n6. Phone\\n7. Email");
		int choice = sc.nextInt();
		int index = 0;
		for (index = 0; index < numberOfConatcts; index++)
			if (name.equals(contactBook.get(index).getFirstName())) {
				System.out.println("Name exists, now enter the new details");

				break;
			} else {
				System.out.println("No contact found");
				return;
			}
		switch (choice) {
		case 1:
			System.out.println("Enter new First Name");
			String newFirstName = sc.next();
			contactBook.get(index).setFirstName(newFirstName);
			break;
		case 2:
			System.out.println("Enter new Last Name");
			String newLastName = sc.next();
			contactBook.get(index).setLastName(newLastName);
			break;
		case 3:
			System.out.println("Enter new City");
			String newCity = sc.next();
			contactBook.get(index).setCity(newCity);
			break;
		case 4:
			System.out.println("Enter new State");
			String newState = sc.next();
			contactBook.get(index).setState(newState);
			break;
		case 5:
			System.out.println("Enter new Zip code");
			int newZip = sc.nextInt();
			contactBook.get(index).setZip(newZip);
			break;
		case 6:
			System.out.println("Enter new Phone Number");
			String newPNumber = sc.next();
			contactBook.get(index).setPhoneNumber(newPNumber);
			break;
		case 7:
			System.out.println("Enter new Email");
			String newEmail = sc.next();
			contactBook.get(index).setEmail(newEmail);
			break;
		}

	}

	public void delete() {
		int index;
		System.out.println("Enter the name of the contact to delete");
		String name = sc.next();
		for (index = 0; index < numberOfConatcts; index++)
			if (contactBook.get(index).getFirstName().equals(name)) {
				break;
			}
		while (!contactBook.get(index + 1).equals(null)) {
			contactBook.set(index, contactBook.get(index + 1));
			index++;
		}
		contactBook.set(index, null);
		System.out.println("Deleted details of : " + name);
	}

	public void display() {
		Contact person;
		System.out.println("Enter name to see details");
		String name = sc.next();

		for (int i = 0; i < contactBook.size(); i++) {
			if (contactBook.get(i).getFirstName().equals(name)) {
				person = contactBook.get(i);
				System.out.println(person);
			}
		}
	}

	private static Contact details() {
		Scanner sc = new Scanner(System.in);
		Contact contact1 = new Contact();

		System.out.println("Enter first name:");
		contact1.setFirstName(sc.next());
		System.out.println("Enter second name:");
		contact1.setLastName(sc.next());
		System.out.println("Enter address:");
		contact1.setAddress(sc.next());
		System.out.println("Enter city:");
		contact1.setCity(sc.next());
		System.out.println("Enter state:");
		contact1.setState(sc.next());
		System.out.println("Enter pin code:");
		contact1.setZip(sc.nextInt());
		System.out.println("Enter phone number:");
		contact1.setPhoneNumber(sc.next());
		System.out.println("Enter email:");
		contact1.setEmail(sc.next());
		return contact1;
	}

	public void searchByCity(String city, String firstName) {
		Predicate<Contact> searchContact = (contact -> contact.getCity().equals(city)
				&& contact.getFirstName().equals(firstName));
		contactBook.stream().filter(searchContact).forEach(person -> output(person));
	}

	public void searchByState(String state, String firstName) {
		Predicate<Contact> searchContact = (contact -> contact.getState().equals(state)
				&& contact.getFirstName().equals(firstName));
		contactBook.stream().filter(searchContact).forEach(person -> output(person));
	}

	public void contactsInCity(String city) {
		ArrayList<Contact> list = contactByCity.get(city);
		list.stream().forEach(contact -> output(contact));
	}

	public void contactsInState(String State) {
		ArrayList<Contact> list = contactByState.get(State);
		list.stream().forEach(contact -> output(contact));
	}

	public void sortByFirstName() {
		contactBook.stream().sorted((contact1, contact2) -> contact1.getFirstName().compareTo(contact2.getFirstName()))
				.forEach(System.out::println); // Its method reference
	}

	public void sortByZip() {
		contactBook.stream().sorted((contact1, contact2) -> contact1.getZip() - contact2.getZip())
				.forEach(System.out::println);
	}

	public void sortByCity() {
		contactBook.stream().sorted((contact1, contact2) -> contact1.getCity().compareTo(contact2.getCity()))
				.forEach(System.out::println);
	}

	public void sortByState() {
		contactBook.stream().sorted((contact1, contact2) -> contact1.getState().compareTo(contact2.getState()))
				.forEach(System.out::println);
	}

	private static void output(Contact contact) {
		System.out.println("firstName : " + contact.getFirstName());
		System.out.println("SecondName : " + contact.getLastName());
		System.out.println("Address : " + contact.getAddress());
		System.out.println("City : " + contact.getCity());
		System.out.println("State : " + contact.getState());
		System.out.println("Pin code : " + contact.getZip());
		System.out.println("Phone nmber : " + contact.getPhoneNumber());
		System.out.println("email : " + contact.getEmail());
	}
}