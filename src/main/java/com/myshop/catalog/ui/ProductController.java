package com.myshop.catalog.ui;

import com.myshop.catalog.query.category.CategoryData;
import com.myshop.catalog.query.category.CategoryDataDao;
import com.myshop.catalog.query.product.CategoryProduct;
import com.myshop.catalog.query.product.ProductData;
import com.myshop.catalog.query.product.ProductQueryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    private CategoryDataDao categoryDataDao;
    private ProductQueryService productQueryService;

    public ProductController(CategoryDataDao categoryDataDao,
                             ProductQueryService productQueryService) {
        this.categoryDataDao = categoryDataDao;
        this.productQueryService = productQueryService;
    }

    @RequestMapping("/categories")
    public String categories(ModelMap model) {
        List<CategoryData> categories = categoryDataDao.findAll();
        model.addAttribute("categories", categories);
        return "category/categoryList";
    }

    @RequestMapping("/categories/{categoryId}")
    public String list(@PathVariable("categoryId") Long categoryId,
                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       ModelMap model) {
        CategoryProduct productInCategory = productQueryService.getProductInCategory(categoryId, page, 10);
        model.addAttribute("productInCategory", productInCategory);
        return "category/productList";
    }

    @RequestMapping("/products/{productId}")
    public String detail(@PathVariable("productId") String productId,
                         ModelMap model,
                         HttpServletResponse response) throws IOException {
        Optional<ProductData> product = productQueryService.getProduct(productId);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "category/productDetail";
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }
}
