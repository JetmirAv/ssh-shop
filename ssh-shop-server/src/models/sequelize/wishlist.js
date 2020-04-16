"use strict";
module.exports = (sequelize, DataTypes) => {
  const WishList = sequelize.define(
    "WishList",
    {
      user_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      product_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
    },
    {
      underscored: true,
      tableName: "wish_lists",
    }
  );
  WishList.associate = function (models) {
    // 1 - 1
    WishList.belongsTo(models.User, {
      foreignKey: "user_id",
      as: "User",
    });
    WishList.belongsTo(models.Product, {
      foreignKey: "product_id",
      as: "Product",
    });
  };
  return WishList;
};
