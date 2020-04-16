"use strict";
module.exports = (sequelize, DataTypes) => {
  const VariantOptions = sequelize.define(
    "VariantOptions",
    {
      variant_id: {
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
      tableName: "variant_options",
    }
  );
  VariantOptions.associate = function (models) {
    // 1 - 1
    VariantOptions.belongsTo(models.Variant, {
      foreignKey: "variant_id",
      as: "Variant",
    });
  };
  return VariantOptions;
};
