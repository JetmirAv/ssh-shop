const CustomError = require("./CustomError");

/**
 *
 * @param {Error} error
 */
const handler = (error) => {
  switch (error.name) {
    case "JsonWebTokenError":
      console.log("KNEJ");

      return new CustomError("Unauthorized", {}, 401, "JsonWebTokenError");
  }
};

module.exports = handler;
