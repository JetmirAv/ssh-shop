const CustomError = require("../errors/CustomError");
const { WishList, Sequelize } = require("../models/sequelize");
//const { GetMyProducts, GetProduct } = require("../services/products");
//const { GetUser } = require("../services/users");
const Product = require("../models/mongo/products");

/**
 *
//  * @param {Number} user_id
//  * @returns product
 */
const getProducts = async (user_id) => {
    console.log(user_id);
    try {
      const wishlist = await WishList.findAll({
        where: {
          user_id: user_id
        },
      });
  
      let productIds =wishlist.map((rec) => rec.product_id);
  
      const products = await Product.find({ _id: { $in: productIds } });
  
  
      if (!products) throw new CustomError("Not found!", {}, 401);
      return products;
    } catch (err) {
      throw err;
    }
  };

//  const getproductIds = async (user_id) => {
//    try {
//      const product = await whishlist.findAll({
//        where: {
//          user_id: user_id,
//        },
//      });
//      if (!address) throw new CustomError("Not found!", {}, 401);
//      return address;
//    } catch (err) {
//      throw err;
//    }
//  };
  

  module.exports = {
    getProducts
  }