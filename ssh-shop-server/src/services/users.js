let { User } = require("../models/sequelize");

/**
 *
 * @param {Number} user_id
 * @returns User
 */
const GetUser = async (user_id) => {
  try {
    const user = await User.findByPk(user_id);
    if (!user) throw new Error({ error: "Not Found" });
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
    console.log({ data });

    const user = new User({ ...data });
    console.log({ user });

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
    const instance = new User({ ...user.dataValues, ...data });
    await instance.validate();
    await user.update({ ...instance.dataValues });
    return user;
  } catch (err) {
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
