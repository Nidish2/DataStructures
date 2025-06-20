package ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class D1_Arrays {

	private int[] arr;
	private int size = 0;

	public D1_Arrays(int capacity) {
		// TODO Auto-generated constructor stub
		arr = new int[capacity];
	}

	public void insert(int value) {
		if (size < arr.length) {
			arr[size++] = value;
		} else {
			System.out.println("Array is full. Can't add more.");
		}
	}

	public void read() {
		if (size == 0) {
			System.out.println("Array is empty.");
			return;
		}
		for (int i = 0; i < size; i++) {
			System.out.println("Index " + i + " = " + arr[i]);
		}
	}

	public void update(int index, int value) {
		if (index >= 0 && index < size) {
			arr[index] = value;
		} else {
			System.out.println("Invalid index for update.");
		}
	}

	public void delete(int index) {
		if (index >= 0 && index < size) {
			for (int i = index; i < size - 1; i++) {
				arr[i] = arr[i + 1];
			}
			arr[size - 1] = 0;
			size--;
		} else {
			System.out.println("Invalid index for deletion.");
		}
	}

	public static void main(String[] args) {
		// Initialize array with a capacity of 5
		D1_Arrays arrayOps = new D1_Arrays(5);

		System.out.println("👉 Creating elements...");
		arrayOps.insert(10);
		arrayOps.insert(20);
		arrayOps.insert(30);
		arrayOps.insert(40);
		arrayOps.insert(50);
		arrayOps.insert(60); // Should show array full
		System.out.println();

		System.out.println("📖 Reading array...");
		arrayOps.read();
		System.out.println();

		System.out.println("✏️ Updating index 2 with value 300...");
		arrayOps.update(2, 300);
		arrayOps.read();
		System.out.println();

		System.out.println("🗑️ Deleting element at index 1...");
		arrayOps.delete(1);
		arrayOps.read();
		System.out.println();

		System.out.println("🔁 Attempt to delete at invalid index 10...");
		arrayOps.delete(10); // Invalid deletion
		System.out.println();

		System.out.println("✅ Final state of the array:");

		// Swap using bitwise XOR
		int a = 10;
		int b = 20;
		System.out.println("\nInitial values: a = " + a + ", b = " + b);
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;

		System.out.println("After swapping with XOR a = " + a + ", b = " + b);

		// Swap using a temporary variable
		System.out.println("\nInitial values: a = " + a + ", b = " + b);
		int temp = a;
		a = b;
		b = temp;

		System.out.println("After swapping with temp: a = " + a + ", b = " + b);

		// Swap using multiplication and division (not recommended for large numbers)
		System.out.println("\nInitial values: a = " + a + ", b = " + b);
		a = 10;
		b = 20;
		a = a * b; // a = 200
		b = a / b; // b = 10
		a = a / b; // a = 20

		System.out.println("After swapping with multiplication/division: a = " + a + ", b = " + b);

		// Swap using addition and subtraction (not recommended for large numbers)
		System.out.println("\nInitial values: a = " + a + ", b = " + b);
		a = 10;
		b = 20;
		a = a + b; // a = 30
		b = a - b; // b = 10
		a = a - b; // a = 20

		System.out.println("After swapping with addition/subtraction: a = " + a + ", b = " + b);

		// Dynamic Array
		ArrayList<Integer> dynamicArray = new ArrayList<>();

		// Create
		System.out.println("\n👉 Adding elements...");
		dynamicArray.add(10);
		dynamicArray.add(20);
		dynamicArray.add(30);
		dynamicArray.add(40);
		System.out.println("Current ArrayList: " + dynamicArray);

		// Read
		System.out.println("\n📖 Reading elements:");
		for (int i = 0; i < dynamicArray.size(); i++) {
			System.out.println("Index " + i + " = " + dynamicArray.get(i));
		}

		// Update
		System.out.println("\n✏️ Updating index 2 to 300...");
		if (dynamicArray.size() > 2) {
			dynamicArray.set(2, 300);
		}
		System.out.println("After update: " + dynamicArray);

		// Delete
		System.out.println("\n🗑️ Deleting element at index 1...");
		if (dynamicArray.size() > 1) {
			dynamicArray.remove(1);
		}
		System.out.println("After deletion: " + dynamicArray);

		// Check if empty
		System.out.println("\n❓ Is ArrayList empty? " + dynamicArray.isEmpty());

		// Read final state
		System.out.println("\n✅ Final ArrayList:");
		for (int i = 0; i < dynamicArray.size(); i++) {
			System.out.println("Index " + i + " = " + dynamicArray.get(i));
		}

		//
		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(20, 30, 40, 50));
		System.out.println("\nInitial List: " + list);
		Collections.swap(list, 0, 2); // swaps 10 and 30

		System.out.println(list);

	}
}
