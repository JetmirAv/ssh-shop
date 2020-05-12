package org.fiek.models;

import java.util.ArrayList;

public class Variant {
    final String tableName = "variants";

    public int ID;
    public int product_id;
    public String name;
    public String description;

    public Product product;
    public ArrayList<VariantOptions> options;

    public Variant(int ID,
                   int product_id,
                   String name,
                   String description,
                   Product product,
                   ArrayList<VariantOptions> options) {
        this.ID = ID;
        this.product_id = product_id;
        this.name = name;
        this.description = description;
        this.product = product;
        this.options = options;
    }

    public Variant(){
         this( -1, -1, "", "", null, new ArrayList<VariantOptions>() );
    }

    public String getTableName() {
        return tableName;
    }

    public int getID() {
        return ID;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ArrayList<VariantOptions> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<VariantOptions> options) {
        this.options = options;
    }


}
