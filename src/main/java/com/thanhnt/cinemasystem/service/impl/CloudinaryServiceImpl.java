package com.thanhnt.cinemasystem.service.impl;

import com.cloudinary.utils.ObjectUtils;
import com.thanhnt.cinemasystem.config.CloudinaryConfig;
import com.thanhnt.cinemasystem.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
  private final CloudinaryConfig cloudinaryConfig;

  @Override
  public String uploadImage(byte[] image) {
    var params =
        ObjectUtils.asMap(
            "folder", "cinema",
            "resource_type", "image");
    try {
      var uploadResult = cloudinaryConfig.cloudinary().uploader().upload(image, params);
      return uploadResult.get("secure_url").toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
