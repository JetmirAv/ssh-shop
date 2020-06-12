package org.fiek.store.product;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.fiek.models.Category;
import org.fiek.models.Message;
import org.fiek.models.Product;

import eu.lestard.fluxfx.Store;

public class ProductStore extends Store {
    private Category category;
    public Product product;
    public ArrayList<Category> categories = new ArrayList<>();
    public Product selectedProduct;

    public ArrayList<Product> productList = new ArrayList<>();

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public ArrayList<Category> getCategory() {
        return categories;
    }

    public void addProductAction(String product) {
        final Product actionProduct = new GsonBuilder().create().fromJson(product, Product.class);

    }

    public void setSelectedProduct(String product) {
        final Product actionProduct = new GsonBuilder().create().fromJson(product, Product.class);
        this.selectedProduct = actionProduct;

    }


    public void GetCategoryAction(String[] category) {
        for (String strCategory : category) {
            final Category actionCategory = new GsonBuilder().create().fromJson(strCategory, Category.class);
            this.category = actionCategory;
            categories.add(this.category);
        }
    }

    public void addProducts(String products) {
        Gson gson = new Gson();
        Product[] products1 = gson.fromJson(products, Product[].class);
        this.productList.addAll(Arrays.asList(products1));
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
}