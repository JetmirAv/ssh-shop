const bcrypt = require("bcrypt");
const { User, Sequelize } = require("../models/sequelize");
const CustomError = require("../errors/CustomError");
const { generateJWT } = require("../util/generateJWT");
const {
  CreateUser,
  UpdateUser,
  DeleteUser,
  GetUser,
} = require("../services/users");

/**
 *
 * @param {Request} req
 * @param {Response} res
 */
const me = async (req, res, next) => {
  try {
    return res.status(200).json({ user: req.user });
  } catch (err) {
    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 */
const login = async (req, res, next) => {
  try {
    console.log({ ...req.body });

    let user = await User.findOne({
      where: { email: { [Sequelize.Op.like]: req.body.email } },
    });
    if (!user) throw new CustomError("Wrong creditials", {}, 401);

    await user.validatePassword(req.body.password);

    return res.status(200).json({ user: user, token: generateJWT(user.id) });
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
const register = async (req, res, next) => {
  try {
    console.log({ ...req.body });

    const user = await CreateUser(req.body);

    return res.status(200).json({ user: user, token: generateJWT(user.id) });
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
const changePassword = async (req, res, next) => {
  try {
    let user = req.user;
    var newPassword = req.body.newPassword;
    var confirmPassword = req.body.confirmPassword;
    console.log({ user });
    await user.validatePassword(req.body.oldPassword);
    if (newPassword == confirmPassword) {
      user.update({ password: confirmPassword }, { fields: ["password"] });
      return res.status(200).json({ password: newPassword });
    }
  } catch (err) {
    console.log(err);
    next(err);
  }
};

module.exports = {
  me,
  login,
  changePassword,
  register,
};
