package com.njusc.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Echo on 17/9/19.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
