import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NotesManager {

    private static final String FILE_NAME = "notes.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        System.out.println("Welcome to the Simple Notes Manager!");

        while (!exit) {
            displayMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        writeNote();
                        break;
                    case 2:
                        readNotes();
                        break;
                    case 3:
                        exit = true;
                        System.out.println("Exiting. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input to prevent an infinite loop
            }
        }
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Write a new note");
        System.out.println("2. Read all notes");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void writeNote() {
        System.out.print("Enter your note (end with a blank line): \n");
        // Read the entire note, line by line, until a blank line is entered
        StringBuilder note = new StringBuilder();
        String line;
        while ((line = scanner.nextLine()).length() > 0) {
            note.append(line).append(System.lineSeparator());
        }

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // 'true' for append mode
            writer.write(note.toString());
            System.out.println("Note saved successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the note: " + e.getMessage());
        }
    }

    private static void readNotes() {
        System.out.println("\n--- Your Notes ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            if (reader.ready()) {
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } else {
                System.out.println("No notes found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the notes: " + e.getMessage());
        }
    }
}
