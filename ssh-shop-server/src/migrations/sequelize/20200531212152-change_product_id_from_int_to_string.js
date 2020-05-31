"use strict";

module.exports = {
  up: async (queryInterface, Sequelize) => {
    await queryInterface.changeColumn("carts", "variant_id", {
      type: Sequelize.STRING,
      allowNull: false,
    });

    await queryInterface.changeColumn("orders", "variant_id", {
      type: Sequelize.STRING,
      allowNull: false,
    });

    await queryInterface.removeConstraint("wish_lists", "product_wishlist", {
      type: Sequelize.STRING,
      allowNull: false,
    });
    await queryInterface.changeColumn("wish_lists", "product_id", {
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
