const MongoClient = require("mongodb").MongoClient;
const url = `mongodb+srv://${process.env.MONGO_USERNAME}:${process.env.MONGO_PASSWORD}@${process.env.MONGO_HOST}`;
var _db;

module.exports = {
  connectToServer: function (callback) {
    MongoClient.connect(
      url,
      { useNewUrlParser: true, useUnifiedTopology: true },
      function (err, client) {
        _db = client.db("ssh-shop");
        return callback(err);
      }
    );
  },

  getDb: function () {
    return _db;
  },
};
