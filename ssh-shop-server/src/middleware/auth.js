const jwt = require("jsonwebtoken");
const User = require("../models/sequelize/user");

const auth = async (req, res, next) => {
  let token = req.header("Authorization") || "";
  token = token.replace("Bearer ", "");
  try {
    const data = jwt.verify(token, process.env.JWT_KEY);
    const user = await User.findByPk(data.id);
    if (!user) throw new Error();

    req.user = user;
    req.token = token;

    next();
  } catch (error) {
    res.status(401).send({ error: "Not authorized to access this resource" });
  }
};
module.exports = auth;
