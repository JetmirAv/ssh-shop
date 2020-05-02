const CustomError = require("../errors/CustomError");
const { Channel, Sequelize } = require("../models/sequelize");
const Op = Sequelize.Op;

/**
 *
//  * @param {Number} channel_id
//  * @returns Channel
 */
const GetChannel = async (channel_id) => {
  try {
    const channel = await Channel.findByPk(channel_id);
    if (!channel) throw new CustomError("Not found!", {}, 401);
    return channel;
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Number} product_id
//  * @param {Channel} data
//  * @returns Channel
 */
const CreateChannel = async (data) => {
  try {
    const channel = new Channel({ ...data });
    let user_id = await Channel.findOrCreate({
      where: { user_id: { [Op.like]: data.user_id }, 
               product_id: { [Op.like]: data.product_id } },
    });
    if (user_id && product_id)
      throw new CustomError("This user has already been in channel", {}, 401);
    await channel.validate();
    await channel.save();
    return channel;
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Number} channel_id
//  * @param {Number} product_id
//  * @param {Channel} data
//  * @returns Channel
 */
const UpdateChannel = async (channel_id, user_id, product_id, data) => {
  try {
    const channel = await Channel.findOne({
      where: {
        id: channel_id,
        user_id: user_id,
        product_id: product_id,
      },
    });
    if (channel) {
      const instance = new Channel({ ...channel.dataValues, ...data });
      await instance.validate();
      await channel.update({
        ...instance.dataValues,
      });
      return channel;
    } else {
      throw new CustomError("No combination between user and channel", {}, 404);
    }
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Channel} channel_id
//  * @returns Boolean
//  */


module.exports = {
  CreateChannel,
  UpdateChannel,
  GetChannel,
};
