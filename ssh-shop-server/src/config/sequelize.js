require("dotenv").config();

console.log(process.env);
module.exports = {
  
  development: { 
    username: process.env.DB_USERNAME,
    password: process.env.DB_PASSWORD,
    database: process.env.DATABASE_NAME,
    host: process.env.DB_HOST,
    dialect: process.env.DB_DIALECT,
    migrationStorage: "sequelize",
    seederStorage: "sequelize",
    migrationStorageTableName: "migration_meta",
    seederStorageTableName: "seeder_meta",
  },
  test: {
    username: process.env.DB_USERNAME,
    password: process.env.DB_PASSWORD,
    database: process.env.DATABASE_NAME,
    host: process.env.DB_HOST,
    dialect: process.env.DB_DIALECT,
    migrationStorage: "sequelize",
    seederStorage: "sequelize",
    migrationStorageTableName: "migration_meta",
    seederStorageTableName: "seeder_meta",
  },
  production: {
    username: process.env.DB_USERNAME,
    password: process.env.DB_PASSWORD,
    database: process.env.DATABASE_NAME,
    host: process.env.DB_HOST,
    dialect: process.env.DB_DIALECT,
    migrationStorage: "sequelize",
    seederStorage: "sequelize",
    migrationStorageTableName: "migration_meta",
    seederStorageTableName: "seeder_meta",
  },
};
