const { CreateAddress, UpdateAddress } = require("../services/address");
console.log("ne controller!");
// const func = async (req, res) => {
//   await res.send("respond with a resource");
// };

/**
 *
 * @param {Request} req
 * @param {Response} res
 */
const create = async (req, res, next) => {
  try {
    const address = await CreateAddress(req.body);
    return res.status(200).json({ address });
  } catch (err) {
    next(err);
  }
};

/**
 *
 * @param {Request} req
 * @param {Response} res
 */
console.log("KEQQ!");
const update = async (req, res, next) => {
  try {
    console.log("MIR !");
    const address = await UpdateAddress(
      req.params.address_id,
      req.params.user_id,
      req.body
    );
    return res.status(200).json({ address });
  } catch (err) {
    next(err);
  }
};

// /**
//  *
//  * @param {Request} req
//  * @param {Response} res
//  */
// const drop = async (req, res, next) => {
//   try {
//     const response = await DeleteUser(req.params.user_id);

//     return res.status(200).json(response);
//   } catch (err) {
//     next(err);
//   }
// };

// /**
//  *
//  * @param {Request} req
//  * @param {Response} res
//  * @param {*} next
//  */
// const get = async (req, res, next) => {
//   try {
//     return res.status(200).json({ user: await GetUser(req.params.user_id) });
//   } catch (err) {
//     next(err);
//   }
// };

module.exports = {
  create,
  update,
};
