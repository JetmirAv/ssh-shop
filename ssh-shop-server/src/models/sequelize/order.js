"use strict";
const Sequelize = require("sequelize");

module.exports = class Order extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        user_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
        card_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
        address_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
        variant_id: {
          type: Sequelize.STRING,
          allowNull: false,
        },
        quantity: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
        price: {
          type: Sequelize.FLOAT,
          allowNull: false,
        },
      },
      {
        underscored: true,
        tableName: "orders",
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
    this.belongsTo(models.Card, {
      foreignKey: "card_id",
      as: "card",
    });
    this.belongsTo(models.Address, {
      foreignKey: "address_id",
      as: "address",
    });
  }
};
