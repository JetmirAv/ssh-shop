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
      return res.status(200).json({
        wishlist: await GetWishlist(
          req.params.user_id,
          
        ),
      });
    } catch (err) {
      next(err);
    }
  };
  
  module.exports = {
    get,
  };
  
  