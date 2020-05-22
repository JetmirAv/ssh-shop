("use strict");
const Sequelize = require("sequelize");

module.exports = class UserSocket extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        user_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
        socket_id: {
          type: Sequelize.STRING,
          allowNull: false,
        },
      },
      {
        underscored: true,
        tableName: "user_sockets",
        sequelize,
      }
    );
  }

  static associate(models) {
    // 1 - 1
    this.belongsTo(models.User, {
      foreignKey: "user_id",
      as: "Product",
    });
  }
};
