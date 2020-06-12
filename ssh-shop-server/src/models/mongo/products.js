const mongoose = require("../../config/mongoose");
const Schema = mongoose.Schema;
const ProductSchema = Schema(
  {
    user_id: { type: Number, required: true },
    name: { type: String, required: true },
    description: { type: String, required: true },
    category_id: { type: Number, required: true },
    discount_pt: { type: Number, required: true },
    variants: [
      new Schema({
        name: { type: String, required: true },
        options: { type: Schema.Types.Mixed, required: true },
      }),
    ],
    combinations: [
      new Schema(
        {
          stock: { type: Number, required: true },
          price: { type: Number, required: true },
          photo: { type: String, required: false },
        },
        { strict: false }
      ),
    ],
    lowestPrice: { type: Number, required: true },
  },
  { timestamps: { created_at: true, updatedAt: true } }
);
module.exports = mongoose.model("products", ProductSchema);
