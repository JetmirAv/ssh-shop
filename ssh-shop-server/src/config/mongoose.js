require("dotenv").config();
const mongoose = require("mongoose");

const uri = `mongodb+srv://${process.env.MONGO_USERNAME}:${process.env.MONGO_PASSWORD}@${process.env.MONGO_HOST}`;

try {
  mongoose.connect(uri, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
  });

  const db = mongoose.connection;

  db.on("error", () => {
    console.error("> MongoDB Error Connection");
  });

  db.once("open", () => {
    console.info("> MongoDB Successfully initialized");
  });
} catch (err) {
  throw err;
}

module.exports = mongoose;
