package com.shoponline.chinaorder.contrtoller.mainController;

import com.shoponline.chinaorder.config.minioConfig.MinioService;
import com.shoponline.chinaorder.dto.ProductCategory;
import com.shoponline.chinaorder.entity.*;
import com.shoponline.chinaorder.service.attribute.AttributeService;
import com.shoponline.chinaorder.service.attributevalue.attribute.AttributeValueService;
import com.shoponline.chinaorder.service.authority.AuthorityService;
import com.shoponline.chinaorder.service.brand.BrandService;
import com.shoponline.chinaorder.service.cart.CartService;
import com.shoponline.chinaorder.service.category.CategoryService;
import com.shoponline.chinaorder.service.color.ColorService;
import com.shoponline.chinaorder.service.commune.CommuneService;
import com.shoponline.chinaorder.service.district.DistrictService;
import com.shoponline.chinaorder.service.image.ImageService;
import com.shoponline.chinaorder.service.imageBannerr.imageProduct.ImageBannerService;
import com.shoponline.chinaorder.service.imageProduct.ImageProductService;
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
import com.shoponline.chinaorder.support.PathEncoderDecoder;
import com.shoponline.chinaorder.support.RoleSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private final ImageProductService imageProductService;
    private final ImageBannerService imageBannerService;
    private final LogsService logsService;
    private final OrdersService ordersService;
    private final OrderItemService orderItemService;
    private final PeopleService peopleService;
    private final ProductService productService;
    private final ProvinceService provinceService;
    private final ReviewService reviewService;
    private final SizeService sizeService;
    private final AttributeService attributeService;
    private final AttributeValueService attributeValueService;
    private final StatusService statusService;
    private final SupplierService supplierService;
    private final UnitService unitService;
    private final VariantService variantService;
    private final VoucherService voucherService;
    private MinioService minioService;

    @Autowired
    public mainController(AuthorityService authorityService, UserService userService, BrandService brandService, CartService cartService, CategoryService categoryService, ColorService colorService, CommuneService communeService, DistrictService districtService, ImageService imageService, ImageProductService imageProductService, ImageBannerService imageBannerService, LogsService logsService, OrdersService ordersService, OrderItemService orderItemService, PeopleService peopleService, ProductService productService, ProvinceService provinceService, ReviewService reviewService, SizeService sizeService, AttributeService attributeService, AttributeValueService attributeValueService, StatusService statusService, SupplierService supplierService, UnitService unitService, VariantService variantService, VoucherService voucherService, MinioService minioService) {
        this.authorityService = authorityService;
        this.userService = userService;
        this.brandService = brandService;
        this.cartService = cartService;
        this.categoryService = categoryService;
        this.colorService = colorService;
        this.communeService = communeService;
        this.districtService = districtService;
        this.imageService = imageService;
        this.imageProductService = imageProductService;
        this.imageBannerService = imageBannerService;
        this.logsService = logsService;
        this.ordersService = ordersService;
        this.orderItemService = orderItemService;
        this.peopleService = peopleService;
        this.productService = productService;
        this.provinceService = provinceService;
        this.reviewService = reviewService;
        this.sizeService = sizeService;
        this.attributeService = attributeService;
        this.attributeValueService = attributeValueService;
        this.statusService = statusService;
        this.supplierService = supplierService;
        this.unitService = unitService;
        this.variantService = variantService;
        this.voucherService = voucherService;
        this.minioService = minioService;
    }

    String template = "layout";
//    String domain = "http://minio-container:9000/leng-keng/"; /** dùng chạy docker-compose trên local */
//    String domain = "http://localhost:9000/leng-keng/"; /** dùng chạy container minio trên local */
    String domain = "http://chinaorder.site:9000/leng-keng/";
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
    public String Lengkeng(Principal principal, Authentication authentication, Model model){
        if(principal != null && authentication != null){
            String username = principal.getName();
            String authority = "";
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                authority = grantedAuthority.getAuthority();
            }
            if((authority.equals(RoleSystem.ROLE_ADMIN) || authority.equals(RoleSystem.ROLE_MANAGER))) return Redirect("/admin/dashboard","");
        }
        model.addAttribute("banners", imageBannerService.getAllImageBannersActive());
        model.addAttribute("domain", domain);
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
    public String category(Model model){
//        ProductCategory mockProductCategory = mock(ProductCategory.class);
//        when(mockProductCategory.getProduct().getCategory().getId()).thenReturn(Integer.valueOf(1));
        List<ImageProduct> imageProducts = imageProductService.getAllImageProducts();
        Map<Integer, ImageProduct> uniqueImageProductMap = new HashMap<>();
        for (ImageProduct imageProduct : imageProducts) {
            uniqueImageProductMap.put(imageProduct.getProduct().getId(), imageProduct);
        }
        List<ProductCategory> productCategories = new ArrayList<>();
        productService.getAllProducts().forEach(p->{
            uniqueImageProductMap.values().forEach(im -> {
                if(im.getProduct().equals(p)){
                    List<Variants> variants = variantService.FindAllByProduct(p);
                    if(!variants.isEmpty()) {
                        Variants variant = variants.get(0);
                        int v_quantity = variants.size();
                        productCategories.add(new ProductCategory().builder()
                                .product(p)
                                .variant(variant)
                                .variants(v_quantity)
                                .imageProduct(im)
                                .build());
                    } else {
                        productCategories.add(new ProductCategory().builder()
                                .product(p)
                                .variants(0)
                                .imageProduct(im)
                                .build());
                    }
                }
            });
        });

        Map<Integer, List<ProductCategory>> groupedByCategory = productCategories.stream()
                .collect(Collectors.groupingBy(pC -> pC.getProduct().getCategory().getId()));

        List<List<ProductCategory>> allGroupedCategories = new ArrayList<>(groupedByCategory.values());

        model.addAttribute("allGroupedCategories", allGroupedCategories);
        model.addAttribute("domain", domain);
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
                                @RequestParam("id") int supplier_id){
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
                             @RequestParam("id") int brand_id){
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
                                 @RequestParam("id") int category_id){
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
                            @RequestParam("id") int unit_id){
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

    /********************* sizes *********************/

    @GetMapping("/admin/sizes")
    public String size(Model model,
                       @RequestParam(value = "success", defaultValue = "false") boolean success,
                       @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        model.addAttribute("sizes", sizeService.getAllSizes());
        model.addAttribute("sizesx", sizeService.findSizesNotUsedInVariants());
        model.addAttribute("content", "pages/support/size");
        model.addAttribute("title", "Quản lý đơn vị kích thước");

        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        return template;
    }

    @PostMapping({"/admin/sizes/add"})
    public String sizes_Add(Model model,
                            @RequestParam("sizeName") String sizeName) {
        try {
            Sizes sizes = new Sizes(sizeName);
            sizeService.createSize(sizes);
            return Redirect("/admin/sizes", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/sizes", false);
        }
    }


    @PostMapping({"/admin/sizes/del"})
    public String sizes_Del(Model model,
                            @RequestParam("id") int size_id){
        try{
            sizeService.deleteSize(size_id);
            return Redirect("/admin/sizes", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/sizes", false);
        }
    }

    @PostMapping({"/admin/sizes/edit"})
    public String sizes_Edit(Model model,
                             @RequestParam("id") int id,
                             @RequestParam("name") String name){
        try{
            Sizes size = sizeService.findSizeById(id);
            size.setSizeName(name);
            sizeService.createSize(size);
            return Redirect("/admin/sizes", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/sizes", false);
        }
    }


    /********************* attribute *********************/

    @GetMapping("/admin/attributes")
    public String attribute(Model model,
                            @RequestParam(value = "success", defaultValue = "false") boolean success,
                            @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        model.addAttribute("attributes", attributeService.getAllAttribute());
        model.addAttribute("attributesx", attributeService.findAttributesNotUsedInAttributeValues());
        model.addAttribute("content", "pages/support/attribute");
        model.addAttribute("title", "Quản lý thuộc tính");

        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        return template;
    }
    @PostMapping({"/admin/attributes/add"})
    public String attributes_Add(Model model,
                                 @RequestParam("name") String name) {
        try {
            Attribute attribute = new Attribute(name);
            attributeService.createAttribute(attribute);
            return Redirect("/admin/attributes", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/attributes", false);
        }
    }
    @PostMapping({"/admin/attributes/del"})
    public String attributes_Del(Model model,
                                 @RequestParam("id") int attribute_id){
        try{
            attributeService.deleteAttribute(attribute_id);
            return Redirect("/admin/attributes", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/attributes", false);
        }
    }

    @PostMapping({"/admin/attributes/edit"})
    public String attributes_Edit(Model model,
                                  @RequestParam("id") int id,
                                  @RequestParam("name") String name){
        try{
            Attribute attribute = attributeService.findAttributeById(id);
            attribute.setName(name);
            attributeService.createAttribute(attribute);
            return Redirect("/admin/attributes", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/attributes", false);
        }
    }

    /********************* status *********************/

    @GetMapping("/admin/status")
    public String status(Model model,
                         @RequestParam(value = "success", defaultValue = "false") boolean success,
                         @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        model.addAttribute("liststatus", statusService.getAllStatuses());
        model.addAttribute("liststatusx", statusService.findStatusNotUsedInOrder());
        model.addAttribute("content", "pages/support/status");
        model.addAttribute("title", "Quản lý trạng thái đơn");

        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        return template;
    }

    @PostMapping({"/admin/status/add"})
    public String status_Add(Model model,
                             @RequestParam("status_name") String status_name) {
        try {
            Status status = new Status(status_name); //#EE82EE
            statusService.createStatus(status);
            return Redirect("/admin/status", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/status", false);
        }
    }


    @PostMapping({"/admin/status/del"})
    public String status_Del(Model model,
                             @RequestParam("id") int status_id){
        try{
            statusService.deleteStatus(status_id);
            return Redirect("/admin/status", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/status", false);
        }
    }

    @PostMapping({"/admin/status/edit"})
    public String status_Edit(Model model,
                              @RequestParam("id") int id,
                              @RequestParam("name") String statusname){
        try{
            Status status = statusService.findStatusById(id);
            status.setStatusname(statusname);
            return Redirect("/admin/status", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/status", false);
        }
    }

    /********************* colors *********************/

    @GetMapping("/admin/colors")
    public String colors(Model model,
                         @RequestParam(value = "success", defaultValue = "false") boolean success,
                         @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        model.addAttribute("colors", colorService.getAllColors());
        model.addAttribute("colorsx", colorService.findColorsNotUsedInVariants());
        model.addAttribute("content", "pages/support/color");
        model.addAttribute("title", "Quản lý đơn vị kích thước");

        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        return template;
    }

    @PostMapping({"/admin/colors/add"})
    public String colors_Add(Model model,
                             @RequestParam("color") String color,
                             @RequestParam("abbreviations") String abbreviations,
                             @RequestParam("hexCode") String hexCode) {
        try {
            Colors color_ = new Colors(color, abbreviations, hexCode); //#EE82EE
            colorService.createColor(color_);
            return Redirect("/admin/colors", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/colors", false);
        }
    }


    @PostMapping({"/admin/colors/del"})
    public String colors_Del(Model model,
                             @RequestParam("id")  int color_id){
        try{
            colorService.deleteColor(color_id);
            return Redirect("/admin/colors", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/colors", false);
        }
    }

    @PostMapping({"/admin/colors/edit"})
    public String colors_Edit(Model model,
                              @RequestParam("id") int id,
                              @RequestParam("color") String color,
                              @RequestParam("abbreviations") String abbreviations,
                              @RequestParam("hexCode") String hexCode){
        try{
            Colors colorById = colorService.findColorById(id);
            colorById.setColor(color);
            colorById.setAbbreviations(abbreviations);
            colorById.setHexCode(hexCode);
            colorService.createColor(colorById);
            return Redirect("/admin/colors", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(": " + e.getMessage());
            return Redirect("/admin/colors", false);
        }
    }

    /***************************************************/
    /**************** Chỉnh sửa Banner ****************/
    /***************************************************/

    @GetMapping("/admin/bannertop")
    public String bannertop(Model model,
                           @RequestParam(value = "success", defaultValue = "false") boolean success,
                           @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        List<ImageBanner> imageBanners = imageBannerService.getAllImageBanners();
        model.addAttribute("domain", domain);
        model.addAttribute("imageBanners", imageBanners);

        model.addAttribute("content", "pages/support/bannertop");
        model.addAttribute("title", "Quản lý Banner top trang chủ");

        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        return template;
    }

    @PostMapping({"/admin/bannertop/add"})
    public String bannertop_Add(Model model, Principal principal,
                             @ModelAttribute ImageBanner imageBanner,
                             @RequestParam("file") MultipartFile file) {
        String fullPath = "banner/banner-top/";
        String fullPathName = fullPath + file.getOriginalFilename();
        try {
            minioService.uploadFile(fullPathName, file.getInputStream(), file.getContentType());
            imageBanner.setImgurl(fullPathName);
            imageBanner.setType("banner-top");
            imageBannerService.createImageBanner(imageBanner);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return Redirect("/admin/bannertop/?username=" + principal.getName(), false);
        }
        return Redirect("/admin/bannertop/?username=" + principal.getName(), true);
    }

    @GetMapping("/admin/bannertop/del/{id}")
    public String bannertop_del(Model model, Principal principal,
                                @PathVariable("id")   int id,
                                @RequestParam(value = "success", defaultValue = "false") boolean success,
                                @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        ImageBanner imageBanner = imageBannerService.findImageBannerById(id);
        String filePath = imageBanner.getImgurl();
        try {
            minioService.deleteFile(filePath);
            List<ImageBanner> imageBanners = imageBannerService.findImagesByUrl(filePath);
            imageBanners.forEach(i->imageBannerService.deleteImageBanner(i.getId()));
        } catch (Exception e) {
            // Bắt mọi ngoại lệ
            e.printStackTrace(); // In thông tin lỗi ra console để debug
            return Redirect("/admin/bannertop/?username=" + principal.getName(), false); // Xóa thất bại
        }
        return Redirect("/admin/bannertop/?username=" + principal.getName(), true); // Xóa thành công
    }

    /***************************************************/
    /********************* Product *********************/
    /***************************************************/

    @GetMapping("/admin/products")
    public String products(Model model,
                           @RequestParam(value = "success", defaultValue = "false") boolean success,
                           @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        List<Products> products = productService.getAllProducts();

        model.addAttribute("imageProducts", imageProductService.getAllImageProducts());
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
    @PostMapping("/admin/products/uploadImage")
    public String products_uploadFiles(@RequestParam("files") MultipartFile[] files,
                                       @RequestParam("product_id") int product_id,
                                       Principal principal) {
        String fullPath = "products/" + product_id + "/";
        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;
            String fullPathName = fullPath + file.getOriginalFilename();
            try {
                Products product = productService.findProductById(product_id);
                imageProductService.createImageProduct(new ImageProduct(product, fullPathName));
                System.out.println(fullPathName);
                minioService.uploadFile(fullPathName, file.getInputStream(), file.getContentType());
//                model.addAttribute("message", "File uploaded successfully");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return Redirect("/admin/products/?username=" + principal.getName(), false);
//                model.addAttribute("error", "Error uploading file: " + e.getMessage());
            }
        }
        return Redirect("/admin/products/?username=" + principal.getName(), true);
    }
    @GetMapping("/admin/products/delImage")
    public String products_delImage(Model model, Principal principal,
                                    @RequestParam("product_id") int id,
                                    @RequestParam("url") String imgUrl,
                                    @RequestParam(value = "success", defaultValue = "false") boolean success,
                                    @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        String originalImgUrl = PathEncoderDecoder.decodePath(imgUrl);
//        int startIndex = originalImgUrl.indexOf("products") + "products".length();
//        String resultFile = originalImgUrl.substring(startIndex);
        try {
            minioService.deleteFile(originalImgUrl);
            List<ImageProduct> imageProducts = imageProductService.findImagesByUrl(originalImgUrl);
            imageProducts.forEach(i->imageProductService.deleteImageProduct(i.getId()));
        } catch (Exception e) {
            // Bắt mọi ngoại lệ
            e.printStackTrace(); // In thông tin lỗi ra console để debug
            return Redirect("/admin/products/?username=" + principal.getName(), false); // Xóa thất bại
        }
        return Redirect("/admin/products/?username=" + principal.getName(), true); // Xóa thành công
    }


    @GetMapping("/admin/products/detail/{id}")
    public String product_detail(Model model,
                                 @PathVariable int id,
                                 @RequestParam(value = "success", defaultValue = "false") boolean success,
                                 @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        Products product = productService.findProductById(id);
        List<Variants> variants = variantService.FindAllByProduct(product);
//        minioService.listFileVariants();
        model.addAttribute("images", imageService.getAllImages());
        model.addAttribute("attributeValues", attributeValueService.findAllByProduct(product));
        model.addAttribute("product", product);
        model.addAttribute("variants", variants);
        model.addAttribute("colors", colorService.getAllColors());
        model.addAttribute("sizes", sizeService.getAllSizes());
        model.addAttribute("attributes", attributeService.getAllAttribute());
        model.addAttribute("content", "pages/product/product_detail_variants");
        model.addAttribute("title", "Quản lý biến thể của " + product.getProductName());

        model.addAttribute("success", success);
        model.addAttribute("unsuccess", unsuccess);
        return template;
    }

    @PostMapping({"/admin/products/detail/addVariants"})
    public String Products_detail_addVariants(Model model, @ModelAttribute Variants variants) {
        try {
            variantService.createVariant(variants);
            return Redirect("/admin/products/detail/"+variants.getProduct().getId(), true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/products/detail/"+variants.getProduct().getId(), false);
        }
    }

    @PostMapping({"/admin/products/detail/addAttributes"})
    public String Products_detail_addAttributes(Model model, @RequestParam("product") int product_id,
                                                @RequestParam("attribute") List<Integer> attribute_ids,
                                                @RequestParam("value") List<String> values) {
        try {
            List<AttributeValue> attributeValues = new ArrayList<>();
            Products product = productService.findProductById(product_id);
            for (int i = 0; i < values.size(); i++) {
                attributeValues.add(new AttributeValue(attributeService.findAttributeById(attribute_ids.get(i)), product, values.get(i)));
            }
            attributeValues.forEach(a -> attributeValueService.createAttributeValue(a));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/products/detail/"+product_id, false);
        }
        return Redirect("/admin/products/detail/"+ product_id, true);
    }
    @PostMapping("/admin/products/detail/uploadImage")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files,
                              @RequestParam("product_id") int product_id,
                              @RequestParam("variant_id") int variant_id) {
        String fullPath = "products/" + product_id + "/" + variant_id + "/";
        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;
            String fullPathName = fullPath + file.getOriginalFilename();
            try {
                Variants variant = variantService.findVariantById(variant_id);
                imageService.createImage(new Image(variant, fullPathName));
                System.out.println(fullPathName);
                minioService.uploadFile(fullPathName, file.getInputStream(), file.getContentType());
//                model.addAttribute("message", "File uploaded successfully");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return Redirect("/admin/products/detail/" + product_id, false);
//                model.addAttribute("error", "Error uploading file: " + e.getMessage());
            }
        }
        return Redirect("/admin/products/detail/" + product_id, true);
    }
    @GetMapping("/admin/products/detail/delImage")
    public String delImage(Model model,
                           @RequestParam("product_id") int id,
                           @RequestParam("url") String imgUrl,
                           @RequestParam(value = "success", defaultValue = "false") boolean success,
                           @RequestParam(value = "unsuccess", defaultValue = "false") boolean unsuccess) {
        String originalImgUrl = PathEncoderDecoder.decodePath(imgUrl);
        try {
            minioService.deleteFile(originalImgUrl);
            List<Image> images = imageService.findImagesByUrl(originalImgUrl);
            images.forEach(i->imageService.deleteImage(i.getId()));
        } catch (Exception e) {
            // Bắt mọi ngoại lệ
            e.printStackTrace(); // In thông tin lỗi ra console để debug
            return Redirect("/admin/products/detail/" + id, false); // Xóa thất bại
        }
        return Redirect("/admin/products/detail/" + id, true); // Xóa thành công
    }

    /***************************************************/
    /********************* Variants *********************/
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
            variantService.createVariant(variants);
            return Redirect("/admin/variants", true);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
            return Redirect("/admin/variants", false);
        }
    }


    /***************************************************/
    /********************* xxxxxxx *********************/
    /***************************************************/



}
