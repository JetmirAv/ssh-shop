const CustomError = require("./CustomError");

/**
 *
 * @param {Error} error
 */
const handler = (error) => {
  switch (error.name) {
    case "SequelizeForeignKeyConstraintError":
      return new CustomError("Internal Server Error", 500, {}, "SequelizeForeignKeyConstraintError");
    case "SequelizeValidationError":
      let errors = {};
      error.errors.map((error) => (errors[error.path] = error.message));
      return new CustomError("Validation Error", errors, 400, "SequelizeValidationError");
  }
};

module.exports = handler;
