const CustomError = require("./CustomError");
const sequelizeErrorHandler = require("./SequelizeErrors");

/**
 *
 * @param {Error} err
 */
const findError = (err) => {
  let error = {};

  error = sequelizeErrorHandler(err);
  if (error) return error;

  return new CustomError(err.message, err.name);
};

/**
 *
 * @param {Error} err
 * @param {Request} req
 * @param {Response} res
 * @param {*} next
 */
const handleError = (err, _req, res, _next) => {
  err = findError(err);

  return res.status(err.statusCode).json(err);
};

module.exports = handleError;
