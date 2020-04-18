"use strict";
const Sequelize = require("sequelize");

module.exports = class VariantOptions extends Sequelize.Model {
  static init(sequelize) {
    return super.init(
      {
        variant_id: {
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
        tableName: "variant_options",
        sequelize,
      }
    );
  }

  static associate(models) {
    // 1 - 1
    this.belongsTo(models.Variant, {
      foreignKey: "variant_id",
      as: "variant",
    });
  }
};
