"use strict";
module.exports = (sequelize, DataTypes) => {
  const Variant = sequelize.define(
    "Variant",
    {
      product_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      name: {
        type: DataTypes.STRING,
        allowNull: false,
      },
      description: {
        type: DataTypes.TEXT,
      },
    },
    {
      underscored: true,
      tableName: "variants",
    }
  );
  Variant.associate = function (models) {
    // 1 - 1
    Variant.belongsTo(models.Product, {
      foreignKey: "product_id",
      as: "Product",
    });

    // 1 - n
    Variant.hasMany(models.VariantOptions, {
      foreignKey: "variant_id",
      as: "Options",
    });
  };
  return Variant;
};
