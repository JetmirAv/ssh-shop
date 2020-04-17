"use strict";
module.exports = (sequelize, DataTypes) => {
  const Cart = sequelize.define(
    "Cart",
    {
      user_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      variant_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      quantity: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
    },
    {
      underscored: true,
      tableName: "carts",
    }
  );
  Cart.associate = function (models) {
    // 1 - 1
    Cart.belongsTo(models.User, {
      foreignKey: "user_id",
      as: "user",
    });
  };
  return Cart;
};
