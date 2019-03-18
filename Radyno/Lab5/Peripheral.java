package package1;

import java.util.*;

public class Peripheral {

    public final static List<String> brandsList = new ArrayList<>();

    private static final String[] brands = new String[]{"",
            "Apacer", "Seagate", "Smasung", "Toshiba", //storages
            "A4Tech", "ACME", "Apple", "BenQ", "Canyon", "Defender", "Dell", "Dialog", "Genesis", "HP", "HyperX",
            "Lenovo", "Logitech", "Microsoft", "MSI", "Razer", "SmartBuy", //input
            "Acer", "AOC", "ASUS", "BenQ", "Dell", "HP", "Lenovo", "LG", "NEC", "Philips", "Samsung" //output
    };

    public static void createBrandsList() {
        for (String brand : brands) {
            if (!brandsList.contains(brand)) {
                brandsList.add(brand);
            }
        }
        Collections.sort(brandsList);
    }

    public static String getRandomBrand() {
        if (brandsList.isEmpty()) {
            createBrandsList();
        }
        return Peripheral.brandsList.get((int) (Math.random() * Peripheral.brandsList.size()));
    }

    private final String name;
    private final String brandName;
    private final int cost;

    public static boolean addBrand(final String brandName) throws NullPointerException {
        if (brandName == null) {
            throw new NullPointerException("bad brandName");
        }
        if (!brandsList.contains(brandName)) {
            brandsList.add(brandName);
            Collections.sort(brandsList);
            return true;
        } else {
            return false;
        }
    }

    public Peripheral(final String name, final String brandName, final int cost) throws IllegalArgumentException, NullPointerException {
        if (brandsList.isEmpty()) {
            createBrandsList();
        }
        if (!brandsList.contains(brandName)) {
            throw new IllegalArgumentException("no such brandName in brandsList");
        }
        if (name == null) {
            throw new NullPointerException("name is null");
        }
        this.name = name;
        this.brandName = brandName;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getBrandName() {
        return brandName;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Peripheral{" +
                "name='" + name + '\'' +
                ", brandName='" + brandName + '\'' +
                ", cost=" + cost +
                '}';
    }
}
