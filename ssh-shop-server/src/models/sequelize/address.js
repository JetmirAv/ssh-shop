"use strict";
const Sequelize = require("sequelize");

module.exports = class Address extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        user_id: {
          type: Sequelize.INTEGER,
          references: {
            model: sequelize.models.User,
            key: "id",
          },
        },
        street: {
          type: Sequelize.STRING,
        },
        postal: {
          type: Sequelize.STRING,
        },
        phone_number: {
          type: Sequelize.STRING,
        },
        city_id: {
          type: Sequelize.INTEGER,
        },
      },
      {
        underscored: true,
        tableName: "addresses",
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
    this.belongsTo(models.City, {
      foreignKey: "city_id",
      as: "city",
    });
  }
};
