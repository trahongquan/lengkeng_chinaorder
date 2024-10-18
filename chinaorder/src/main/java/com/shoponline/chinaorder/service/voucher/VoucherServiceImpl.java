// VoucherServiceImpl.java
package com.shoponline.chinaorder.service.voucher;

import com.shoponline.chinaorder.dao.VoucherRepository;
import com.shoponline.chinaorder.entity.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public List<Voucher> getAllVouchers() {
        return voucherRepository.findAll();
    }

    @Override
    public Voucher createVoucher(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public Voucher findVoucherById(int id) {
        return voucherRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteVoucher(int id) {
        voucherRepository.deleteById(id);
    }
}
