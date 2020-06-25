const CustomError = require("../errors/CustomError");
const Product = require("../models/mongo/products");

/**
 *
//  * @param {Number} product_id
//  * @returns Product
 */
const GetMyProducts = async (user) => {
  try {
    let result = await Product.find({ user_id: user.id });
    return result;
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @param {Number} product_id
//  * @returns Product
 */
const GetProduct = async (product_id) => {
  try {
    const product = await Product.findById(product_id);
    if (!product) throw new CustomError("Not found!", {}, 404);
    return product;
  } catch (err) {
    throw err;
  }
};

/**
 *
//  * @returns product
 */
/**
 *
//  * @returns product
 */
const GetAllProducts = async (
  sort_string = "price-asc",
  categoryId,
  search,
  user_id
) => {
  try {
    let sort_arr = sort_string.split("-");
    let sort_column = sort_arr[0];
    let sort_order = sort_arr[1];
    if (sort_column == "price") {
      sort_column = "lowestPrice";
    }
    if (sort_order == "asc") {
      sort_order = 1;
    } else {
      sort_order = -1;
    }
    if (!isNaN(user_id)) {
      var product = await Product.find({ user_id: [user_id] }).sort({
        [sort_column]: sort_order,
      });
    } else if (isNaN(categoryId) && !search) {
      var product = await Product.find().sort({ [sort_column]: sort_order });
    } else if (!search) {
      var product = await Product.find({ category_id: categoryId }).sort({
        [sort_column]: sort_order,
      });
    } else {
      let searchArr = search.split("-");

      for (str in searchArr) {
        var product = await Product.find({
          name: { $regex: searchArr[str] },
        }).sort({ [sort_column]: sort_order });
      }
    }

    if (!product) throw new CustomError("Not found!", {}, 404);
    return product;
  } catch (err) {
    throw err;
  }
};
/**
 *
 * @param {Product} data
 * @returns Product
 */
const CreateProduct = async (data) => {
  try {
    let product = new Product({
      user_id: data.user_id,
      name: data.name,
      description: data.description,
      category_id: data.category_id,
      discount_pt: data.discount_pt,
      variants: data.variants,
      combinations: data.combinations,
      lowestPrice: findCheapestPrice(data.combinations),
    });
    await product.save();
    return product;
  } catch (err) {
    throw err;
  }
};

const findCheapestPrice = function (combinations) {
  let arr = [];
  for (i in combinations) {
    arr.push(combinations[i].price);
  }
  arr.sort(function (a, b) {
    return a - b;
  });
  return arr[0];
};
/**
 *
//  * @param {Number} product_id
//  * @param {Product} data
//  * @returns Product
 */
const UpdateProduct = async (product_id, user_id, data) => {
  try {
    console.log(user_id);
    const product = Product.findByIdAndUpdate(
      { _id: product_id },
      {
        user_id: user_id,
        name: data.name,
        description: data.description,
        category_id: data.category_id,
        discount_pt: data.discount_pt,
        variants: data.variants,
        combinations: data.combinations,
        lowestPrice: findCheapestPrice(data.combinations),
      }
    );
    return product;
  } catch (err) {
    console.log({ err });

    throw err;
  }
};

/**
 *
 * @param {Product} product_id
 * @returns Boolean
 */
const DeleteProduct = async (product_id) => {
  try {
    const product = await Product.findByIdAndDelete(product_id);
    return true;
  } catch (err) {
    throw err;
  }
};

module.exports = {
  GetProduct,
  CreateProduct,
  UpdateProduct,
  DeleteProduct,
  GetAllProducts,
  GetMyProducts,
};
