package product_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
