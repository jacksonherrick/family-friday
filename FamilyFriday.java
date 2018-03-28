import java.util.HashSet;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.util.Collections;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.StringBuilder;

public class FamilyFriday{

	public static HashSet<String> employees;

	public static void main(String [] args){
		employees = new HashSet<String>();
		System.out.println("Welcome to Family Friday. Please add names below. Please type \"done\" when you are finished adding names");
		Scanner kb = new Scanner(System.in);
		String s = kb.nextLine();
		while(!s.equals("done")){
			employees.add(s);
			s = kb.nextLine();
		}

		System.out.println("You entered " + employees.size() + " names. The names you entered were: ");
		for(String name : employees){
			System.out.println(name);
		}

		System.out.println("Please enter your desired group size");
		int groupSize = kb.nextInt();
		kb.close();

		System.out.println("Thank you. These are your groups:");

		getGroups(groupSize, employees);


	}

	public static void test(){
	}

	public static String createString(){
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String word = "";
		for(int i=0; i<10; i++){
			word = word + chars.charAt((int)(Math.random()*chars.length()));
		}

		return word;
	}

	public static HashSet<String> [] getGroups(int groupSize){
		employees = getEmployees();
		String [] employeeArr = hashSetToArray(employees);
		// Randomizes the order of employeeArr to get random groups when we late iterate through
		shuffle(employeeArr);
		/*
		System.out.println("Employees:");
		for(int i=0; i<employeeArr.length; i++){
			System.out.println(employeeArr[i]);
		}
		System.out.println("TEST DONE");
		*/

		/*
		 * Since groups have to be at least 3, there should be numEmployees / 3 groups. 
		 * There will be numEmployees % 3 groups of 4 and the rest will be groups of 3.
		 */
		HashSet[] groups;

		if(employees.size() < 2*groupSize){
			groups = new HashSet[2];
			for(int i=0; i<groups.length; i++){
				groups[i] = new HashSet();
			}

			for(int i=0; i<employeeArr.length; i++){
				groups[i%2].add(employeeArr[i]);
			}
		}
		else{
			int numGroups = employees.size()/groupSize;
			groups = new HashSet[numGroups];
			for(int i=0; i<groups.length; i++){
				groups[i] = new HashSet();
			}
		
			// Creates groups of size groupSize or groupSize +1 
			for(int i=0; i<employeeArr.length; i++){
				/*
				System.out.println("GROUPS: " + numGroups);
				Boolean b = (groups[i%numGroups] == null);
				System.out.println("GROUPS IS NULL: "+ b);
				*/
				groups[i%numGroups].add(employeeArr[i]);
			}
		}
		

		print(groups);
		return groups;
	}

	public static HashSet<String> [] getGroups(int groupSize, HashSet<String> _employees){
		String [] employeeArr = hashSetToArray(_employees);
		// Randomizes the order of employeeArr to get random groups when we late iterate through
		shuffle(employeeArr);
		/*
		System.out.println("Employees:");
		for(int i=0; i<employeeArr.length; i++){
			System.out.println(employeeArr[i]);
		}
		System.out.println("TEST DONE");
		*/

		/*
		 * Since groups have to be at least 3, there should be numEmployees / 3 groups. 
		 * There will be numEmployees % 3 groups of 4 and the rest will be groups of 3.
		 */
		HashSet[] groups;

		if(employees.size() < 2*groupSize){
			groups = new HashSet[2];
			for(int i=0; i<groups.length; i++){
				groups[i] = new HashSet();
			}

			for(int i=0; i<employeeArr.length; i++){
				groups[i%2].add(employeeArr[i]);
			}
		}
		else{
			int numGroups = employees.size()/groupSize;
			groups = new HashSet[numGroups];
			for(int i=0; i<groups.length; i++){
				groups[i] = new HashSet();
			}
		
			// Creates groups of size groupSize or groupSize +1 
			for(int i=0; i<employeeArr.length; i++){
				/*
				System.out.println("GROUPS: " + numGroups);
				Boolean b = (groups[i%numGroups] == null);
				System.out.println("GROUPS IS NULL: "+ b);
				*/
				groups[i%numGroups].add(employeeArr[i]);
			}
		}
		

		print(groups);
		return groups;
	}

	// Reads the file of employee names and returns it as a HashSet<String> 
	public static HashSet<String> getEmployees(){
		employees = new HashSet<String>();
		try{
			Scanner s = new Scanner(new File("names.txt"));
			while(s.hasNext()){
				employees.add(s.nextLine());
			}
		} catch (FileNotFoundException e){
			System.out.println("File not found");
		}
		

		return employees;
	}

	// Adds a new employee to both the text file names.txt and the HashSet employees
	public static void addEmployee(String name){
		try{
			FileWriter f = new FileWriter("names.txt", true);
			employees.add(name);
			f.append(name + "\n");
			f.close();
		}
		catch (IOException e){
			System.out.println("Error writing to file");
		}
		
	}

	// Converts the HashSet into an array so that it can be shuffled
	public static String [] hashSetToArray(HashSet<String> employees){
		String [] arr = new String [employees.size()];
		int i=0;
		for(String s : employees){
			arr[i] = s;
			i++;
		}
		return arr;
	} 

	public static void testHashSetToArray(){

	}
	
	// Shuffles the array of names
	public static String [] shuffle(String [] arr){
		int swapIndex;
		String temp;
		for(int i = arr.length-1; i>=1; i--){
			swapIndex = (int)(Math.random()*(i));
			//System.out.println("Swapping: " + swapIndex + " and " + i);

			temp = arr[i];
			arr[i] = arr[swapIndex];
			arr[swapIndex] = temp;
			/*
			for(int k=0; k<arr.length; k++){
				System.out.println(arr[k]);
			}
			*/
		}
		return arr;
	}

	public static void testShuffle(String [] arr){
		String [] before = new String [arr.length];
		for(int i=0; i<before.length; i++){
			before[i] = arr[i];
		}

		String [] after = shuffle(arr);

		// Is it the same as before?
		for(int i=0; i<after.length; i++){
			if(after[i] != before[i]){
				System.out.println("They are not the same");
				break;
			}
		}

		// Did it add any extra elements?
		HashSet<String> set = new HashSet<String>();
		for(int i=0; i<before.length; i++){
			set.add(before[i]);
		}

		for(int i=0; i<after.length; i++){
			if(!set.contains(after[i])){
				System.out.println("Extra element added");
				break;
			}
		}

		// Did it remove any elements?
		HashSet<String> set2 = new HashSet<String>();
		for(int i=0; i<after.length; i++){
			set2.add(after[i]);
		}

		for(int i=0; i<before.length; i++){
			if(!set2.contains(before[i])){
				System.out.println("Element removed");
				break;
			}
		}

		System.out.println("Tests over");

	}

	// Prints out the groups
	public static void print(HashSet<String> [] groups){
		for(int i=0; i<groups.length; i++){
			System.out.println("Group " + i + ":");
			for(String s: groups[i]){
				System.out.println(s);
			}
			System.out.println();
		}
	}

}