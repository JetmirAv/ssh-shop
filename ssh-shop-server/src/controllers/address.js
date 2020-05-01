const {
  CreateAddress,
  UpdateAddress,
  DeleteAddress,
  GetAddress,
} = require("../services/address");
const func = async (req, res) => {
  await res.send("respond with a resource");
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 */
const create = async (req, res, next) => {
  try {
    const address = await CreateAddress(req.body);
    return res.status(200).json({ address });
  } catch (err) {
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
    const address = await UpdateAddress(
      req.params.address_id,
      req.params.user_id,
      req.body
    );
    return res.status(200).json({ address });
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
    const response = await DeleteAddress(req.params.address_id);
    return res.status(200).json(response);
  } catch (err) {
    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 * @param {*} next
 */
const get = async (req, res, next) => {
  try {
    return res
      .status(200)
      .json({ user: await GetAddress(req.params.address_id) });
  } catch (err) {
    next(err);
  }
};

module.exports = {
  create,
  update,
  drop,
  get,
  func,
};
