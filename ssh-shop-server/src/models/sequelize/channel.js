"use strict";
module.exports = (sequelize, DataTypes) => {
  const Channel = sequelize.define(
    "Channel",
    {
      product_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      user_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      name: {
        type: DataTypes.STRING,
      },
    },
    {
      underscored: true,
      tableName: "channels",
    }
  );
  Channel.associate = function (models) {
    // 1 - 1
    Channel.belongsTo(models.User, {
      foreignKey: "user_id",
      as: "user",
    });
    Channel.belongsTo(models.Product, {
      foreignKey: "product_id",
      as: "product",
    });
  };
  return Channel;
};
