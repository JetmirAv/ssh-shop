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
    console.log("Brenda try-it!");
    let user = req.user;
    var oldPassword = req.body.oldPassword;
    var newPassword = req.body.newPassword;
    var confirmPassword = req.body.confirmPassword;
    console.log("Old password:" + oldPassword);
    console.log("New password:" + newPassword);
    console.log("Confirm password:" + confirmPassword);
    console.log({ user });
    let hash = user.password;
    console.log("Hash : " + hash);
    bcrypt.compare(oldPassword, hash, function (err, user) {
      if (res) {
        // Passwords match
        if (newPassword == confirmPassword) {
          console.log("Mir o!");
          // user.update(
          //   { password: req.body.newPassword },
          //   { fields: ["password"] }
          // );
          console.log("u ba update!");
        }
      }
    });
  } catch (err) {
    console.log("Jasht try-it!");
    next(err);
  }
};

module.exports = {
  me,
  login,
  changePassword,
  register,
};
