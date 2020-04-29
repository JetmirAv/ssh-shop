"use strict";
module.exports = {
  up: async (queryInterface, Sequelize) => {
    await queryInterface.createTable("variant_options", {
      id: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER,
      },
      variant_id: {
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
      created_at: {
        allowNull: false,
        type: Sequelize.DATE,
      },
      updated_at: {
        allowNull: false,
        type: Sequelize.DATE,
      },
    });
    await queryInterface.addConstraint("variant_options", ["variant_id"], {
      type: "foreign key",
      name: "variant_options",
      references: {
        //Required field
        table: "variants",
        field: "id",
      },
      onDelete: "cascade",
      onUpdate: "cascade",
    });
    return Promise.resolve();
  },
  down: (queryInterface) => {
    return queryInterface.dropTable("variant_options");
  },
};
