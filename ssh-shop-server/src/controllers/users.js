const {
  CreateUser,
  UpdateUser,
  DeleteUser,
  GetUser,
} = require("../services/users");

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
    const user = await CreateUser(req.body);

    return res.status(200).json({ user });
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
  console.log("Po hin te update!");
  try {
    const user = await UpdateUser(req.params.user_id, req.body);

    return res.status(200).json({ user });
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
    const response = await DeleteUser(req.params.user_id);

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
    return res.status(200).json({ user: await GetUser(req.params.user_id) });
  } catch (err) {
    next(err);
  }
};

module.exports = {
  get,
  create,
  update,
  drop,
  func,
};
