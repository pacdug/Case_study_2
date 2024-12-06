package util;

import java.io.*;
import java.util.List;

public class FileUtil {

    // Ghi object thành file nhị phân
    public static void writeBinaryFile(String filename, Object object) throws IOException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty.");
        }
        if (object == null) {
            throw new IllegalArgumentException("Object cannot be null.");
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(object);
        } catch (IOException e) {
            throw new IOException("Failed to write object to binary file: " + filename, e);
        }
    }

    // Ghi danh sách các dòng thành file CSV
    public static void writeCSVFile(String filename, List<String[]> data) throws IOException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty.");
        }
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Data cannot be null or empty.");
        }

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

    // Đọc object từ file nhị phân
    public static Object readFromBinaryFile(String filename) throws IOException, ClassNotFoundException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty.");
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Failed to read object from binary file: " + filename, e);
        }
    }
}
