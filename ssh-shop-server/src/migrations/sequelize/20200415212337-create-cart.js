"use strict";
module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.sequelize.transaction((t) => {
      return Promise.all([
        queryInterface.createTable(
          "carts",
          {
            id: {
              allowNull: false,
              autoIncrement: true,
              primaryKey: true,
              type: Sequelize.INTEGER,
            },
            user_id: {
              type: Sequelize.INTEGER,
              allowNull: false,
            },
            variant_id: {
              type: Sequelize.INTEGER,
              allowNull: false,
            },
            quantity: {
              type: Sequelize.INTEGER,
              allowNull: false,
            },
            created_at: {
              allowNull: false,
              type: Sequelize.DATE,
            },
            updated_at: {
              allowNull: false,
              type: Sequelize.DATE,
            },
          },
          { transaction: t }
        ),
        queryInterface.addConstraint(
          "carts",
          ["user_id"],
          {
            type: "foreign key",
            name: "user_carts",
            references: {
              //Required field
              table: "users",
              field: "id",
            },
            onDelete: "cascade",
            onUpdate: "cascade",
          },
          { transaction: t }
        ),
      ]);
    });
  },
  down: (queryInterface) => {
    return queryInterface.dropTable("carts");
  },
};
