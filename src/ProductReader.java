import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductReader {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            String fileName = selectedFile.getAbsolutePath();
            List<Product> Products = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                System.out.println("File Contents:");
                String line;

                System.out.printf("%-10s %-15s %-30s %-30s%n", "ID#", "Name", "Description", "Cost");
                System.out.println("================================================================");

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(", ");

                    if (data.length == 4) {
                        String ID = data[0];
                        String name = data[1];
                        String description = data[2];
                        double cost = Double.valueOf(data[3]);

                        Product products = new Product(ID, name, description, cost);

                        Products.add(products);
                    }
                }

                // Display
                for (Product product : Products) {
                    System.out.printf("%-10s %-15s %-30s %-30s%n", product.getID(), product.getName(), product.getDescription(), product.getCost());
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
