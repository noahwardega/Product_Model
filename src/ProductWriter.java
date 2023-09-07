import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductWriter {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getAbsolutePath();
            List<String[]> records = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                System.out.println("File Contents:");
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(", ");

                    if (data.length == 4) {
                        records.add(data);
                    }
                }

                System.out.printf("%-10s %-15s %-30s %-30s%n", "ID#", "Name", "Description", "Cost");
                System.out.println("================================================================");

                for (String[] data : records) {
                    System.out.printf("%-10s %-15s %-30s %-30s%n", data[0], data[1], data[2], data[3]);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
