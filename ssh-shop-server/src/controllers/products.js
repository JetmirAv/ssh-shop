const {
  CreateProduct,
  UpdateProduct,
  GetProduct,
  DeleteProduct,
  FindAndCountProducts,
} = require("../services/products");

const getAll = async (req, res) => {
  let result = await FindAndCountProducts(req.user);

  return res.status(200).json(result);
};

/**
 * @param {Request} req
 * @param {Response} res
 */
const create = async (req, res, next) => {
  try {
    req.body.user_id = req.user.id;
    const product = await CreateProduct(req.body);

    return res.status(200).json({ product });
  } catch (err) {
    console.log({ err });

    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 */

const update = async (req, res, next) => {
  try {
    const product = await UpdateProduct(
      req.params.product_id,
      req.user.id,
      req.body
    );
    return res.status(200).json({ product });
  } catch (err) {
    next(err);
  }
};

const get = async (req, res, next) => {
  try {
    return res
      .status(200)
      .json({ user: await GetProduct(req.params.product_id) });
  } catch (err) {
    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 */
const drop = async (req, res, next) => {
  try {
    const response = await DeleteProduct(req.params.product_id);
    return res.status(200).json(response);
  } catch (err) {
    next(err);
  }
};

module.exports = {
  get,
  create,
  update,
  drop,
  getAll,
};
