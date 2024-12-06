package util;

import java.io.*;

/**
 * Utility class to handle file read/write operations for text and binary files.
 */
public class FileUtil {

    /**
     * Ghi đối tượng vào file nhị phân
     *
     * @param filename tên file
     * @param object đối tượng cần ghi
     * @throws IOException nếu có lỗi trong quá trình ghi
     */
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

    /**
     * Ghi thông tin vào file văn bản
     *
     * @param filename tên file
     * @param content nội dung cần ghi
     * @throws IOException nếu có lỗi trong quá trình ghi
     */
    public static void writeTextFile(String filename, String content) throws IOException {
        if (filename == null || filename.isEmpty()) {
            throw new IllegalArgumentException("Filename cannot be null or empty.");
        }
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null.");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            throw new IOException("Failed to write content to text file: " + filename, e);
        }
    }

    /**
     * Đọc đối tượng từ file nhị phân
     *
     * @param filename tên file
     * @return đối tượng đọc được từ file
     * @throws IOException nếu có lỗi trong quá trình đọc
     * @throws ClassNotFoundException nếu không tìm thấy lớp của đối tượng
     */
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
