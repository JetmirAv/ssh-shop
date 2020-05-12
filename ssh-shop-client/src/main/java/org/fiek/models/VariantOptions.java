package org.fiek.models;

public class VariantOptions {
    final String tableName = "variant_options";
    public int ID;
    public int variant_id;
    public String name;
    public String description;

    public Variant variant;

    public VariantOptions(int ID, int variant_id, String name, String description) {
        this.ID = ID;
        this.variant_id = variant_id;
        this.name = name;
        this.description = description;
        this.variant = variant;
    }

    public VariantOptions() {
        this(-1, -1, "", "");
    }

    public String getTableName() {
        return tableName;
    }

    public int getID() {
        return ID;
    }

    public int getVariantId() {
        return variant_id;
    }

    public void setVariantId(int variant_id) {
        this.variant_id = variant_id;
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

    public Variant getVariant() {
        return variant;
    }

    public void setVariant(Variant variant) {
        this.variant = variant;
    }
}
