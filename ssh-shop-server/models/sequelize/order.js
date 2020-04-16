"use strict";
module.exports = (sequelize, DataTypes) => {
  const Order = sequelize.define(
    "Order",
    {
      user_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      card_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      address_id: {
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
      price: {
        type: DataTypes.FLOAT,
        allowNull: false,
      },
    },
    {
      underscored: true,
      tableName: "orders",
    }
  );
  Order.associate = function (models) {
    // 1 - 1
    Order.belongsTo(models.User, {
      foreignKey: "user_id",
      as: "User",
    });
    Order.belongsTo(models.Card, {
      foreignKey: "card_id",
      as: "Card",
    });
    Order.belongsTo(models.Address, {
      foreignKey: "address_id",
      as: "Address",
    });
  };
  return Order;
};
