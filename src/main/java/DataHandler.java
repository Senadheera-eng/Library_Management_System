import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataHandler {
    private static final String BOOKS_FILE = "books.json";
    private static final String MEMBERS_FILE = "members.json";
    private static final String LENDING_FILE = "lendingrecord.json";

    public static Map<Integer, Book> loadBooks() {
        return loadData(BOOKS_FILE, new TypeToken<Map<Integer, Book>>() {}.getType());
    }

    public static Map<Integer, Member> loadMembers() {
        return loadData(MEMBERS_FILE, new TypeToken<Map<Integer, Member>>() {}.getType());
    }

    public static Map<Integer, LendingRecord> loadLendingRecords() {
        return loadData(LENDING_FILE, new TypeToken<Map<Integer, LendingRecord>>() {}.getType());
    }

    public static void saveBooks(Map<Integer, Book> books) {
        saveData(BOOKS_FILE, books);
    }

    public static void saveMembers(Map<Integer, Member> members) {
        saveData(MEMBERS_FILE, members);
    }

    public static void saveLendingRecords(Map<Integer, LendingRecord> lendingRecords) {
        saveData(LENDING_FILE, lendingRecords);
    }

    private static <T> Map<Integer, T> loadData(String fileName, java.lang.reflect.Type type) {
        try (Reader reader = new FileReader(fileName)) {
            return new Gson().fromJson(reader, type);
        } catch (IOException e) {
            System.err.println("Error loading file: " + fileName + " - " + e.getMessage());
            return new HashMap<>(); // Explicitly initialize a new HashMap with correct types
        }
    }

    private static void saveData(String fileName, Object data) {
        try (Writer writer = new FileWriter(fileName)) {
            new Gson().toJson(data, writer);
        } catch (IOException e) {
            System.err.println("Error saving file: " + fileName + " - " + e.getMessage());
        }
    }
}
