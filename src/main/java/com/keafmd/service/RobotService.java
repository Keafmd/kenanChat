package com.keafmd.service;

import com.keafmd.model.Response;

import java.io.IOException;

/**
 * Keafmd
 *
 * @ClassName: RobotService
 * @Description:
 * @author: 牛哄哄的柯南
 * @date: 2021-08-05 9:27
 */

public interface RobotService {
    Response answer(String question) throws IOException;
}
