package ds;

public class D2_String {

	public static void main(String[] args) {

		// ---------- Immutable String ----------
		String s = "Hello";
		s = s + " World"; // Creates a new String object
		System.out.println("String (Immutable): " + s);

		// String basic operations
		System.out.println("Length: " + s.length());
		System.out.println("Char at index 1: " + s.charAt(1));
		System.out.println("Substring (2 to 5): " + s.substring(2, 5));
		System.out.println("Replace 'l' with 'z': " + s.replace('l', 'z'));
		System.out.println("Contains 'World': " + s.contains("World"));
		System.out.println("Index of 'o': " + s.indexOf('o'));
		System.out.println("Equals 'Hello World': " + s.equals("Hello World"));
		System.out.println("Equals Ignore Case: " + s.equalsIgnoreCase("hello world"));
		System.out.println("Starts with 'He': " + s.startsWith("He"));
		System.out.println("Ends with 'ld': " + s.endsWith("ld"));

		// ---------- Mutable StringBuilder (Not Thread-safe) ----------
		StringBuilder sb = new StringBuilder("Hello");
		sb.append(" World");
		System.out.println("\nStringBuilder: " + sb);

		// Insertion, Deletion, Replacement
		sb.insert(5, " Java"); // After "Hello"
		System.out.println("After Insert: " + sb);

		sb.delete(5, 10); // Remove " Java"
		System.out.println("After Delete: " + sb);

		sb.replace(0, 5, "Hi"); // Replace "Hello" with "Hi"
		System.out.println("After Replace: " + sb);

		sb.reverse();
		System.out.println("Reversed Builder: " + sb);
		sb.reverse(); // Undo reverse

		// ---------- Mutable StringBuffer (Thread-safe) ----------
		StringBuffer sbf = new StringBuffer("Hello");
		sbf.append(" World");
		System.out.println("\nStringBuffer: " + sbf);

		// More StringBuffer operations
		sbf.insert(5, " Java");
		System.out.println("Buffer Insert: " + sbf);

		sbf.delete(5, 10);
		System.out.println("Buffer Delete: " + sbf);

		sbf.replace(0, 5, "Hi");
		System.out.println("Buffer Replace: " + sbf);

		sbf.reverse();
		System.out.println("Reversed Buffer: " + sbf);
		sbf.reverse();

		// ---------- Convert between types ----------
		String converted = sb.toString();
		System.out.println("\nConverted to String: " + converted);

		// Real use-case: capitalize first letter of each word
		String sentence = "java is awesome";
		String[] words = sentence.split(" ");
		StringBuilder cap = new StringBuilder();
		for (String word : words) {
			cap.append(Character.toUpperCase(word.charAt(0)))
			   .append(word.substring(1))
			   .append(" ");
		}
		System.out.println("Capitalized: " + cap.toString().trim());
	}
}
