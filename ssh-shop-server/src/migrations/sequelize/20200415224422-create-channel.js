"use strict";
module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.sequelize.transaction((t) => {
      return Promise.all([
        queryInterface.createTable(
          "channels",
          {
            id: {
              allowNull: false,
              autoIncrement: true,
              primaryKey: true,
              type: Sequelize.INTEGER,
            },
            product_id: {
              type: Sequelize.INTEGER,
              allowNull: false,
            },
            user_id: {
              type: Sequelize.INTEGER,
              allowNull: false,
            },
            name: {
              type: Sequelize.STRING,
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
          "channels",
          ["user_id"],
          {
            type: "foreign key",
            name: "user_channels",
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
        queryInterface.addConstraint(
          "channels",
          ["product_id"],
          {
            type: "foreign key",
            name: "product_channels",
            references: {
              //Required field
              table: "products",
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
  down: (queryInterface, Sequelize) => {
    return queryInterface.dropTable("channels");
  },
};
