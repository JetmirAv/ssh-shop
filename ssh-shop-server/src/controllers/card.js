const {
  CreateCard,
  UpdateCard,
  DeleteCard,
  GetCard,
} = require("../services/card");
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
    const card = await CreateCard(req.body);
    return res.status(200).json({ card });
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
    const card = await UpdateCard(
      req.params.card_id,
      req.params.user_id,
      req.body
    );
    return res.status(200).json({ card });
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
    const response = await DeleteCard(req.params.card_id);
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
    return res.status(200).json({ user: await GetCard(req.params.card_id) });
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
