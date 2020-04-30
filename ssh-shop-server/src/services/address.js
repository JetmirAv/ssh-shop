const CustomError = require("../errors/CustomError");
const { Address, Sequelize } = require("../models/sequelize");
const Op = Sequelize.Op;
console.log("ne service!");

/**
 *
//  * @param {Number} address_id
//  * @returns Address
 */
const GetAddress = async (address_id) => {
  console.log("Para try-it te GetAddress");
  try {
    console.log("Brenda try-it te GetAddress");
    const address = await Address.findByPk(address_id);
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
console.log("CreateAddress is waiting");
const CreateAddress = async (data) => {
  try {
    const address = new Address({ ...data });
    console.log("Brenda CreateAdd");
    let user_id = await Address.findOne({
      where: { user_id: { [Op.like]: data.user_id } },
    });
    if (user_id)
      throw new CustomError("This user has already address", {}, 401);
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
  try {
    let combine_obj = await Address.findOne({
      where: { user_id: { [Op.like]: user_id } },
      where: { id: { [Op.like]: address_id } },
    });
    if (combine_obj) {
      const instance = new Address({ ...address.dataValues, ...data });
      await instance.validate();
      await address.update({
        ...instance.dataValues,
      });
    }
    return address;
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Address} address_id
//  * @returns Boolean
//  */
// const DeleteUser = async (user_id) => {
//   try {
//     const user = await GetUser(user_id);
//     await user.destroy();
//     return true;
//   } catch (err) {
//     throw err;
//   }
// };

module.exports = {
  CreateAddress,
  UpdateAddress,
  GetAddress,
};
