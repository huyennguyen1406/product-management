package product_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import product_management.entity.Product;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    List<Product> products = Arrays.asList(
            new Product(1, "Laptop", 1500.0, 1),
            new Product(2, "Smartphone", 800.0, 2),
            new Product(3, "Headphones", 200.0, 3)
    );

    @GetMapping(path = {"","/"})
    public String showProducts(Model model) {
        // Tạo danh sách sản phẩm giả lập

        model.addAttribute("products", products); // Gửi danh sách sản phẩm tới view
        return "product-list"; // Tên của file HTML trong thư mục templates
    }

    @GetMapping("/{id}")
    public String showProductDetails(@PathVariable int id, Model model) {
        // Lấy thông tin sản phẩm chi tiết (giả lập)
        Product product = products.stream().filter(p->p.getId()==id).findAny().orElse(null); // Dữ liệu giả lập
        model.addAttribute("product", product);
        return "product-detail";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        products.add(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Product product = products.stream().filter(p -> p.getId() == id).findAny().orElse(null);
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product product) {
        products.removeIf(p -> p.getId() == product.getId());
        products.add(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        products.removeIf(p -> p.getId() == id);
        return "redirect:/products";
    }

    @PostMapping("/updateQuantity")
    @ResponseBody
    public String updateQuantity(@RequestBody Product updatedProduct) {
        for (Product product : products) {
            if (product.getId() == updatedProduct.getId()) {
                product.setQuantity(updatedProduct.getQuantity());
                return "Cập nhật thành công";
            }
        }
        return "Sản phẩm không tồn tại";
    }
}
