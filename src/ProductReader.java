import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductReader {
    public static void main(String[] args) {
        ArrayList<String> Products = new ArrayList<String>();
        Scanner input = new Scanner(System.in);

        while (true) {
            String ID = SafeInput.getNonZeroLenString(input, "What is the ID?");
            String name = SafeInput.getNonZeroLenString(input, "What is the name?");
            String description = SafeInput.getNonZeroLenString(input, "What is the description?");
            double cost = SafeInput.getDouble(input, "What is the cost?");

            String data = ID + ", " + name + ", " + description + ", " + cost;
            Products.add(data);

            boolean askUser = SafeInput.getYNConfirm(input, "Would you like to add another product to the file?");
            if (askUser == false) {
                break;
            }
        }

        Path fileName = Path.of("ProductTestData.txt");

        try {
            BufferedWriter writer = Files.newBufferedWriter(fileName, StandardOpenOption.CREATE);

            for (int i = 0; i < Products.size(); i++) {
                writer.write(Products.get(i));
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

