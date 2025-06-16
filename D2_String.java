package ds;

public class D2_String {

	public static void main(String[] args) {
		
		// ---------- String ----------
		String s = "Hello";
		s = s + " World"; // creates new object
		System.out.println("String: " + s);

		// ---------- StringBuilder ----------
		StringBuilder sb = new StringBuilder("Hello");
		sb.append(" World");
		System.out.println("StringBuilder: " + sb);

		// ---------- StringBuffer ----------
		StringBuffer sbf = new StringBuffer("Hello");
		sbf.append(" World");
		System.out.println("StringBuffer: " + sbf);

		// --- More common operations ---
		System.out.println("Char at 1 (String): " + s.charAt(1));
		System.out.println("Substring (2 to 5): " + s.substring(2, 5));
		System.out.println("Replace l->z: " + s.replace('l', 'z'));

		// Reverse using StringBuilder
		sb.reverse();
		System.out.println("Reversed (Builder): " + sb);

		// Insert, Delete, Replace in Builder/Buffer
		sb.insert(0, "Start-");
		sb.delete(0, 6);
		sb.replace(0, 5, "Hi");
		System.out.println("Modified Builder: " + sb);
	}
}
