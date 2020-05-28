const CustomError = require("../errors/CustomError");
const { Wishlist, Sequelize } = require("../models/sequelize");

const Op = Sequelize.Op;

const GetWishlist = async (user_id) => {
  try {
    const wishlist = await Wishlist.findAll({
      where: {
        user_id: user_id,
      },
    });
    if (!wishlist) throw new CustomError("Not found!", {}, 401);
    return wishlist;
  } catch (err) {
    throw err;
  }
};
module.exports = {
    GetWishlist,
  };
  
