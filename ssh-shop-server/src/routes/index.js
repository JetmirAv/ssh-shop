let usersRouter = require("./users");
let authRouter = require("./auth");
let channelRouter = require("./channel");
let productsRouter = require("./products");
let citiesRouter = require("./cities");
let countriesRouter = require("./countries");
let testRouter = require("./test");
let categoriesRouter = require("./categories");

module.exports = (app) => {
  app.use("/users", usersRouter);
  app.use("/auth", authRouter);
  app.use("/channels", channelRouter);
  app.use("/products", productsRouter);
  app.use("/cities", citiesRouter);
  app.use("/countries", countriesRouter);
  app.use("/test", testRouter);
  app.use("/categories", categoriesRouter);
};
