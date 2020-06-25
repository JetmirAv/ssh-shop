'use strict';

module.exports = {
  up: (queryInterface, Sequelize) => {

      return queryInterface.bulkInsert(
        'cards', [
        {
          user_id: 1,
          number: "4774555533332222",
          exp_month: "March",
          exp_year: "2822",
          code: "393",
          created_at: new Date(),
          updated_at: new Date()
        },
        {
          user_id: 2,
          number: "4774555533332222",
          exp_month: "March",
          exp_year: "2822",
          code: "393",
          created_at: new Date(),
          updated_at: new Date()
          },
          {
            user_id: 3,
            number: "4774555533332222",
            exp_month: "March",
            exp_year: "2822",
            code: "393",
            created_at: new Date(),
            updated_at: new Date() 
            },
          {
            user_id: 4,
            number: "4774555533332222",
            exp_month: "March",
            exp_year: "2822",
            code: "393",
            created_at: new Date(),
            updated_at: new Date() 
              },
          {
            user_id: 5,
            number: "4774555533332222",
            exp_month: "March",
            exp_year: "2822",
            code: "393",
            created_at: new Date(),
            updated_at: new Date()  
           }
    ])
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
