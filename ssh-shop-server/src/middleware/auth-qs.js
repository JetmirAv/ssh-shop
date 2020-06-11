const jwt = require("jsonwebtoken");
const { User } = require("../models/sequelize");
const CustomError = require("../errors/CustomError");

const auth = async (req, res, next) => {
  let token = req.query.token;

  token = token.replace("Bearer ", "");
  try {
    const data = jwt.verify(token, process.env.JWT_KEY);
    const user = await User.findByPk(data.id);
    console.log("USERI:" + user);
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
