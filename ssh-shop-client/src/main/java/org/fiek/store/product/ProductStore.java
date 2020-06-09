package org.fiek.store.product;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;

import org.fiek.models.Category;
import org.fiek.models.Product;

import eu.lestard.fluxfx.Store;

public class ProductStore extends Store {
    private Category category;
    public Product product;
    public ArrayList<Category> categories = new ArrayList<>();


    public ArrayList<Category> getCategory() {
        return categories;
    }
    public void addProductAction(String product) {
        final Product actionProduct = new GsonBuilder().create().fromJson(product, Product.class);

    }

    public void GetCategoryAction(String[] category) {
        for (String strCategory : category) {
            final Category actionCategory = new GsonBuilder().create().fromJson(strCategory, Category.class);
            this.category = actionCategory;
            categories.add(this.category);
        }
    }
}