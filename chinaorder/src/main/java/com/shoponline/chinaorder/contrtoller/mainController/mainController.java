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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping({"/", ""})
    public String Lengkeng(){
//        provinceService.getAllProvinces().forEach(p -> System.out.println(p));
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
        return "layout";
    }
    @GetMapping("/admin/suppliers")
    public String suplier(Model model) {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        List<Supplier> suppliersx = supplierService.findSuppliersNotUsedInProducts();
        model.addAttribute("suppliers", suppliers);
        model.addAttribute("suppliersx", suppliersx);
        model.addAttribute("content", "pages/support/suplier");
        model.addAttribute("title", "Quản lý Nhà cung cấp");
        return "layout";
    }
    @GetMapping("/admin/brands")
    public String brands(Model model) {
        List<Brands> brands = brandService.getAllBrands();
        List<Brands> brandsx = brandService.findBrandsNotUsedInProducts();
        model.addAttribute("brands", brands);
        model.addAttribute("brandsx", brandsx);
        model.addAttribute("content", "pages/support/brands");
        model.addAttribute("title", "Quản lý Thương hiệu");
        return "layout";
    }
    @GetMapping("/admin/categories")
    public String categories(Model model) {
        List<Categories> categories = categoryService.getAllCategories();
        List<Categories> categoriesx = categoryService.findCategoriesNotUsedInProducts();
        model.addAttribute("categories", categories);
        model.addAttribute("categoriesx", categoriesx);
        model.addAttribute("content", "pages/support/categories");
        model.addAttribute("title", "Quản lý phân loại hàng");
        return "layout";
    }
    @GetMapping("/admin/units")
    public String unit(Model model) {
        List<Unit> units = unitService.getAllUnits();
        List<Unit> unitsx = unitService.findUnitsNotUsedInProducts();
        model.addAttribute("units", units);
        model.addAttribute("unitsx", unitsx);
        model.addAttribute("content", "pages/support/units");
        model.addAttribute("title", "Quản lý đơn vị tính");
        return "layout";
    }
}
