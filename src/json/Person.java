package json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Person {
	private String name;
	private int age;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public JSONObject toJsonObject() {
		JSONObject obj = new JSONObject();
		obj.put("name", this.name);
		obj.put("age", this.age);
		return obj;
	}
	
	public static void saveToJson(String fileName, List<Person> persons) {
		JSONArray arr = new JSONArray();
		
		for (Person person : persons) {
			arr.add(person.toJsonObject());
		}
		
		try(FileWriter file = new FileWriter(fileName)){
			file.write(arr.toJSONString());
			file.flush();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static List<Person> loadFromJSON(String fileName) {
		List<Person> persons = new ArrayList<>();
		JSONParser parser = new JSONParser();
		
		try(FileReader reader = new FileReader(fileName)){
			JSONArray jsonArr = (JSONArray) parser.parse(reader);
			
			for (Object obj : jsonArr) {
				JSONObject jsonObj = (JSONObject) obj;
				String name = (String) jsonObj.get("name");
				long age = (long) jsonObj.get("age");
				persons.add(new Person(name, (int) age));
			}
		}
		catch(IOException | ParseException e) {
			e.printStackTrace();
		}
		return persons;
	}
	
	@Override
	public String toString() {
		return "Person { name = '" + name + "', age = " + age + " }"; 
	}
}