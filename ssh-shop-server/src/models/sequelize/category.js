"use strict";
const Sequelize = require("sequelize");

module.exports = class Category extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        name: {
          type: Sequelize.STRING,
          allowNull: false,
        },
        description: {
          type: Sequelize.TEXT,
        },
      },
      {
        underscored: true,
        tableName: "categories",
        sequelize,
      }
    );
  }

  static associate(models) {
    // 1 - n
    this.hasMany(models.Product, {
      foreignKey: "category_id",
      as: "products",
    });
  }
};
