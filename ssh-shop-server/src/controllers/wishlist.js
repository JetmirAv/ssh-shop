const {
    getProducts,
    getIsd

} = require("../services/wishlist");

const getAll = async (req, res, next) => {
    try {

      
      const product = await getProducts(
        req.params.user_id
      );
      
      //({ carts: await GetAllCarts(req.params.user_id) });
      return res.status(200).json({ product });
    } catch (err) {
      next(err);
    }
  };


module.exports = {
    getAll,
  };
  