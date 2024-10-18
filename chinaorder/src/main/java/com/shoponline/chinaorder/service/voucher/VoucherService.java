// VoucherService.java
package com.shoponline.chinaorder.service.voucher;

import com.shoponline.chinaorder.entity.Voucher;

import java.util.List;

public interface VoucherService {
    List<Voucher> getAllVouchers();

    Voucher createVoucher(Voucher voucher);

    Voucher findVoucherById(int id);

    void deleteVoucher(int id);
}
