package com.jk.service;

import java.util.List;
import java.util.Map;

public interface BookService {

    List<Map<String, Object>> queryBook();

    List<Map<String, Object>> queryMM();

    List<Map<String, Object>> queryZhu();
}