const {
  CreateAddress,
  UpdateAddress,
  DeleteAddress,
  GetAddress,
  GetAllAddresses,
} = require("../services/address");

/**
 *
 * @param {Request} req
 * @param {Response} res
 */

const getAddresses = async (req, res, next) => {
  try {
    const address = await GetAllAddresses(req.params.user_id);
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
const create = async (req, res, next) => {
  try {
    //assign user_id to the user_id got by params
    req.body.user_id = parseInt(req.params.user_id);
    console.log({ ...req.body });

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
  console.log("para service ne update!");
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
      .json({ address: await GetAddress(req.params.address_id) });
  } catch (err) {
    next(err);
  }
};

module.exports = {
  create,
  update,
  drop,
  get,
  getAddresses,
};
