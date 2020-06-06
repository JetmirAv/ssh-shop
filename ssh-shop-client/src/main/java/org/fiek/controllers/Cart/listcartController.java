/**
 * Sample Skeleton for 'list-cartController.fxml' Controller Class
 */

package org.fiek.controllers.Cart;

import java.net.URL;

import java.util.ResourceBundle;

import eu.lestard.fluxfx.View;

import javafx.fxml.FXML;

import javafx.scene.input.MouseEvent;

import javafx.scene.layout.HBox;

import javafx.scene.layout.VBox;

import javafx.scene.text.Text;

import org.fiek.App;







public class listcartController implements View {

        @FXML // ResourceBundle that was given to the FXMLLoader
        private ResourceBundle resources;

        @FXML // URL location of the FXML file that was given to the FXMLLoader
        private URL location;

        @FXML
        private VBox cartList;


        public listcartController(Cart cart) {
              this.cart = cart;
        }

        @FXML
// This method is called by the FXMLLoader when initialization is complete
        void initialize() {
                assert cartList != null : "fx:id=\"cartList\" was not injected: check your FXML file 'list-cart.fxml'.";
                renderCarts(baseStore.getAuthStore());
                baseStore.getAuthStoreEventStream().subscribe(this::renderCarts);
                }

        private void renderCarts(AuthStore authStore) {
               ArrayList<Cart> carts = authStore.getCarts();
               System.out.println("size:" + carts.size());
               if (!carts.isEmpty()) {
                     cartList.getChildren().clear();
               for (Cart cart : carts) {
                  fxmlLoader = new FXMLLoader(App.class.getResource("views/cart/list-item-cart.fxml"));
                  fxmlLoader.setController(new listitemcartController(cart));
                  HBox hbox = null;
                  try {
                      hbox = fxmlLoader.load();
                  } catch (IOException e) {
                  e.printStackTrace();
                  }
               cartList.getChildren().add(hbox);
               }
            }
          }

        }