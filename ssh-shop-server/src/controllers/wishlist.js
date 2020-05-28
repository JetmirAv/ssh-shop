const {
    GetWishlist,
  } = require("../services/wishlist");

  
/**
 *
 * @param {Request} req
 * @param {Response} res
 * @param {*} next
 */


const get = async (req, res, next) => {
  try {
    const wishlist = await GetWishlist(req.params.user_id);
    return res.status(200).json({ wishlist });
  } catch (err) {
    next(err);
  }
};
    
  module.exports = {
    get,
  };
  
  