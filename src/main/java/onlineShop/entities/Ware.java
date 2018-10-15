package onlineShop.entities;

import java.time.LocalDate;
import java.util.Arrays;

public class Ware {

    private String name;

    private String manufacturer;

    private LocalDate date;//LocalDate

    private String category;

    private String subCategory;

    private double price;

    public Ware(String name, String manufacturer, LocalDate date, Double price, String category, String subCategory) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.date = date;
        this.price = price;
        this.category = category;
        this.subCategory = subCategory;
    }

    public Ware() {
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public boolean hasCharacteristics(Ware ware) {
        return this.name.equals(ware.getName()) ||
                this.manufacturer.equals(ware.getManufacturer()) ||
                this.category.equals(ware.getCategory()) ||
                this.subCategory.equals(ware.getSubCategory());
    }

    public boolean hasCharacteristics(Ware ware, String... args) {
        final boolean[] res = {false};

        Arrays.stream(args).forEach(arg -> {
            switch (arg) {
                case "name":
                    res[0] = res[0] || this.name.equals(ware.getName());
                    break;
                case "manufacturer":
                    res[0] = res[0] || this.manufacturer.equals(ware.getManufacturer());
                    break;
                case "category":
                    res[0] = res[0] || this.category.equals(ware.getCategory());
                    break;

                default:
                    res[0] = res[0] || this.subCategory.equals(ware.getSubCategory());
                    break;
            }
        });

        return res[0];
    }
}

/*
* methodA {
*
* try {
*   methodB();
* } catch{
* }
*
* }
* */

/*
* Nokia 1100 -> Category - Mobile, subCategory - Mobile phones, Manufacturer - Nokia
* HeadPhones -> Category - Mobile, subCategory - Acceso, Manufacturer - Nokia
*
*
* */
