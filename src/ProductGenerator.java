import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductGenerator {
    public static void main(String[] args) {
        ArrayList<Product> Products = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        while (true) {
            String ID = SafeInput.getNonZeroLenString(input, "What is the ID?");
            String name = SafeInput.getNonZeroLenString(input, "What is the name?");
            String description = SafeInput.getNonZeroLenString(input, "What is the description?");
            double cost = SafeInput.getDouble(input, "What is the cost?");

            Product product = new Product(ID, name, description, cost);

            Products.add(product);

            boolean askUser = SafeInput.getYNConfirm(input, "Would you like to add another product to the file?");
            if (askUser == false) {
                break;
            }
        }

        Path fileName = Path.of("ProductTestData.txt");

        try {
            BufferedWriter writer = Files.newBufferedWriter(fileName, StandardOpenOption.CREATE);

            for (Product product : Products) {
                String csvRecord = product.toCSVDataRecord();
                writer.write(csvRecord);
                writer.newLine();
            }
            writer.close();
            System.out.println("Products saved to " + fileName);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

