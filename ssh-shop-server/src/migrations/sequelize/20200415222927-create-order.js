"use strict";
module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.sequelize.transaction((t) => {
      return Promise.all([
        queryInterface.createTable(
          "orders",
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
            card_id: {
              type: Sequelize.INTEGER,
              allowNull: false,
            },
            address_id: {
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
            price: {
              type: Sequelize.FLOAT,
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
          "orders",
          ["user_id"],
          {
            type: "foreign key",
            name: "user_orders",
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
          "orders",
          ["card_id"],
          {
            type: "foreign key",
            name: "card_orders",
            references: {
              //Required field
              table: "cards",
              field: "id",
            },
            onDelete: "cascade",
            onUpdate: "cascade",
          },
          { transaction: t }
        ),
        queryInterface.addConstraint(
          "orders",
          ["address_id"],
          {
            type: "foreign key",
            name: "address_orders",
            references: {
              //Required field
              table: "addresses",
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
    return queryInterface.dropTable("orders");
  },
};
