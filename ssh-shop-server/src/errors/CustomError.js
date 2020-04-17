class CustomError extends Error {
  constructor(message, errors, statusCode = "500", name = "Custom Error") {
    super();
    this.statusCode = statusCode;
    this.message = message;
    this.errors = errors;
    this.name = name;
  }
}

module.exports = CustomError;
