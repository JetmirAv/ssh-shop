"use strict";
const Sequelize = require("sequelize");

module.exports = class WishList extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        user_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
        product_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
      },
      {
        underscored: true,
        tableName: "wish_lists",
        sequelize,
      }
    );
  }

  static associate(models) {
    // 1 - 1
    this.belongsTo(models.User, {
      foreignKey: "user_id",
      as: "user",
    });
    this.belongsTo(models.Product, {
      foreignKey: "product_id",
      as: "product",
    });
  }
};
