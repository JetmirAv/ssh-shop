"use strict";
const Sequelize = require("sequelize");

module.exports = class Country extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        name: {
          type: Sequelize.STRING,
          allowNull: false,
        },
      },
      {
        underscored: true,
        tableName: "countries",
        sequelize,
      }
    );
  }

  static associate(models) {
    // 1 - n
    this.hasMany(models.City, {
      foreignKey: "country_id",
      as: "cities",
    });
  }
};
