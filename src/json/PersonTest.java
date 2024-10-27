package json;

import java.util.ArrayList;
import java.util.List;

public class PersonTest {

	public static void main(String[] args) {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Alex", 23));
		persons.add(new Person("Dima", 20));
		persons.add(new Person("Danya", 19));
		
		String fileName = "persons.json";
		Person.saveToJson(fileName, persons);
		System.out.println("JSON data has written to the file: " + fileName);

		List<Person> deserializedPersons = Person.loadFromJSON(fileName);
		System.out.println("JSON data from the file:");
		deserializedPersons.forEach(System.out::println);
	}
}