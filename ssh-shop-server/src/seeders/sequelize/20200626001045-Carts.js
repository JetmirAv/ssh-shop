'use strict';

module.exports = {
  up: (queryInterface, Sequelize) => {

      return queryInterface.bulkInsert(
        'carts', [
          {
            user_id: 1,
            variant_id: "5ef54a96655728416fbb172d",
            quantity: "3",
            product_id: "5ef54a96655728416fbb172a",
            created_at: new Date(),
            updated_at: new Date(),  
            },
          {
            user_id: 2,
            variant_id: "5ef546141afa2e3d0a854ac9",
            quantity: "3",
            product_id: "5ef546141afa2e3d0a854ac4",
            created_at: new Date(),
            updated_at: new Date()  
            },
          {
            user_id: 3,
            variant_id: "5ef546141afa2e3d0a854acb",
            quantity: "3",
            product_id: "5ef546141afa2e3d0a854ac4",
            created_at: new Date(),
            updated_at: new Date(),
            },
          {
            user_id: 4,
            variant_id: "5ef546141afa2e3d0a854acb",
            quantity: "3",
            product_id: "5ef546141afa2e3d0a854ac4",
            created_at: new Date(),
            updated_at: new Date(),  
            },
          {
            user_id: 5,
            variant_id: "5ef546141afa2e3d0a854acb",
            quantity: "3",
            product_id: "5ef546141afa2e3d0a854ac4",
            created_at: new Date(),
            updated_at: new Date(),  
            },
          ]);
  },

  down: (queryInterface, Sequelize) => {
    /*
      Add reverting commands here.
      Return a promise to correctly handle asynchronicity.

      Example:
      return queryInterface.bulkDelete('People', null, {});
    */
  }
};
