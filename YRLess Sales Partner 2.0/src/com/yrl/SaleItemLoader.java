package com.yrl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SaleItemLoader {

    public static final String FILE_PATH = "data/SaleItems.csv";

    public static void loadItems(Map<String, Person> personsMap, Map<String, Purchase> itemsMap,
                                  Map<String, Store> storesMap, Map<String, Sale> salesMap) {

        String line = null;

        try (Scanner s = new Scanner(new File(FILE_PATH))) {
            line = s.nextLine();
            while (s.hasNextLine()) {
                line = s.nextLine();
                if (!line.trim().isEmpty()) {
                    String[] tokens = line.split(",");
                    
                    Sale currentSale = salesMap.get(tokens[0]);
                    Store currentStore = currentSale.getStore();
                    currentStore.addSale(currentSale);

                    
                    Item currentItem = itemsMap.get(tokens[1]);
                    
                    if (currentItem.getType() == 'P') {
						if(tokens.length > 3) {
							currentItem = new Lease(currentItem.getCode(), currentItem.getitemName(), currentItem.getitemBaseCost(), currentItem.getType(), tokens[2], tokens[3]);
						}else {
							currentItem = new Purchase(currentItem.getCode(), currentItem.getitemName(), currentItem.getitemBaseCost(), currentItem.getType());
						}
                    } else if (currentItem.getType() == 'S') {
                        currentItem = new Service(currentItem.getCode(), currentItem.getitemName(), currentItem.getitemBaseCost(), currentItem.getType(), Double.parseDouble(tokens[2]), tokens[3]);
                    } else if (currentItem.getType() == 'D') {
                        currentItem = new DataPlan(currentItem.getCode(), currentItem.getitemName(), currentItem.getitemBaseCost(), currentItem.getType(), Double.parseDouble(tokens[2]));
                    } else if (currentItem.getType() == 'V') {
                        currentItem = new VoicePlan(currentItem.getCode(), currentItem.getitemName(), currentItem.getitemBaseCost(), currentItem.getType(), tokens[2], Integer.parseInt(tokens[3]));
                    }
                    currentSale.addItem(currentItem);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Encountered Error on line " + line, e);
        }
    }
}
