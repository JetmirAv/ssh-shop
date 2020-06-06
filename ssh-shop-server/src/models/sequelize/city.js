"use strict";
const Sequelize = require("sequelize");

module.exports = class City extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        name: {
          type: Sequelize.STRING,
          allowNull: false,
        },
        country_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
      },
      {
        underscored: true,
        tableName: "cities",
        sequelize,
        defaultScope: {
          include: [{ association: "country" }],
        },
      }
    );
  }

  static associate(models) {
    // 1 - 1
    this.belongsTo(models.Country, {
      foreignKey: "country_id",
      as: "country",
    });
  }
};
