"use strict";
module.exports = (sequelize, DataTypes) => {
  const Category = sequelize.define(
    "Category",
    {
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
      tableName: "categories",
    }
  );
  Category.associate = function (models) {
    // 1 - n
    Category.hasMany(models.Product, {
      foreignKey: "category_id",
      as: "products",
    });
  };
  return Category;
};
