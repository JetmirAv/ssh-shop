const CustomError = require("./CustomError");
const sequelizeErrorHandler = require("./SequelizeErrors");
const jwtErrorHandler = require("./JWTErrors");

/**
 *
 * @param {Error} err
 */
const findError = (err) => {
  let error = {};

  error = sequelizeErrorHandler(err);
  if (error) return error;
  error = jwtErrorHandler(err);
  if (error) return error;
  return err;
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
  console.log({ errKtu: err });

  return res.status(err.statusCode).json(err);
};

module.exports = handleError;
