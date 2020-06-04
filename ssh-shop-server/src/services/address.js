const CustomError = require("../errors/CustomError");
const { Address, Sequelize } = require("../models/sequelize");
const Op = Sequelize.Op;

/**
 *
//  * @param {Number} address_id
//  * @returns Address
 */
const GetAddress = async (address_id) => {
  try {
    const address = await Address.findByPk(address_id);
    if (!address) throw new CustomError("Not found!", {}, 401);
    return address;
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Number} user_id
//  * @returns Address
 */
const GetAllAddresses = async (user_id) => {
  try {
    const address = await Address.findAll({
      where: {
        user_id: user_id,
      },
    });
    if (!address) throw new CustomError("Not found!", {}, 401);
    return address;
  } catch (err) {
    throw err;
  }
};

/**
 *
 * @param {Address} data
 * @returns Address
 */
const CreateAddress = async (data) => {
  try {
    const address = new Address({ ...data });
    await address.validate();
    await address.save();
    return address;
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Number} address_id
//  * @param {Address} data
//  * @returns Address
 */
const UpdateAddress = async (address_id, user_id, data) => {
  let street = data.street;
  let postal = data.postal;
  if (address_id == 0) throw new CustomError("Choose address first! ", {}, 401);
  if (street === "" && postal === "")
    throw new CustomError("Street and Postal can not be empty !", {}, 401);
  if (street === "")
    throw new CustomError("Street can not be empty !", {}, 401);
  if (postal === "")
    throw new CustomError("Postal can not be empty !", {}, 401);

  try {
    const address = await Address.findOne({
      where: {
        id: address_id,
        user_id: user_id,
      },
    });
    if (address) {
      const instance = new Address({ ...address.dataValues, ...data });
      await instance.validate();
      await address.update({
        ...instance.dataValues,
      });
      return address;
    } else {
      throw new CustomError("No combination between user and address", {}, 404);
    }
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Address} address_id
//  * @returns Boolean
//  */
const DeleteAddress = async (address_id) => {
  try {
    const address = await GetAddress(address_id);
    await address.destroy();
    return true;
  } catch (err) {
    throw err;
  }
};

module.exports = {
  CreateAddress,
  UpdateAddress,
  DeleteAddress,
  GetAddress,
  GetAllAddresses,
};
