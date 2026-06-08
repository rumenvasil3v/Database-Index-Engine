package main;

import java.util.List;

public class DatabaseApp {
	public static void main(String[] args) {
		System.out.println("Database Index Engine");

		DatabaseIndexEngine dbIndex = new DatabaseIndexEngine();

		System.out.println("\nInserting records");
		dbIndex.insert(10, "{\"id\": 10, \"item\": \"Laptop\", \"status\": \"shipped\"}");
		dbIndex.insert(20, "{\"id\": 20, \"item\": \"Smartphone\", \"status\": \"processing\"}");
		dbIndex.insert(30, "{\"id\": 30, \"item\": \"Wireless Earbuds\", \"status\": \"delivered\"}");

		System.out.println("\n--- Rotation Architecture Verification ---");
		System.out.println("Expected Balanced Root Key: 20");
		System.out.println("Actual Database Root Key  : " + dbIndex.getRootKey());

		System.out.println("\n--- Point Query Lookup Test ---");
		String record = dbIndex.find(30);
		System.out.println("Querying ID 30 returned: " + (record != null ? record : "NOT FOUND"));

		dbIndex.insert(15, "{\"id\": 15, \"item\": \"Tablet\", \"status\": \"shipped\"}");
		dbIndex.insert(25, "{\"id\": 25, \"item\": \"Smartwatch\", \"status\": \"processing\"}");

		System.out.println("\n--- Executing Database Range Query Scan (IDs 12 to 28) ---");
		List<String> rangeResults = dbIndex.rangeSearch(12, 28);
		for (String row : rangeResults) {
			System.out.println(" -> Record Content: " + row);
		}
	}
}
