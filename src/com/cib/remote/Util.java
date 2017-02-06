package com.cib.remote;

import com.cib.remote.bean.InquiryBean;
import ibbl.common.util.FinAmount;
import ibbl.common.util.GeneralUtil;
import ibbl.gl.bean.GenVoucherBean;
import ibbl.gl.bean.GlHeadBean;

/**
 * Copyright &copy; 2002-2003 Islami Bank Bangladesh Limited
 * <p>
 * Original author: Ayatullah Khomeni
 * <br/> Date: 01/01/2017
 * <br/> Last modification by: ayat $
 * <br/> Last modification on 01/01/2017: 3:05 PM
 * <br/> Current revision: : 1.1.1.1
 * </p>
 * Revision History:
 * ------------------
 */

public class Util {
    public Util() {
    }

    public static GenVoucherBean makeCIBGenVoucher(InquiryBean inquiry) {
        String crHead = "10067500";
        String desc = "CIB Report Inquiry Charge";
        String trCode = "147";
        String trType = "102";
        String refAccNo = "";
        FinAmount amount = new FinAmount(inquiry.getTotalCost().doubleValue());
        GenVoucherBean voucher = new GenVoucherBean();
        GlHeadBean debit = new GlHeadBean();
        GlHeadBean credit = new GlHeadBean();
        String dbtHead = inquiry.getChargeableAccount();
        if(!GeneralUtil.isBlankOrNull(dbtHead)) {
            debit.setCustomerAccount(false);
            debit.setAmount(amount);
            debit.setGlHead(dbtHead);
            debit.setInstrumentNo(inquiry.getInstrumentNo());
            voucher.setDebitSide(new GlHeadBean[]{debit});
        }

        if(!GeneralUtil.isBlankOrNull(crHead)) {
            credit.setCustomerAccount(false);
            credit.setAmount(amount);
            credit.setGlHead(crHead);
            credit.setInstrumentNo(inquiry.getInstrumentNo());
            voucher.setCreditSide(new GlHeadBean[]{credit});
        }

        voucher.setNote(desc);
        voucher.setTotalAmount(amount);
        voucher.setTransCode(trCode);
        voucher.setTransType(trType);
        voucher.setRefAccNo(refAccNo);
        return voucher;
    }

}
