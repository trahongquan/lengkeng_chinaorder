package com.shoponline.chinaorder.contrtoller.mainController;

import com.shoponline.chinaorder.entity.*;
import com.shoponline.chinaorder.service.authority.AuthorityService;
import com.shoponline.chinaorder.service.brand.BrandService;
import com.shoponline.chinaorder.service.cart.CartService;
import com.shoponline.chinaorder.service.category.CategoryService;
import com.shoponline.chinaorder.service.color.ColorService;
import com.shoponline.chinaorder.service.commune.CommuneService;
import com.shoponline.chinaorder.service.district.DistrictService;
import com.shoponline.chinaorder.service.image.ImageService;
import com.shoponline.chinaorder.service.logs.LogsService;
import com.shoponline.chinaorder.service.orders.OrdersService;
import com.shoponline.chinaorder.service.orderitem.OrderItemService;
import com.shoponline.chinaorder.service.people.PeopleService;
import com.shoponline.chinaorder.service.product.ProductService;
import com.shoponline.chinaorder.service.province.ProvinceService;
import com.shoponline.chinaorder.service.review.ReviewService;
import com.shoponline.chinaorder.service.size.SizeService;
import com.shoponline.chinaorder.service.status.StatusService;
import com.shoponline.chinaorder.service.supplier.SupplierService;
import com.shoponline.chinaorder.service.unit.UnitService;
import com.shoponline.chinaorder.service.users.UserService;
import com.shoponline.chinaorder.service.variant.VariantService;
import com.shoponline.chinaorder.service.voucher.VoucherService;
import com.shoponline.chinaorder.support.RoleSystem;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/Lengkeng")
public class mainController {

    private final AuthorityService authorityService;
    private final UserService userService;
    private final BrandService brandService;
    private final CartService cartService;
    private final CategoryService categoryService;
    private final ColorService colorService;
    private final CommuneService communeService;
    private final DistrictService districtService;
    private final ImageService imageService;
    private final LogsService logsService;
    private final OrdersService ordersService;
    private final OrderItemService orderItemService;
    private final PeopleService peopleService;
    private final ProductService productService;
    private final ProvinceService provinceService;
    private final ReviewService reviewService;
    private final SizeService sizeService;
    private final StatusService statusService;
    private final SupplierService supplierService;
    private final UnitService unitService;
    private final VariantService variantService;
    private final VoucherService voucherService;

    @Autowired
    public mainController(AuthorityService authorityService, UserService userService, BrandService brandService, CartService cartService, CategoryService categoryService, ColorService colorService, CommuneService communeService, DistrictService districtService, ImageService imageService, LogsService logsService, OrdersService ordersService, OrderItemService orderItemService, PeopleService peopleService, ProductService productService, ProvinceService provinceService, ReviewService reviewService, SizeService sizeService, StatusService statusService, SupplierService supplierService, UnitService unitService, VariantService variantService, VoucherService voucherService) {
        this.authorityService = authorityService;
        this.userService = userService;
        this.brandService = brandService;
        this.cartService = cartService;
        this.categoryService = categoryService;
        this.colorService = colorService;
        this.communeService = communeService;
        this.districtService = districtService;
        this.imageService = imageService;
        this.logsService = logsService;
        this.ordersService = ordersService;
        this.orderItemService = orderItemService;
        this.peopleService = peopleService;
        this.productService = productService;
        this.provinceService = provinceService;
        this.reviewService = reviewService;
        this.sizeService = sizeService;
        this.statusService = statusService;
        this.supplierService = supplierService;
        this.unitService = unitService;
        this.variantService = variantService;
        this.voucherService = voucherService;
    }

    String template = "layout";
    public String Redirect(String url, Object success) {
        if (success instanceof Boolean) {
            if((boolean) success){
                if(url.contains("?")){
                    String successParam = "&success=" + success;
                    return "redirect:/Lengkeng" + url + successParam;
                } else {
                    String successParam = "?success=" + success;
                    return "redirect:/Lengkeng" + url + successParam;
                }
            } else{
                if(url.contains("?")){
                    String successParam = "&unsuccess=" + true;
                    return "redirect:/Lengkeng" + url + successParam;
                } else {
                    String successParam = "?unsuccess=" + true;
                    return "redirect:/Lengkeng" + url + successParam;
                }
            }
        } else {
            return "redirect:/Lengkeng" + url;
        }
    }

    @GetMapping({"/", ""})
    public String Lengkeng(Principal principal, Authentication authentication){
        if(principal != null && authentication != null){
            String username = principal.getName();
            String authority = "";
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                authority = grantedAuthority.getAuthority();
            }
            if((authority.equals(RoleSystem.ROLE_ADMIN) || authority.equals(RoleSystem.ROLE_MANAGER))) return Redirect("/admin/dashboard","");
        }
        return "visitors/index";
    }
    @GetMapping("/accouunt")
    public String accouunt(){
        return "visitors/cart";
    }
    @GetMapping("/cart")
    public String cart(){
        return "visitors/cart";
    }
    @GetMapping("/category")
    public String category(){
        return "visitors/category";
    }
    @GetMapping("/product")
    public String product(){
        return "visitors/product";
    }
    @GetMapping("/checkout")
    public String checkout(){
        return "visitors/checkout";
    }
    @GetMapping("/checkout_payment")
    public String checkoutPayment(){
        return "visitors/checkout-payment";
    }
    @GetMapping("/checkout_shipping")
    public String checkoutShipping(){
        return "visitors/checkout-shipping";
    }

                                /******************************************************************/
                                /***************************** ADMIN ******************************/
                                /******************************************************************/
//    @GetMapping("/admin/dashboard")
//    public String dashboard(){
//        return "admin/Dashboard";
//    }

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("content", "pages/dashboard");
        model.addAttribute("title", "Dashboard");
        return template;
    }

    /***************************************************/
    /********************* Suports *********************/
    /***************************************************/


    /********************* suppliers *********************/

    @GetMapping("/admin/suppliers")
    public String suplier(Model model,
                          @RequestParam(value = "success", defaultValue = "false") boolean success,
                          @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        List<Supplier> suppliersx = supplierService.findSuppliersNotUsedInProducts();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("suppliersx", suppliersx);
        model.addAttribute("content", "pages/support/supplier");
        model.addAttribute("title", "Quản lý Nhà cung cấp");

        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        return template;
    }

    @PostMapping({"/admin/suppliers/add"})
    public String Suppliers_Add(Model model, @ModelAttribute Supplier supplier) {
        try {
            supplierService.createSupplier(supplier);
            return Redirect("/admin/suppliers", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/suppliers", false);
        }
    }


    @PostMapping({"/admin/suppliers/del"})
    public String Suppliers_Del(Model model,
                                @RequestParam("id") @Min(1) int supplier_id){
        try{
            supplierService.deleteSupplier(supplier_id);
            return Redirect("/admin/suppliers", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/suppliers", false);
        }
    }

    /********************* brands *********************/

    @GetMapping("/admin/brands")
    public String brands(Model model,
                         @RequestParam(value = "success", defaultValue = "false") boolean success,
                         @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        List<Brands> brands = brandService.getAllBrands();
        List<Brands> brandsx = brandService.findBrandsNotUsedInProducts();
        model.addAttribute("brands", brands);
        model.addAttribute("brandsx", brandsx);
        model.addAttribute("content", "pages/support/brands");
        model.addAttribute("title", "Quản lý Thương hiệu");

        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        return template;
    }


    @PostMapping({"/admin/brands/add"})
    public String Brands_Add(Model model,
                             @RequestParam("brand_name") String brand_name) {
        try {
            Brands brand = new Brands(brand_name);
            brandService.createBrand(brand);
            return Redirect("/admin/brands", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/brands", false);
        }
    }


    @PostMapping({"/admin/brands/del"})
    public String Brands_Del(Model model,
                             @RequestParam("id") @Min(1) int brand_id){
        try{
            brandService.deleteBrand(brand_id);
            return Redirect("/admin/brands", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/brands", false);
        }
    }

    @PostMapping({"/admin/brands/edit"})
    public String Brands_Edit(Model model,
                              @RequestParam("id") int id,
                              @RequestParam("name") String name){
        try{
            Brands brand = brandService.findBrandById(id);
            brand.setBrandname(name);
            brandService.createBrand(brand);
            return Redirect("/admin/brands", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/brands", false);
        }
    }

    /********************* categories *********************/

    @GetMapping("/admin/categories")
    public String categories(Model model,
                             @RequestParam(value = "success", defaultValue = "false") boolean success,
                             @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        List<Categories> categories = categoryService.getAllCategories();
        List<Categories> categoriesx = categoryService.findCategoriesNotUsedInProducts();
        model.addAttribute("categories", categories);
        model.addAttribute("categoriesx", categoriesx);
        model.addAttribute("content", "pages/support/categories");
        model.addAttribute("title", "Quản lý phân loại hàng");

        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        return template;
    }

    @PostMapping({"/admin/categories/add"})
    public String Categories_Add(Model model,
                                 @RequestParam("category_name") String category_name) {
        try {
            Categories category = new Categories(category_name);
            categoryService.createCategory(category);
            return Redirect("/admin/categories", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/categories", false);
        }
    }


    @PostMapping({"/admin/categories/del"})
    public String Categories_Del(Model model,
                                 @RequestParam("id") @Min(1) int category_id){
        try{
            categoryService.deleteCategory(category_id);
            return Redirect("/admin/categories", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/categories", false);
        }
    }

    @PostMapping({"/admin/categories/edit"})
    public String Categories_Edit(Model model,
                                  @RequestParam("id") int id,
                                  @RequestParam("name") String name){
        try{
            Categories category = categoryService.findCategoryById(id);
            category.setCatname(name);
            categoryService.createCategory(category);
            return Redirect("/admin/categories", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/categories", false);
        }
    }

    /********************* units *********************/

    @GetMapping("/admin/units")
    public String unit(Model model,
                       @RequestParam(value = "success", defaultValue = "false") boolean success,
                       @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        List<Unit> units = unitService.getAllUnits();
        List<Unit> unitsx = unitService.findUnitsNotUsedInProducts();
        model.addAttribute("units", units);
        model.addAttribute("unitsx", unitsx);
        model.addAttribute("content", "pages/support/units");
        model.addAttribute("title", "Quản lý đơn vị tính");

        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        return template;
    }

    @PostMapping({"/admin/units/add"})
    public String Units_Add(Model model,
                            @RequestParam("unit_name") String unit_name) {
        try {
            Unit unit = new Unit(unit_name);
            unitService.createUnit(unit);
            return Redirect("/admin/units", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/units", false);
        }
    }


    @PostMapping({"/admin/units/del"})
    public String Units_Del(Model model,
                            @RequestParam("id") @Min(1) int unit_id){
        try{
            unitService.deleteUnit(unit_id);
            return Redirect("/admin/units", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/units", false);
        }
    }

    @PostMapping({"/admin/units/edit"})
    public String Units_Edit(Model model,
                             @RequestParam("id") int id,
                             @RequestParam("name") String name){
        try{
            Unit unit = unitService.findUnitById(id);
            unit.setUnit(name);
            unitService.createUnit(unit);
            return Redirect("/admin/units", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/units", false);
        }
    }

    /***************************************************/
    /********************* Product *********************/
    /***************************************************/

    @GetMapping("/admin/products")
    public String products(Model model,
                           @RequestParam(value = "success", defaultValue = "false") boolean success,
                           @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        List<Products> products = productService.getAllProducts();

        model.addAttribute("products", products);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("brands", brandService.getAllBrands());
        model.addAttribute("suppliers", supplierService.getAllSuppliers());
        model.addAttribute("units", unitService.getAllUnits());
        model.addAttribute("content", "pages/product/product_manager");
        model.addAttribute("title", "Quản lý sản phẩm");

        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        return template;
    }

    @PostMapping({"/admin/products/add"})
    public String Products_Add(Model model, @ModelAttribute Products products) {
        try {
            System.out.println(products);
            productService.createProduct(products);
            return Redirect("/admin/products", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/products", false);
        }
    }

    @PostMapping({"/admin/products/del/{id}"})
    public String Products_del(@PathVariable int id) {
        try {
            productService.deleteProduct(id, variantService);
            return Redirect("/admin/products", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/products", false);
        }
    }

    @PostMapping({"/admin/products/detail"})
    public String Products_detail(Model model, @ModelAttribute Products products) {
//        try {
//            System.out.println(products);
//            productService.createProduct(products);
//            return Redirect("/admin/products", true);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/products", false);
//        }
    }


    /***************************************************/
    /********************* Product *********************/
    /***************************************************/

    @GetMapping("/admin/variants")
    public String variants(Model model,
                           @RequestParam(value = "success", defaultValue = "false") boolean success,
                           @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        List<Variants> variants = variantService.getAllVariants();

        model.addAttribute("variants", variants);
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("colors", colorService.getAllColors());
        model.addAttribute("sizes", sizeService.getAllSizes());
        model.addAttribute("content", "pages/product/variant_manager");
        model.addAttribute("title", "Quản lý sản phẩm");

        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        return template;
    }

    @PostMapping({"/admin/variants/add"})
    public String Variants_Add(@ModelAttribute Variants variants) {
        try {
            System.out.println(variants);
            variantService.createVariant(variants);
            return Redirect("/admin/variants", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/variants", false);
        }
    }
}
