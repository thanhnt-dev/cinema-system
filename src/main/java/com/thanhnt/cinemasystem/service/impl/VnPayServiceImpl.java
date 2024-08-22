package com.thanhnt.cinemasystem.service.impl;

import com.thanhnt.cinemasystem.service.VnPayService;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class VnPayServiceImpl implements VnPayService {
  @Value("${spring.vnpay.pay-url}")
  private String vnpPayUrl;

  @Value("${spring.vnpay.return-url}")
  private String vnpReturnUrl;

  @Value("${spring.vnpay.tmn-code}")
  private String vnpTmnCode;

  @Value("${spring.vnpay.hash-secret}")
  private String secretKey;

  @Value("${spring.vnpay.api-url}")
  private String vnpApiUrl;

  @Value("${spring.vnpay.version}")
  private String vnPayVersion;

  @Value("${spring.vnpay.command}")
  private String vnPayCommand;

  @Value("${spring.vnpay.order-type}")
  private String vnPayOrderType;

  @Override
  public String hmacSHA512(String data) {
    try {
      final Mac hmac512 = Mac.getInstance("HmacSHA512");
      byte[] hmacKeyBytes = secretKey.getBytes();
      final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
      hmac512.init(secretKey);
      byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
      byte[] result = hmac512.doFinal(dataBytes);
      StringBuilder sb = new StringBuilder(2 * result.length);
      for (byte b : result) {
        sb.append(String.format("%02x", b & 0xff));
      }
      return sb.toString();

    } catch (Exception ex) {
      return "";
    }
  }

  @Override
  public Map<String, String> buildVnPayParams(Long amount, String code) {
    var vnpTxnRef = code;

    Map<String, String> vnpParams = new HashMap<>();
    vnpParams.put("vnp_Version", vnPayVersion);
    vnpParams.put("vnp_Command", vnPayCommand);
    vnpParams.put("vnp_TmnCode", vnpTmnCode);
    vnpParams.put("vnp_Amount", String.valueOf(amount));
    vnpParams.put("vnp_CurrCode", "VND");
    vnpParams.put("vnp_BankCode", "NCB");
    vnpParams.put("vnp_TxnRef", vnpTxnRef);
    vnpParams.put("vnp_OrderInfo", "Payment for " + vnpTxnRef);
    vnpParams.put("vnp_Locale", "vn");
    vnpParams.put("vnp_ReturnUrl", vnpReturnUrl);
    vnpParams.put("vnp_OrderType", "billpayment");
    vnpParams.put("vnp_IpAddr", "127.0.0.1");

    Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    String vnpCreateDate = formatter.format(cld.getTime());
    vnpParams.put("vnp_CreateDate", vnpCreateDate);

    cld.add(Calendar.MINUTE, 15);
    String vnpExpireDate = formatter.format(cld.getTime());
    vnpParams.put("vnp_ExpireDate", vnpExpireDate);

    return vnpParams;
  }

  @Override
  public String getPaymentUrl(String queryUrl) {
    return String.format("%s?%s", vnpPayUrl, queryUrl);
  }
}
