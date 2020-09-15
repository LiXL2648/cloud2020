package com.li.springcloud.service;

import java.math.BigDecimal;

public interface AccountService {

    void decrease(Long useId, BigDecimal money);
}
