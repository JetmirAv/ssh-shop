"use strict";
module.exports = (sequelize, DataTypes) => {
  const Role = sequelize.define(
    "Role",
    {
      name: {
        type: DataTypes.STRING,
        allowNull: false,
      },
      description: {
        type: DataTypes.TEXT,
        allowNull: false,
      },
    },
    {
      underscored: true,
      tableName: "roles",
    }
  );
  Role.associate = function (models) {
    Role.hasMany(models.User, {
      foreignKey: "role_id",
      as: "Users",
    });
  };
  return Role;
};
