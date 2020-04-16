"use strict";
module.exports = {
  up: (queryInterface, Sequelize) => {
    return queryInterface.sequelize.transaction((t) => {
      return Promise.all([
        queryInterface.createTable(
          "products",
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
            name: {
              type: Sequelize.STRING,
              allowNull: false,
            },
            description: {
              type: Sequelize.TEXT,
            },
            category_id: {
              type: Sequelize.INTEGER,
              allowNull: false,
            },
            discount_pt: {
              type: Sequelize.FLOAT,
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
          "products",
          ["user_id"],
          {
            type: "foreign key",
            name: "user_products",
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
          "products",
          ["category_id"],
          {
            type: "foreign key",
            name: "category_products",
            references: {
              //Required field
              table: "categories",
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
    return queryInterface.dropTable("products");
  },
};
