package util;

import java.io.*;
import java.util.List;

public class FileUtil {

    public static void writeBinaryFile(String filename, Object object) throws IOException {
        validateFileName(filename);
        validateObject(object);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(object);
        } catch (IOException e) {
            throw new IOException("Failed to write object to binary file: " + filename, e);
        }
    }

    public static void writeCSVFile(String filename, List<String[]> data) throws IOException {
        validateFileName(filename);
        validateData(data);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String[] row : data) {
                String line = String.join(",", row);
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IOException("Failed to write content to CSV file: " + filename, e);
        }
    }

    public static Object readFromBinaryFile(String filename) throws IOException, ClassNotFoundException {
        validateFileName(filename);

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Failed to read object from binary file: " + filename, e);
        }
    }

    private static void validateFileName(String filename) {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty.");
        }
    }

    private static void validateObject(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("Object cannot be null.");
        }
    }

    private static void validateData(List<String[]> data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data cannot be null or empty.");
        }
    }
}
