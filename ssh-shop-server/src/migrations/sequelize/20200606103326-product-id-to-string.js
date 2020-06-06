"use strict";

module.exports = {
  up: async (queryInterface, Sequelize) => {
    await queryInterface.removeConstraint("channels", "product_channels", {
      type: Sequelize.STRING,
      allowNull: false,
    });
    await queryInterface.changeColumn("channels", "product_id", {
      type: Sequelize.STRING,
      allowNull: false,
    });

    return Promise.resolve();
  },

  down: (queryInterface, Sequelize) => {
    /*
      Add reverting commands here.
      Return a promise to correctly handle asynchronicity.

      Example:
      return queryInterface.dropTable('users');
    */
  },
};
