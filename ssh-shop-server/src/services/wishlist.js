const CustomError = require("../errors/CustomError");
const { Wishlist, Sequelize } = require("../models/sequelize");

const Op = Sequelize.Op;

const GetWishlist = async (user_id) => {
    try {
      let wishlist  = await Wishlist.findAll({
          include: [{
              model: wishlist,
              where: { user_id:user_id },
              right: true         //will create an inner join

          }]
        
      });
      return wishlist;
    } catch (err) {
      throw err;
    }
};
module.exports = {
    GetWishlist,
  };
  
