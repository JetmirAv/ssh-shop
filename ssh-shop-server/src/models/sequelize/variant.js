"use strict";
const Sequelize = require("sequelize");

module.exports = class Variant extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        product_id: {
          type: Sequelize.INTEGER,
          allowNull: false,
        },
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
        tableName: "variants",
        sequelize,
      }
    );
  }

  static associate(models) {
    // 1 - 1
    this.belongsTo(models.Product, {
      foreignKey: "product_id",
      as: "Product",
    });

    // 1 - n
    this.hasMany(models.VariantOptions, {
      foreignKey: "variant_id",
      as: "options",
    });
  }
};
