"use strict";
module.exports = (sequelize, DataTypes) => {
  const Card = sequelize.define(
    "Card",
    {
      user_id: {
        type: DataTypes.INTEGER,
        allowNull: false,
      },
      number: {
        type: DataTypes.STRING,
        allowNull: false,
      },
      exp_month: {
        type: DataTypes.STRING,
        allowNull: false,
      },
      exp_year: {
        type: DataTypes.STRING,
        allowNull: false,
      },
      code: {
        type: DataTypes.STRING,
        allowNull: false,
      },
    },
    {
      underscored: true,
      tableName: "cards",
    }
  );
  Card.associate = function (models) {
    // 1 - 1
    Card.belongsTo(models.User, {
      foreignKey: "user_id",
      as: "user",
    });
  };
  return Card;
};
