const UserServices = require("../services/users");

const func = async (req, res, next) => {
  await res.send("respond with a resource");
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 */
const create = async (req, res) => {
  console.log({ ...req.body });
  try {
    const user = await UserServices.CreateUser(req.body);

    return res.status(200).json({ user });
  } catch (err) {
    throw err;
  }
};

module.exports = {
  create,
  func,
};
