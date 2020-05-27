const {
  CreateChannel,
  UpdateChannel,
  GetExistingChannel,
  FindAndCountChannels,
} = require("../services/channel");

/**
 *
 * @param {Request} req
 * @param {Response} res
 */
const findAll = async (req, res, next) => {
  try {
    const channels = await FindAndCountChannels(req.user);
    return res.status(200).json({ channels });
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
    const channel = await CreateChannel({
      user_id: req.user.id,
      product_id: parseInt(req.params.product_id),
    });
    return res.status(200).json({ channel });
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
    const channel = await UpdateChannel(
      req.params.user_id,
      req.params.product_id,
      req.body
    );
    return res.status(200).json({ channel });
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
    return res.status(200).json({
      channel: await GetExistingChannel(
        req.params.user_id,
        req.params.product_id
      ),
    });
  } catch (err) {
    next(err);
  }
};

module.exports = {
  create,
  findAll,
  update,
  get,
};
