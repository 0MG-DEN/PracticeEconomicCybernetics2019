package package1;

import com.sun.istack.internal.NotNull;

import java.util.*;

public class Peripheral {

    public final static List<String> BRANDS_LIST = new ArrayList<>();

    private static final String[] BRANDS = new String[]{"",
            "Apacer", "Seagate", "Smasung", "Toshiba", //storages
            "A4Tech", "ACME", "Apple", "BenQ", "Canyon", "Defender", "Dell", "Dialog", "Genesis", "HP", "HyperX",
            "Lenovo", "Logitech", "Microsoft", "MSI", "Razer", "SmartBuy", //input
            "Acer", "AOC", "ASUS", "BenQ", "Dell", "HP", "Lenovo", "LG", "NEC", "Philips", "Samsung" //output
    };

    private final String name;
    private final String brandName;
    private final int cost;

    public static void createBrandsList() {
        for (final String brand : BRANDS) {
            if (!BRANDS_LIST.contains(brand)) {
                BRANDS_LIST.add(brand);
            }
        }
        Collections.sort(BRANDS_LIST);
    }

    public static String getRandomBrand() {
        if (BRANDS_LIST.isEmpty()) {
            createBrandsList();
        }
        return Peripheral.BRANDS_LIST.get((int) (Math.random() * Peripheral.BRANDS_LIST.size()));
    }

    public static boolean addBrand(final String brandName) {
        if (BRANDS_LIST.contains(brandName)) {
            return false;
        } else {
            BRANDS_LIST.add(brandName);
            Collections.sort(BRANDS_LIST);
            return true;
        }
    }

    public Peripheral(@NotNull final String name, final String brandName, final int cost) throws IllegalArgumentException {
        if (BRANDS_LIST.isEmpty()) {
            createBrandsList();
        }
        if (!BRANDS_LIST.contains(brandName)) {
            throw new IllegalArgumentException("no such brandName in BRANDS_LIST");
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
