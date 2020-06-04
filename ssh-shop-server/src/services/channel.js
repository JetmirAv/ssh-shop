const CustomError = require("../errors/CustomError");
const { Channel, Product, Sequelize } = require("../models/sequelize");
const { FindAndCountProducts } = require("../services/products");

//  * @param {Number} product_id
//  * @param {Channel} data
//  * @returns Channel
const CreateChannel = async (data) => {
  try {
    const existChannel = await GetExistingChannel(
      data.user_id,
      data.product_id
    );
    if (existChannel) return existChannel;
    const product = await Product.findByPk(data.product_id);
    if (!product) throw Error("Not found");
    const channel = new Channel({ ...data, name: product.name });
    console.log("pas krijimit te objektit:", channel);
    await channel.validate();
    await channel.save();
    return channel;
  } catch (err) {
    console.log({ err });

    throw err;
  }
};

const GetExistingChannel = async (user_id, product_id) => {
  try {
    let channel = await Channel.findOne({
      where: { user_id, product_id },
    });
    return channel;
  } catch (err) {
    throw err;
  }
};

//  * @param {Number} product_id
//  * @param {Channel} data
//  * @returns Channel
const UpdateChannel = async (user_id, product_id, data) => {
  try {
    const channel = await Channel.findOne({
      where: {
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

//  * @param {User} user
//  * @returns Channel
const FindAndCountChannels = async (user) => {
  try {
    let products = await FindAndCountProducts(user);
    let porductIds = products.rows.map((p) => p.id);

    let channels = await Channel.findAndCountAll({
      where: {
        [Sequelize.Op.or]: [
          { user_id: user.id },
          { product_id: { [Sequelize.Op.in]: porductIds } },
        ],
      },
      order: [["updated_at", "desc"]],
    });

    return channels;
  } catch (err) {
    throw err;
  }
};

module.exports = {
  CreateChannel,
  UpdateChannel,
  GetExistingChannel,
  FindAndCountChannels,
};
