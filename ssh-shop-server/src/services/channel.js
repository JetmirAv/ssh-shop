const CustomError = require("../errors/CustomError");
const { Channel, Product, Sequelize } = require("../models/sequelize");
const { GetMyProducts, GetProduct } = require("../services/products");
const { GetUser } = require("../services/users");

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
    let products = await GetMyProducts(user);

    let productIds = products.map((p) => p.id);
    console.log({ productIds });

    let channels = await Channel.findAll({
      where: {
        [Sequelize.Op.or]: [
          { user_id: user.id },
          { product_id: { [Sequelize.Op.in]: productIds } },
        ],
      },
      order: [["updated_at", "desc"]],
    });

    let mongoProducts = [];

    channels.map(async (channel) =>
      mongoProducts.push(GetProduct(channel.product_id))
    );

    mongoProducts = await Promise.all(mongoProducts);

    console.log({ mongoProducts });

    let productUsers = [];
    mongoProducts.map((product) => productUsers.push(GetUser(product.user_id)));

    productUsers = await Promise.all(productUsers);
    console.log({ productUsers });

    let mongoProductsJson = mongoProducts.map((product, index) => {
      return { ...product._doc, user: productUsers[index] };
    });

    let channelsJson = channels.map((channel, index) => {
      return {
        ...channel.dataValues,
        user: channel.user,
        product: mongoProductsJson[index],
      };
    });

    console.log({ channelsJson });

    return channelsJson;
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
