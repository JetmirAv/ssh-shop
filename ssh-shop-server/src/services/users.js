const CustomError = require("../errors/CustomError");
const { User, Sequelize } = require("../models/sequelize");
const Op = Sequelize.Op;

/**
 *
 * @param {Number} user_id
 * @returns User
 */
const GetUser = async (user_id) => {
  try {
    const user = await User.findByPk(user_id);
    if (!user) throw new CustomError("Not found!", {}, 401);
    return user;
  } catch (err) {
    throw err;
  }
};

/**
 *
 * @param {User} data
 * @returns User
 */
const CreateUser = async (data) => {
  try {
    data.role_id = 2;
    const user = new User({ ...data });

    let userWithEmail = await User.findOne({ where: { email: { [Op.like]: data.email } } });
    if (userWithEmail) throw new CustomError("Email already in use", {}, 401);

    await user.validate();
    await user.save();
    return user;
  } catch (err) {
    throw err;
  }
};

/**
 *
 * @param {Number} user_id
 * @param {User} data
 * @returns User
 */
const UpdateUser = async (user_id, data) => {
  try {
    const user = await GetUser(user_id);

    if (user.email !== data.email) {
      let userWithEmail = await User.findOne({ where: { email: { [Op.like]: data.email } } });
      if (userWithEmail) throw new CustomError("Email already in use", {}, 401);
    }

    const instance = new User({ ...user.dataValues, ...data });

    await instance.validate();
    await user.update({ ...instance.dataValues, password: null, role_id: null });
    return user;
  } catch (err) {
    console.log({ err });

    throw err;
  }
};

/**
 *
 * @param {User} user_id
 * @returns Boolean
 */
const DeleteUser = async (user_id) => {
  try {
    const user = await GetUser(user_id);
    await user.destroy();
    return true;
  } catch (err) {
    throw err;
  }
};

module.exports = {
  GetUser,
  CreateUser,
  UpdateUser,
  DeleteUser,
};
