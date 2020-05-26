const jwt = require("jsonwebtoken");
const { User } = require("../models/sequelize");
const CustomError = require("../errors/CustomError");

const auth = async (req, res, next) => {
  let token = req.header("Authorization") || "";

  token = token.replace("Bearer ", "");

  console.log("====================================");
  console.log({ token });
  console.log("====================================");

  try {
    const data = jwt.verify(token, process.env.JWT_KEY);
    const user = await User.findByPk(data.id);
    if (!user) throw new CustomError("Unathorized", {}, 403);

    req.user = user;
    req.token = token;

    next();
  } catch (error) {
    console.log({ error });

    next(error);
  }
};
module.exports = auth;
