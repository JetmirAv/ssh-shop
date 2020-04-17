"use strict";
module.exports = (sequelize, DataTypes) => {
  const Product = sequelize.define(
    "Product",
    {
      user_id: {
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
      category_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      discount_pt: {
        type: DataTypes.FLOAT,
      },
    },
    {
      underscored: true,
      tableName: "products",
    }
  );
  Product.associate = function (models) {
    // 1 - 1
    Product.belongsTo(models.User, {
      foreignKey: "user_id",
      as: "user",
    });
    Product.belongsTo(models.Category, {
      foreignKey: "category_id",
      as: "category",
    });
  };
  return Product;
};
